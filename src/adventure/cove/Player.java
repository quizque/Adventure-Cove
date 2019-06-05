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
    public int pos_x, pos_y;
    public int hit_points;
    public int mana_points;
    
    public Player(int x, int y, int hp, int mana) 
    {
        pos_x = x;
        pos_y = y;
        hit_points = hp;
        mana_points = mana;
    }
}
