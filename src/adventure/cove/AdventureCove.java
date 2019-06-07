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
        AdventureScript manager = new AdventureScript();
        manager.parseScript("Adventure1.as");
    }
    
}
