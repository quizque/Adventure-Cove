/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.cove;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nickc
 */
public class BattleManager {
    Enemy[] enemys;
    
    public void triggerRandomBattle(Player ply) throws IOException
    {
        Enemy battleEnemy = enemys[(int)(Math.random() * enemys.length)];
        battleEnemy.store();
        Scanner sc = new Scanner(System.in);
        
        while (battleEnemy.enemyHealth >= 1)
        {
            clearScreen();
            displayEnemy(battleEnemy);
            displayInfo(battleEnemy);
            
            System.out.print("What do you want to do? ([A]TTACK/[H]EAL/[R]UN) ");
            char action = sc.next().toUpperCase().charAt(0);
            
            Boolean quit = processAction(action, battleEnemy, ply, sc);
            
            if (quit)
                break;
        }
        
        if (battleEnemy.enemyHealth <= 0)
            System.out.println("You defeated the enemy!\nType anything and press enter to continue...");
        else
            System.out.println("You ran away!\nType anything and press enter to continue...");
        
        battleEnemy.restore();
        sc.next();
    }
    
    private Boolean processAction(char action, Enemy em, Player ply, Scanner sc)
    {
        switch (action)
        {
            case 'A':
                int dmg = (int)((Math.random()*5) + 5);
                em.enemyHealth -= dmg;
                System.out.println("You hit the enemy for " + dmg + "!");
                break;
                
            case 'H':
                int heal = (int)((Math.random()*3) + (em.enemyDamage/2));
                ply.addHP(heal);
                System.out.println("You healed for " + heal + "!");
                break;
                
            case 'R':
                if (Math.random() >= 0.90 && em.randomSpawn)
                    return true;
                else
                    System.out.println("You failed to escape!");
                break;
        }
        
        if (em.enemyHealth <= 0)
            return true;
        
        int enemyDmg = em.enemyDamage + (int)(Math.random()*2);
        ply.subHP(enemyDmg);
        System.out.println("The enemy did " + enemyDmg + " to you!");
        
        // Weird java stuff again
        System.out.println("Type ANYTHING and press enter to continue...");
        sc.next();
        
        return false;
    }
    
    private void clearScreen()
    {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    private void displayInfo(Enemy enm)
    {
        String data = buildEnemyData(enm);
        
        System.out.print(' ');
        for (int i = 0; i != data.length()+2; i++)
            System.out.print('-');
        System.out.print('\n');
        
        System.out.print("| " + data + " |\n");
        
        System.out.print(' ');
        for (int i = 0; i != data.length()+2; i++)
            System.out.print('-');
        System.out.print('\n');
    }
    
    private String buildEnemyData(Enemy enm)
    {
        return "HP: " + enm.enemyHealth + "     MAX DMG: " + enm.enemyDamage + "     NAME: " + enm.enemyName;
    }
    
    private void displayEnemy(Enemy enm)
    {
        for (int y = 0; y != enm.display_y; y++)
        {
            for (int x = 0; x != enm.display_x; x++)
                    System.out.print(enm.displayEnemy[x][y]);
            System.out.print('\n');
        }
                    
    }
}
