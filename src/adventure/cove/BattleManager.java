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
        while (!battleEnemy.randomSpawn || battleEnemy == null)
            battleEnemy = enemys[(int)(Math.random() * enemys.length)];
        
        battleEnemy.store();
        Scanner sc = new Scanner(System.in);
        
        clearScreen();
        System.out.println("Oh no! You ran into a " + battleEnemy.enemyName + "...\nType anything and press enter to continue...");
        sc.next();
        
        while (battleEnemy.enemyHealth > 0 || ply.getHP() > 0)
        {
            clearScreen();
            displayEnemy(battleEnemy);
            displayInfo(battleEnemy);
            
            System.out.print("What do you want to do? ([A]TTACK/[H]EAL/[R]UN) ");
            char action = sc.next().toUpperCase().charAt(0);
            
            Boolean quit = processAction(action, battleEnemy, ply, sc);
            
            if (quit)
                break;
            
            if (ply.getHP() < 1)
            {
                System.out.println("| You died to the boss and lost the game!\n\n" +
                "| Thank you for playing my game, if you want more you can make your own\n" +
                "| scripts and run them inside the program at the start!\n" +
                "| Checkout the github at https://github.com/nickthegamer5/Adventure-Cove for more details about scripting.\n" +
                "\n| CREDITS:\n| Nick Coombe - Devloper\n| Colin Vanvervorf - Play Tester\n| Drew Purde - Play Tester\n\nPress any key and then enter to exit...");
                System.in.read();
                System.in.read();
                System.exit(0);
            }
        }
        
        if (battleEnemy.enemyHealth <= 0)
            System.out.println("You defeated the enemy!\nType anything and press enter to continue...");
        else
            System.out.println("You ran away!\nType anything and press enter to continue...");
        
        battleEnemy.restore();
        sc.next();
    }
    
    public Boolean triggerBattle(Player ply, String enemyName)
    {
        Enemy battleEnemy = enemys[0];
        for (Enemy enm : enemys)
            if (enm.enemyName.equals(enemyName))
                battleEnemy = enm;
        
        battleEnemy.store();
        Scanner sc = new Scanner(System.in);
        
        clearScreen();
        System.out.println("  ____   ____   _____ _____   ____       _______ _______ _      ______ _ \n" +
                           " |  _ \\ / __ \\ / ____/ ____| |  _ \\   /\\|__   __|__   __| |    |  ____| |\n" +
                           " | |_) | |  | | (___| (___   | |_) | /  \\  | |     | |  | |    | |__  | |\n" +
                           " |  _ <| |  | |\\___ \\\\___ \\  |  _ < / /\\ \\ | |     | |  | |    |  __| | |\n" +
                           " | |_) | |__| |____) |___) | | |_) / ____ \\| |     | |  | |____| |____|_|\n" +
                           " |____/ \\____/|_____/_____/  |____/_/    \\_\\_|     |_|  |______|______(_)");
        System.out.println("You have encoutered the boss! The battle is ON!!!\nPress any key and then enter to continue...");
        sc.next();
        
        while (battleEnemy.enemyHealth > 0 || ply.getHP() > 0)
        {
            clearScreen();
            displayEnemy(battleEnemy);
            displayInfo(battleEnemy);
            
            System.out.print("What do you want to do? ([A]TTACK/[H]EAL/[R]UN) ");
            char action = sc.next().toUpperCase().charAt(0);
            
            Boolean quit = processAction(action, battleEnemy, ply, sc);
            
            if (quit)
                break;
            
            if (ply.getHP() < 1)
                return true;
        }
        
        clearScreen();
        
        System.out.println("| You defeated the boss and won the game with " + ply.getHP() + " HP left!!!\n\n" +
                "| Thank you for playing my game, if you want more you can make your own\n" +
                "| scripts and run them inside the program at the start!\n" +
                "| Checkout the github at https://github.com/nickthegamer5/Adventure-Cove for more details about scripting.\n" +
                "\n| CREDITS:\n| Nick Coombe - Devloper\n| Colin Vanvervorf - Play Tester\n| Drew Purde - Play Tester\n\nPress any key and then enter to exit...");
        sc.next();
        System.exit(0);
        return false;
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
                if (Math.random() >= 0.90 && em.randomSpawn == true)
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
