/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdventureScript {
    
    AdventureScript ()
    {
        
    }
    
    // Parse the input file to be used by the game.
    public GameManager parseScript(String scriptLocation) throws FileNotFoundException, IOException
    {
        // GameManager to store all the data and return
        GameManager manager = new GameManager();
        
        // Add the file to a buffered reader so we can read it...
        // NOTE: This is reading from internal and NEEDS to be changed!
        BufferedReader script = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(scriptLocation)));
        
        // This is to store the current line from the buffered reader
        String line;
        
        // Stores totals
        int totalMaps = 0;
        int totalEnemys = 0;
        
        debugPrint("START PARSE");
        
        while ((line = script.readLine()) != null)
            if ("#DECLARE_MAP".equals(line))
                totalMaps++;
            else if ("#DECLARE_ENEMY".equals(line))
                totalEnemys++;
        
        manager.maps = new Map[totalMaps];
        
        
        script = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(scriptLocation)));
        
        // While the buffer is not at the end of the file...
        while ((line = script.readLine()) != null)
        {
            // Remove white space and check if the line is blank.
            if ("".equals(line.trim()))
                continue; // Skip line if true
            
            // If we encounter a DECLARE_MAP tag...
            if ("#DECLARE_MAP".equals(line))
            {
                // Keep track of if we are still parsing the map
                Boolean parsingMap = true;
                
                // Is this the starting map?
                Boolean startingMap = false;
                
                // The the map somewhere while parsing it
                Map parsedMap = new Map();
                
                // The temp var to hold events on the map
                int maxEvents = 10;
                Event[] tempEvent = new Event[maxEvents];
                
                // While we are parsing the map...
                while (parsingMap)
                {
                    line = script.readLine();
                    
                    if ("".equals(line.trim()))
                        continue;
                    
                    // If we reach the end of a map tag...
                    if ("#END_DECLARE".equals(line))
                    {
                        // set parsingMap to false
                        parsingMap = false;
                        debugPrint("END DECLARE");
                        // and escape the while loop
                        continue;
                    }
                    
                    // Split the line into an array by spaces
                    String[] args = line.split(" ");
                    
                    // Switch case the tag
                    switch(args[0])
                    {
                        // If the tag is a NAME tag...
                        case "#NAME":
                            // Set the map name to the second arg
                            parsedMap.name = args[1];
                            debugPrint("NAME: " + parsedMap.name);
                            break;
                        
                        // If the tag is a MAP tag...
                        case "#MAP":
                            
                            // Set the width and height of the display map (the acctual map)
                            int width = Integer.parseInt(args[1]);
                            int height = Integer.parseInt(args[2]);
                            
                            debugPrint("WIDTH,HEIGHT: " + width + "," + height);
                            
                            parsedMap.size_x = width;
                            parsedMap.size_y = height;
                            
                            // declare the display map with width and height
                            parsedMap.displayMap = new char[width][height];
                            
                            // read a line
                            line = script.readLine();
                            
                            // and loop through the map
                            for (int y = 0; y != height; y++)
                            {
                                for (int x = 0; x != width; x++)
                                {
                                    // Store the char at the x y
                                    parsedMap.displayMap[x][y] = line.charAt(x);
                                }
                                
                                // read a line
                                line = script.readLine();
                            }
                            
                            break;
                        
                        // If the tag is the MAP COLLITION tag...
                        case "#MAP_COLLISIONS":
                            
                            // Go through all the args and append them to the coll array
                            parsedMap.collChars = new char[args.length-1];
                            for (int i = 1; i != args.length; i++)
                                parsedMap.collChars[i-1] = args[i].charAt(0);
                            
                            String o = "";
                            for (char k : parsedMap.collChars)
                                o += k + " ";
                            debugPrint("MAP COLLIDERS: " + o);
                            
                            break;
                        
                        // If the tag is the MAP EVENT tag...
                        case "#MAP_EVENT":
                            // Switch case the TYPE of event
                            switch (args[1])
                            {
                                // If it is a text event...
                                case "TEXT":
                                    
                                    int newlines = 1;
                                    for (int i = 6; i != args.length; i++)
                                        if (args[i].length() >= 3)
                                            if (args[i].charAt(args[i].length()-1) == 'n' && args[i].charAt(args[i].length()-2) == '\\')
                                                newlines++;
                                    
                                    String[] data = new String[newlines];
                                    newlines = 0;
                                    
                                    data[0] = args[6] + " ";
                                    for (int i = 7; i != args.length; i++)
                                        if (args[i].charAt(args[i].length()-1) == 'n' && args[i].charAt(args[i].length()-2) == '\\') 
                                        {
                                            data[newlines] += args[i].substring(0, args[i].length()-2);
                                            newlines++;
                                            i++;
                                            data[newlines] = args[i] + " ";
                                        }
                                        else
                                            data[newlines] += args[i] + " ";
                                    
                                    data[newlines] = data[newlines].trim();
                                    
                                    for (String i : data)
                                        System.out.println("LINES: " + i);
                                    
                                    // Build a new event with the arguments
                                    tempEvent[tempEvent.length - maxEvents] = new Event("TEXT", new Vector2D(Integer.parseInt(args[2]), Integer.parseInt(args[3])), new Vector2D(Integer.parseInt(args[4]), Integer.parseInt(args[5])), data);
                                    maxEvents--;
                                    break;
                            }
                            break;
                        
                        // Is this the starting game map?
                        case "#GAME_START":
                            debugPrint("GAME STARTING MAP");
                            startingMap = true;
                            parsedMap.playerStart = new Vector2D(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            break;
                            
                        // Is it the game instructions tag?
                        case "#GAME_INSTRUCTIONS":
                            String instructions = "";
                                    for (int i = 1; i != args.length; i++)
                                        instructions += args[i] + " ";
                            parsedMap.startingIntructions = instructions;
                            break;
                    }
                }
                
                if (startingMap)
                    manager.currentMap = parsedMap;
                
                int c = 0;
                for (int i = 0; i != tempEvent.length; i++)
                    if (tempEvent[i] != null)
                        c++;
                
                parsedMap.events = new Event[c];
                
                c = 0;
                for (int i = 0; i != tempEvent.length; i++)
                    if (tempEvent[i] != null)
                    {
                        parsedMap.events[c] = tempEvent[i];
                        c++;
                    }
                    
                debugPrint("FINISH PARSE" + parsedMap.events.length);
            }
            
            
        }
        
        return manager;
    }
    
    // Debugging stuff
    // NOTE: Needs to be removed
    private void debugPrint(String msg)
    {
        System.out.println(msg);
    }
}
