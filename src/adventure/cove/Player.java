/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

/**
 *
 * @author user
 */
public class Player {
    private Vector2D position = new Vector2D(1, 1);
    private int hitpoints = 100;
    
    public void moveForward() { position.addY(-1); }
    public void moveBackwords() { position.addY(1); }
    public void moveLeft() { position.addX(-1); }
    public void moveRight() { position.addX(1); }
    
    public int getHP() { return hitpoints; }
    public void addHP(int amount) { hitpoints += amount; }
    public void subHP(int amount) { hitpoints -= amount; }
    public void setPosition(Vector2D pos_) { position = pos_; }
    public void restorePrevPos() { position.restoreXY(); }
    public Vector2D getPosition() { return position; }
}
