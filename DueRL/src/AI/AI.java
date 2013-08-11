/*
 * Ai module.  Responsible for making decisions for enemies.
 */
package AI;

import World.Creature;
import java.util.Random;

/**
 *
 * @author eniirane
 */
public class AI {
    private Creature self;
    private Random random;
    
    int protagonistX = 1;
    int protagonistY = 1;
    
    public AI(Creature self) {
        this.self = self;
        this.random = new Random();
    }
    
    public void step() {
        // this is the "okay, let's make a move" -method.  TODO etc.

        // Checking protagonist coordinates to make code a bit cleaner
        protagonistX = self.getWorld().getProtagonist().getX();
        protagonistY = self.getWorld().getProtagonist().getY();

        // First decide whether to move or attack
        
        if (self.isStunned()) {
            moveRandomly();
        } else {
            
            // If the target is close enough, attack those coordinates
            if (canAttack(protagonistX, protagonistY)) {
                self.attack();
            // Else if we're not that far, move cautiously    
            } else if (self.getDistance(protagonistX, protagonistY) > self.getWeapon().getMaxRange() 
                    &&self.getDistance(protagonistX, protagonistY) < self.getWeapon().getMaxRange() + 2) {
                moveCautiously();
            // Else just run at the protagonist     
            } else {
                moveGreedily();
            }
        }
        // if stunned, moveRandomly()
        // if full health and short range weapons, moveGreedy()
        // if full health and long range weapons, moveCautiously()
        // if low health, escape()
        
        
    }
    
    public void moveRandomly() {
        // Used for testing and for confused -effects?
        int x = (random.nextInt(3) - 1);
        int y = (random.nextInt(3) - 1);
        self.move(x, y);
        System.out.println("Enemy is stunned.");        
    }
    
    public void moveGreedily() {
        // move straight towards
        // i'll implement some smarter pathfinding once the arena has obstacles :D
        int x = 0;
        int y = 0;
        
        if (self.getX() < protagonistX)  {
            x = 1;
        } else if (self.getX() > protagonistX) {
            x = -1;
        }

        if (self.getY() < protagonistY)  {
            y = 1;
        } else if (self.getY() > protagonistY) {
            y = -1;
        }
        
        self.move(x, y);
        System.out.println("Enemy runs towards you.");    
    }
    
    public void moveCautiously() {
        // move one tile away from range to counterpunch
        System.out.println("Enemy is cautious.");
    }
    
    public void escape() {
        // move away
        System.out.println("Enemy is escaping.");
    }
    
    public boolean canAttack(int x, int y) {
        if (self.getWeapon().getMaxRange() >= self.getDistance(protagonistX, protagonistY)
                && self.getWeapon().getMinRange() <= self.getDistance(protagonistX, protagonistY)) {
            System.out.println("Enemy is in range!");
            return true;
        } else {
            System.out.println("Enemy is not in range.");
            return false;
        }
    }
}
