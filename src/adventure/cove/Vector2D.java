/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

/**
 *
 * @author nickc
 */
public class Vector2D {
    private int x, y, prev_x, prev_y;
    
    // Constructers
    Vector2D() { x = 0; y = 0; prev_x = x; prev_y = y; }
    Vector2D(int x_, int y_) { x = x_; y = y_; prev_x = x; prev_y = y; }
    
    // Gets
    public int getX() { return x; }
    public int getY() { return y; }
    public int getPrevX() { return prev_x; }
    public int getPrevY() { return prev_y; }
    
    // Sets
    public void setX(int x_) { prev_y = y; x = x_; }
    public void setY(int y_) { prev_x = x; y = y_; }
    public void set(int x_, int y_) { prev_x = x; prev_y = y; x = x_; y = y_; }
    public void restoreXY() { x = prev_x; y = prev_y; }
    
    // Adds
    public void addX(int x_) { prev_x = x; prev_y = y; x += x_; }
    public void addY(int y_) { prev_x = x; prev_y = y; y += y_; }
}
