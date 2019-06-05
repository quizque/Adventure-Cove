/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

import java.io.IOException;
import java.util.Scanner;

public class AdventureCove {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        Scanner scan = new Scanner (System.in);
        
        World TOWN_INSIDE = new World("#########################\n" +
                                      "#     |-----|           #\n" +
                                      "#-                 \\__/ #\n" +
                                      "#|    ______       |  | #\n" +
                                      "#|     |  |        |__| #\n" +
                                      "#-                 /  \\ #\n" +
                                      "#                       #\n" +
                                      "#################   #####\n");
        
        World TOWN_OUTSIDE = new World("#####################          #####################\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "#      /|--------|\\              /|¯¯¯¯¯¯¯¯|\\      #\n" +
                                       "#     | |        | |            | |        | |     #\n" +
                                       "#     | |        | |            | |        | |     #\n" +
                                       "#     | |--------| |            | |________| |     #\n" +
                                       "#     |/----------\\|            |/__________\\|     #\n" +
                                       "#     |  _         |            |  _         |     #\n" +
                                       "#     | | |  |¯|   |            | | |  |¯|   |     #\n" +
                                       "#     |------|-|---|            |------|-|---|     #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "####################################################\n");
        
        Player player = new Player(1, 1, 100, 50, "TOWN_INSIDE", TOWN_INSIDE);
        
        while (player.hit_points > 0)
        {
            char action = scan.next().toLowerCase().charAt(0);
            
            String returnVal = player.MovePlayer(action);
            
            if ("CHANGE_LOCATION" == returnVal);
                switch (player.location)
                {
                    case "TOWN_INSIDE":
                        System.out.println(returnVal + "<<<<");
                        player.map = TOWN_INSIDE;
                        player.pos_x = 18;
                        player.pos_y = 7;
                        player.map.world[player.pos_x][player.pos_y] = '@';
                        break;
                    default:
                        break;
                }
            
            
            for (int y = 0; y != player.map.size_y; y++)
            {
                for (int x = 0; x != player.map.size_x; x++)
                    System.out.print(player.map.world[x][y]);
                System.out.print('\n');
            }
            
            player.map.resetWorld();
        }
    }
    
}
