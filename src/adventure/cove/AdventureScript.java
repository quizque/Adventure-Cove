/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdventureScript {
    
    AdventureScript ()
    {
        
    }
    
    public void parseScript(String scriptLocation) throws FileNotFoundException, IOException
    {
        BufferedReader script = new BufferedReader(new FileReader(scriptLocation));
        
        String line;
        while ((line = script.readLine()) != null)
        {
            System.out.println(line);
        }
    }
    
}
