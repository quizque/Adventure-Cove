/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;


public class Map {
    public char[][] displayMap; // What the user sees
    public int size_x, size_y; // How big the map is
    public String name = "INVALID"; // Whats the name of the map
    public Event[] events; // What events are on the map
    public char[] collChars; // What can the player not hit?
    public Vector2D playerStart = new Vector2D(0,0); // Where does the player start
    public String startingIntructions = ""; // Does the player even start here?
    
    // Return cloned map for displaying and preventing mods to the original
    public char[][] getMap() { return displayMap.clone(); }
    
    // Return the name of the map
    public String getName() { if (name == null) {return "INVALID";} else { return name; }}
    
    // Return what the char is at the given position
    public char getCharAtPos(Vector2D pos_) { return displayMap[pos_.getX()][pos_.getY()]; }
}
