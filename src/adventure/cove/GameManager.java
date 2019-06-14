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
    
    GameManager()
    {
        
    }
    
    public void startGame() throws IOException
    {
        while (player.getHP() != 0)
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
    }
    
    private void checkEvents() throws IOException
    {
        for (Event event : currentMap.events)
        {
            System.out.println(event.inEventArea(player.getPosition()));
            if (event.inEventArea(player.getPosition()))
                switch (event.type)
                {
                    case "TEXT":
                        System.out.println("TEXTY HERE");
                        displayText(event.data);
                        break;
                    case "TELEPORT":
                        System.out.println("TELEPORTY HERE");
                        switchMap(event.data[2], new Vector2D(Integer.parseInt(event.data[0]), Integer.parseInt(event.data[1])));
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
        //clearScreen();
        
        System.out.println("POSITION: " + player.getPosition().getX() + ", " + player.getPosition().getY());
        displayMap();
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
