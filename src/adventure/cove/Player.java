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
public class Player {
    public int pos_x, pos_y;
    public int prev_x, prev_y;
    public int hit_points;
    public int mana_points;
    public String location;
    public World map;
    
    public Player(int x, int y, int hp, int mana, String location_, World map_) 
    {
        pos_x = x;
        pos_y = y;
        hit_points = hp;
        mana_points = mana;
        location = location_;
        map = map_;
    }
    
    public String MovePlayer(char dir)
    {
        prev_x = pos_x;
        prev_y = pos_y;
        
        ///
        /// MOVE POSITION
        ///
        
        switch (dir)
        {
            case 'w':
                pos_y--;
                break;
            case 'a':
                pos_x--;
                break;
            case 's':
                pos_y++;
                break;
            case 'd':
                pos_x++;
                break;
            default:
                break;
        }
        
        
        ///
        /// CHECK FOR MAP EVENT
        ///
        
        // TOWN_OUTSIDE
        System.out.println(pos_x + " " + pos_y);
        
        if ("TOWN_OUTSIDE" == location)
            if (pos_x == 14 && pos_y == 10)
            {
                location = "TOWN_INSIDE";
                return "CHANGE_LOCATION";
            }
            else if (pos_x >= 21 && pos_x <= 30)
            {
                location = "ROUTE_1";
                return "CHANGE_LOCATION";
            }
        
        // TOWN_INSIDE
        if ("TOWN_INSIDE" == location)
            if (pos_x >= 17 && pos_x <= 19 && pos_y == 7)
            {
                location = "TOWN_OUTSIDE_DOOR";
                return "CHANGE_LOCATION";
            }
        
        // ROUTE_1
        if ("ROUTE_1" == location)
            if (pos_x >= 18 && pos_y <= 22 && pos_y == 17)
            {
                location = "TOWN_OUTSIDE";
                return "CHANGE_LOCATION";
            }
        
        
        ///
        /// CHECK FOR WALL
        ///
        
        char[] collArr = {'#', '/', '-', '\\', '|', '_'};
        
        if (map.world[pos_x][pos_y] != ' ')
        {
            Boolean hit = false;
            for (char coll : collArr)
                if (map.world[pos_x][pos_y] == coll)
                {
                    hit = true;
                    pos_x = prev_x;
                    pos_y = prev_y;
                }
            if (!hit)
            {
                map.world[pos_x][pos_y] = '@';
                map.world[prev_x][prev_y] = map.world_bak[prev_x][prev_y];
            }
        } else {
            map.world[pos_x][pos_y] = '@';
            map.world[prev_x][prev_y] = ' ';
        }

        return "";
    }
}
