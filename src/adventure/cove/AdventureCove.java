/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

import java.io.IOException;

public class AdventureCove {

    
    public static void main(String[] args) throws IOException 
    {
        // Make a new script handler
        AdventureScript script = new AdventureScript();
        
        //
        // PRINT INFO SECTION
        //
        System.out.println(" ---------------------------\n" +
                           "| WELCOME TO ADVENTURE COVE |\n" +
                           " ---------------------------\n" +
                           " Press enter to continue...");
        System.in.read();
        
        System.out.println(" --------------\n" +
                           "| INSTRUCTIONS |\n" +
                           " --------------\n" +
                           " 1. Use the WASD keys to control\n" +
                           "	- Enter in W(LEFT), A(LEFT), S(DOWN), or D(RIGHT), and then\n" +
                           "	  press the ENTER key!\n" +
                           " 2. You can interact with some objects! Try walking into them.\n" +
                           " 3. Only press WASD and enter one at a time. Combo moves can be made but you miss a lot of the game\n" +
                           " 4. The goal of the game is to make it to the end and beat the boss!\n" +
                           " 5. YOU ARE THE @ CHARACTER ON THE MAP!" +
                           " Press enter to continue...");
        System.in.read();
        
        System.out.println(" ------------\n" +
                           "| EXTRA INFO |\n" +
                           " ------------\n" +
                           " Creator: Nick Coombe\n" +
                           " Class Code: ICS3U\n" +
                           " Completion Date: 2019-06-16\n" +
                           " Press enter to continue...");
        System.in.read();
        
        System.out.println(" -------------\n" +
                           "| PLEASE NOTE |\n" +
                           " -------------\n" +
                           " This program has its OWN PROGRAMMING LANGUAGE!\n" +
                           " In an atempt to make the game more easy to edit\n" +
                           " I added the ability to program your own game in\n" +
                           " the language I made called AdventureScript (.ads)\n" +
                           " There is one AdventureScript built in which is what\n" +
                           " you will be playing but feel free to check out \"Adventure1.aes\"\n" +
                           " and try to make your own!\n" +
                           " Press enter to continue...");
        System.in.read();
        //
        // END PRINT INFO SECTION
        //
        
        // Parse internal script
        GameManager manager = script.parseScript("Adventure1.ads");
        
        // Start the game!
        manager.startGame();
        
    }
    
}
