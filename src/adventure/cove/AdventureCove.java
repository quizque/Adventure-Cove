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
        
        // Parse internal script
        GameManager manager = script.parseScript("Adventure1.as");
        manager.displayMap();
        
    }
    
}
