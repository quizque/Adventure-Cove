/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdventureScript {
    
    AdventureScript ()
    {
        
    }
    
    public void parseScript(String scriptLocation) throws FileNotFoundException, IOException
    {
        BufferedReader script = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(scriptLocation)));
        
        String line;
        while ((line = script.readLine()) != null)
        {
            if ("".equals(line.trim()))
                continue;
            
            System.out.println(line);
        }
    }
    
}
