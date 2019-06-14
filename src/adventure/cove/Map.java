/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;


public class Map {
    public char[][] displayMap;
    public int size_x, size_y;
    public String name = "INVALID";
    public Event[] events;
    public char[] collChars;
    public Vector2D playerStart = new Vector2D(0,0);
    public String startingIntructions = "";
    
    public char[][] getMap() { return displayMap.clone(); }
    public String getName() { if (name == null) {return "INVALID";} else { return name; }}
    public char getCharAtPos(Vector2D pos_) { return displayMap[pos_.getX()][pos_.getY()]; }
}
