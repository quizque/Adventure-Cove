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
    
    
    Vector2D() { x = 0; y = 0; prev_x = x; prev_y = y; }
    Vector2D(int x_, int y_) { x = x_; y = y_; prev_x = x; prev_y = y; }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public void setX(int x_) { x = x_; }
    public void setY(int y_) { y = y_; }
    public void set(int x_, int y_) { x = x_; y = y_; }
}
