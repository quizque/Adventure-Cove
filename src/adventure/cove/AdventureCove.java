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
                                       "#      /|--------|\\              /|--------|\\      #\n" +
                                       "#     | |        | |            | |        | |     #\n" +
                                       "#     | |        | |            | |        | |     #\n" +
                                       "#     | |--------| |            | |--------| |     #\n" +
                                       "#     |/----------\\|            |/----------\\|     #\n" +
                                       "#     |  _         |            |  _         |     #\n" +
                                       "#     | | |  |¯|   |            | | |  |¯|   |     #\n" +
                                       "#     |------|-|---|            |------|-|---|     #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "#                                                  #\n" +
                                       "####################################################\n");
        
        World ROUTE_1 = new World("######     ##############################\n" +
                                  "#              wwwwwwwwwwwwwwwwwwwwwwwww#\n" +
                                  "#                                       #\n" +
                                  "#wwwwwwwwwwwwwwwww                      #\n" +
                                  "############################        #####\n" +
                                  "#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#\n" +
                                  "#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#\n" +
                                  "#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#\n" +
                                  "#wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww#\n" +
                                  "###      ################################\n" +
                                  "#                                       #\n" +
                                  "#           www           wwwww         #\n" +
                                  "#                        wwwwwwww       #\n" +
                                  "#   wwwww                   wwww        #\n" +
                                  "#   wwwwww                              #\n" +
                                  "# wwwwwwwww                             #\n" +
                                  "#                                       #\n" +
                                  "##################     ##################\n");
        
        Player player = new Player(27, 1, 100, 50, "TOWN_OUTSIDE", TOWN_OUTSIDE);
        
        while (player.hit_points > 0)
        {
            // Get user input
            char action = scan.next().toLowerCase().charAt(0);
            
            // Move the character and
            // set the position of the @ on the map.
            // Also test for event locations on the map.
            String returnVal = player.MovePlayer(action);
            
            // If move player requested an action...
            
            
            if ("CHANGE_LOCATION" == returnVal)
                switch (player.location)
                {
                    case "TOWN_INSIDE":
                        player.map = TOWN_INSIDE;
                        player.pos_x = 18;
                        player.pos_y = 6;
                        player.map.world[player.pos_x][player.pos_y] = '@';
                        break;
                    case "TOWN_OUTSIDE":
                        player.map.world[player.pos_x][player.pos_y-1] = ' ';
                        player.map = TOWN_OUTSIDE;
                        player.pos_x = 14;
                        player.pos_y = 11;
                        player.map.world[player.pos_x][player.pos_y] = '@';
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
