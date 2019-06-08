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
    
    public void parseScript(String scriptLocation) throws FileNotFoundException, IOException
    {
        BufferedReader script = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(scriptLocation)));
        
        String line;
        
        debugPrint("START PARSE");
        
        while ((line = script.readLine()) != null)
        {
            if ("".equals(line.trim()))
                continue;
            
            if ("#DECLARE_MAP".equals(line))
            {
                Boolean parsingMap = true;
                Map parsedMap = new Map();
                int MaxEvents = 10;
                int CurrentEvents = 0;
                
                while (parsingMap)
                {
                    if ((line = script.readLine()) == null)
                        break;
                    
                    if ("".equals(line.trim()))
                        continue;
                    
                    if ("#END_DECLARE".equals(line))
                    {
                        parsingMap = false;
                        debugPrint("END DECLARE");
                        continue;
                    }
                    
                    String[] args = line.split(" ");
                    
                    switch(args[0])
                    {
                        case "#NAME":
                            parsedMap.name = args[1];
                            debugPrint("NAME: " + parsedMap.name);
                            break;
                        
                        case "#MAP":
                            int width = Integer.parseInt(args[1]);
                            int height = Integer.parseInt(args[2]);
                            
                            debugPrint("WIDTH,HEIGHT: " + width + "," + height);
                            
                            parsedMap.displayMap = new char[width][height];
                            
                            line = script.readLine();
                            
                            for (int y = 0; y != height; y++)
                            {
                                for (int x = 0; x != width; x++)
                                {
                                    parsedMap.displayMap[x][y] = line.charAt(x);
                                }
                                
                                line = script.readLine();
                            }
                            
                            break;
                            
                        case "#MAP_COLL":
                            parsedMap.collChars = new char[args.length-1];
                            for (int i = 1; i != args.length; i++)
                                parsedMap.collChars[i-1] = args[i].charAt(0);
                            
                            String o = "";
                            
                            for (char k : parsedMap.collChars)
                                o += k + " ";
                            
                            debugPrint("MAP COLLIDERS: " + o);
                            break;
                            
                        case "#MAP_EVENT":
                            switch (args[1])
                            {
                                case "TEXT":
                                    String[] arr = new String[1];
                                    for (int i = 6; i != args.length; i++)
                                        arr[0] += args[i] + " ";
                                    
                                    debugPrint("MAP TEXT EVENT");
                                    
                                    parsedMap.events[CurrentEvents] = new Event("TEXT", new Vector2D(Integer.parseInt(args[2]), Integer.parseInt(args[3])), new Vector2D(Integer.parseInt(args[4]), Integer.parseInt(args[5])), arr);
                                    break;
                            }
                            break;
                            
                    }
                }
            }
            
            debugPrint("FINISH PARSE");
        }
    }
    
    private void debugPrint(String msg)
    {
        System.out.println(msg);
    }
}
