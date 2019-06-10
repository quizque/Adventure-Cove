/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

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
    
    public void displayMap()
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
    
    public void switchMap(String mapName, Vector2D pos_)
    {
        for (Map map : maps)
            if (map.name == mapName)
            {
                currentMap = map;
                player.setPosition(pos_);
                return;
            }
    }
}
