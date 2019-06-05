/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

public class AdventureCove {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Player player = new Player(0, 0, 100, 50);
        
        World town = new World("xxxxxxxxxx\n" +
                               "uuuuuuuuuu\n" +
                               "          \n" +
                               "^^^^^^^^^^\n" +
                               "          \n");
    }
    
}
