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
    
    private void processInput(char dir)
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
        
        for (char coll : currentMap.collChars)
            if (currentMap.getCharAtPos(player.getPosition()) == coll)
            {
                player.restorePrevPos();
                break;
            }
    }
    
    private void displayGame()
    {
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
    
    private void switchMap(String mapName, Vector2D pos_)
    {
        for (Map map : maps)
            if (map.name.equals(mapName))
            {
                currentMap = map;
                player.setPosition(pos_);
                return;
            }
    }
}
