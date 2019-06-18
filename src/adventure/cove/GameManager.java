/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

import java.io.IOException;

/**
 *
 * @author user
 */
public class GameManager {
    Map[] maps; // What maps are there?
    Map   currentMap; // What map are we on right now?
    Player player = new Player(); // Store the player data in here
    BattleManager battleManager = new BattleManager(); // Don't forget the battle manager so we can FIGHT
    
    GameManager()
    {
        
    }
    
    // Start the game and keep running until we die
    public void startGame() throws IOException
    {
        displayGame();
        while (player.getHP() > 0)
        {
            // Get user input and filter it
            char input = getInput();
            
            // Process the input (movement, events, etc)
            processInput(input);
            
            // Display the user result
            displayGame();
        }
    }
    
    // Nothing special here
    private char getInput() throws IOException
    {
        // Filter out chars that aren't WASD
        switch (Character.toUpperCase((char)System.in.read()))
        {
            case 'W':
                return 'W';
            case 'A':
                return 'A';
            case 'S':
                return 'S';
            case 'D':
                return 'D';
        }
        
        return '0';
    }
    
    // Movement and events
    private void processInput(char dir) throws IOException
    {
        // What way did the user want us to go?
        switch (dir)
        {
            case 'W':
                player.moveForward();
                break;
            case 'A':
                player.moveLeft();
                break;
            case 'S':
                player.moveBackwords();
                break;
            case 'D':
                player.moveRight();
                break;
        }
        
        // Are there any events in this area?
        checkEvents();
        
        // Are we in a wall?
        for (char coll : currentMap.collChars)
            if (currentMap.getCharAtPos(player.getPosition()) == coll) // wall, are we?
            {
                // Dammit we are, go back a space :(
                player.restorePrevPos();
                break;
            }
        
        // Is the player in the grass getting dirty?
        if (currentMap.getCharAtPos(player.getPosition()) == 'w' && Math.random() >= 0.925)
        {
            // They are, meaby a monster will make them not want to be in the grass
            battleManager.triggerRandomBattle(player);
            
            // Did we kill them by accident?
            if (player.getHP() < 1)
            {
                // Welp, we killed them by accident
                clearScreen();
                clearScreen();
                System.out.println("| You died to an enemy and lost the game!\n\n" +
                "| Thank you for playing my game, if you want more you can make your own\n" +
                "| scripts and run them inside the program at the start!\n" +
                "| Checkout the github at https://github.com/nickthegamer5/Adventure-Cove for more details about scripting.\n" +
                "\n| CREDITS:\n| Nick Coombe - Devloper\n| Colin Vanvervorf - Play Tester\n| Drew Purde - Play Tester\n\nPress any key and then enter to exit...");
                System.in.read();
                System.in.read();
                System.exit(0);
            }
        }
    }
    
    // Display HP
    private void displayInfo()
    {
        // Fancy smancy math to get how long a number is
        System.out.print(' ');
        for (int i = 0; i != Math.ceil(Math.log10(player.getHP()))+6; i++)
            System.out.print('-');
        System.out.print('-');
        System.out.print('\n');
        
        // Output fancy text
        System.out.print("| HP: " + player.getHP() + " |\n");
        
        // Close off fancy text
        System.out.print(' ');
        for (int i = 0; i != Math.ceil(Math.log10(player.getHP()))+6; i++)
            System.out.print('-');
        System.out.print('-');
        System.out.print('\n');
    }
    
    // Is the player in an event?
    private void checkEvents() throws IOException
    {
        // For each event on the map
        for (Event event : currentMap.events)
        {
            // If the player is in the event, call a code RED
            if (event.inEventArea(player.getPosition()))
                switch (event.type)
                {
                    // Show the user a sign if they walked into a sign
                    case "TEXT":
                        displayText(event.data);
                        break;
                        
                    // Break physics and rip reality in two peices if they try to teleport
                    case "TELEPORT":
                        switchMap(event.data[2], new Vector2D(Integer.parseInt(event.data[0]), Integer.parseInt(event.data[1])));
                        break;
                        
                    // Smash their bones if the previous two where not enough
                    case "BOSS":
                        // RUN THE BONE-ZONE
                        Boolean died = battleManager.triggerBattle(player, "ASUUTA");
                        
                        // Lets hope the bone-zone wasn't too boney
                        if (died)
                        {
                            // Dammit is was
                            clearScreen();
                            System.out.println("| You died to the boss and lost the game!\n\n" +
                                                "| Thank you for playing my game, if you want more you can make your own\n" +
                                                "| scripts and run them inside the program at the start!\n" +
                                                "| Checkout the github at https://github.com/nickthegamer5/Adventure-Cove for more details about scripting.\n" +
                                                "\n| CREDITS:\n| Nick Coombe - Devloper\n| Colin Vanvervorf - Play Tester\n| Drew Purde - Play Tester\n\nPress any key and then enter to exit...");
                            System.in.read();
                            System.in.read();
                        }
                        break;
                }
        }
    }
    
    // Show them FANCY TEXT
    private void displayText(String[] data) throws IOException
    {
        // Clear their screan to make sure the fancy text doesn't blind them.
        clearScreen();
        
        // Check the length of the fancy text
        int longestLine = 0;
        for (String line : data)
            if (line.length() > longestLine)
                longestLine = line.length();
        
        // Put a cap on the fancy text
        System.out.print(" ");
        for (int i = 0; i != longestLine+2; i++)
            System.out.print("-");
        System.out.print("\n");
        
        // Output the fancy text
        for (String line : data)
        {
            System.out.print("| ");
            System.out.print(line);
            
            if (line.length() < longestLine)
            {
                for (int i = 0; i != (longestLine-line.length()); i++)
                    System.out.print(' ');
            }
            
            System.out.println(" |");
        }
        
        // Put some pants on the fancy text
        System.out.print(" ");
        for (int i = 0; i != longestLine+2; i++)
            System.out.print("-");
        System.out.print("\nPress enter to continue...");
        
        // Read twice because java is weird
        System.in.read();
        System.in.read();
        
    }
    
    // Display the game!
    private void displayGame()
    {
        // Clear the user screen
        clearScreen();
        
        //System.out.println("POSITION: " + player.getPosition().getX() + ", " + player.getPosition().getY());
        // Display the map
        displayMap();
        // Display the HP
        displayInfo();
    }
    
    // Display the glorious map
    private void displayMap()
    {
        // While canada hasn't taken over russia
        for (int y = 0; y != currentMap.size_y; y++)
        {
            // While canada hasn't taken over mexico
            for (int x = 0; x != currentMap.size_x; x++)
                // Check if its legal
                if (y == player.getPosition().getY() && x == player.getPosition().getX())
                    System.out.print('@'); // ITS NOT!?!
                else
                    System.out.print(currentMap.displayMap[x][y]); // IT IS!?!?!
            System.out.print('\n'); // Take over new country
        }
                    
    }
    
    // Whipe the users dirty keyboard keys away from their face
    private void clearScreen()
    {
        // You can read this by saying "backslash n" 30 times or shouting "AHHHHHHHHH" for 3 seconds.
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    // Break more physics and move the player
    private void switchMap(String mapName, Vector2D pos_)
    {
        // For each map we have...
        for (int i = 0; i != maps.length; i++)
        {
            // is it the map we want?
            if (maps[i].name.equals(mapName))
            {
                // yes it is, now set it as our current
                currentMap = maps[i];
                player.setPosition(pos_);
                return;
            }
            // burn it otherwise
        }
    }
}
