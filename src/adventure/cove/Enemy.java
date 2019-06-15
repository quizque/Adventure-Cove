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
public class Enemy {
    public char[][] displayEnemy;
    public int display_x = 0;
    public int display_y = 0;
    
    public int enemyHealth;
    public String enemyName;
    public int enemyDamage;
    public Boolean randomSpawn = false;
    
    public int enemyHealth_old;
    
    public void store()
    {
        enemyHealth_old = enemyHealth;
    }
    
    public void restore()
    {
        enemyHealth = enemyHealth_old;
    }
}
