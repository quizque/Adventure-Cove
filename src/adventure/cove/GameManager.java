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
    Map[] maps;
    Map   currentMap;
    Player player = new Player();
    BattleManager battleManager = new BattleManager();
    
    GameManager()
    {
        
    }
    
    public void startGame() throws IOException
    {
        displayGame();
        while (player.getHP() > 0)
        {
            char input = getInput();
            processInput(input);
            displayGame();
        }
    }
    
    private char getInput() throws IOException
    {
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
    
    private void processInput(char dir) throws IOException
    {
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
        
        
        checkEvents();
        
        for (char coll : currentMap.collChars)
            if (currentMap.getCharAtPos(player.getPosition()) == coll)
            {
                player.restorePrevPos();
                break;
            }
        
        if (currentMap.getCharAtPos(player.getPosition()) == 'w' && Math.random() >= 0.925)
        {
            battleManager.triggerRandomBattle(player);
            if (player.getHP() < 1)
            {
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
    
    private void displayInfo()
    {
        System.out.print(' ');
        for (int i = 0; i != Math.ceil(Math.log10(player.getHP()))+6; i++)
            System.out.print('-');
        System.out.print('-');
        System.out.print('\n');
        
        System.out.print("| HP: " + player.getHP() + " |\n");
        
        System.out.print(' ');
        for (int i = 0; i != Math.ceil(Math.log10(player.getHP()))+6; i++)
            System.out.print('-');
        System.out.print('-');
        System.out.print('\n');
    }
    
    private void checkEvents() throws IOException
    {
        for (Event event : currentMap.events)
        {
            if (event.inEventArea(player.getPosition()))
                switch (event.type)
                {
                    case "TEXT":
                        displayText(event.data);
                        break;
                    case "TELEPORT":
                        switchMap(event.data[2], new Vector2D(Integer.parseInt(event.data[0]), Integer.parseInt(event.data[1])));
                        break;
                    case "BOSS":
                        System.out.println("TRIG");
                        Boolean died = battleManager.triggerBattle(player, "ASUUTA");
                        if (died)
                        {
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
    
    private void displayText(String[] data) throws IOException
    {
        clearScreen();
        
        int longestLine = 0;
        for (String line : data)
            if (line.length() > longestLine)
                longestLine = line.length();
        
        System.out.print(" ");
        for (int i = 0; i != longestLine+2; i++)
            System.out.print("-");
        System.out.print("\n");
        
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
        
        System.out.print(" ");
        for (int i = 0; i != longestLine+2; i++)
            System.out.print("-");
        System.out.print("\nPress enter to continue...");
        
        // Read twice because of weird java issue
        System.in.read();
        System.in.read();
        
    }
    
    private void displayGame()
    {
        clearScreen();
        
        System.out.println("POSITION: " + player.getPosition().getX() + ", " + player.getPosition().getY());
        displayMap();
        displayInfo();
    }
    
    private void displayMap()
    {
        for (int y = 0; y != currentMap.size_y; y++)
        {
            for (int x = 0; x != currentMap.size_x; x++)
                if (y == player.getPosition().getY() && x == player.getPosition().getX())
                    System.out.print('@');
                else
                    System.out.print(currentMap.displayMap[x][y]);
            System.out.print('\n');
        }
                    
    }
    
    private void clearScreen()
    {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    private void switchMap(String mapName, Vector2D pos_)
    {
        
        for (int i = 0; i != maps.length; i++)
        {
            if (maps[i].name.equals(mapName))
            {
                currentMap = maps[i];
                player.setPosition(pos_);
                return;
            }
        }
    }
}
