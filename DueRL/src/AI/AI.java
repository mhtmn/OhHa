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
    
    public AI(Creature self) {
        this.self = self;
        this.random = new Random();
    }
    
    public void step() {
        // this is the "okay, let's make a move" -method.  TODO etc.

        moveGreedily();

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
    }
    
    public void moveGreedily() {
        // move straight towards
        // i'll implement some smarter pathfinding once the arena has obstacles :D
        int x = 0;
        int y = 0;
        
        if (self.getX() < self.getWorld().getProtagonist().getX())  {
            x = 1;
        } else if (self.getX() > self.getWorld().getProtagonist().getX()) {
            x = -1;
        }

        if (self.getY() < self.getWorld().getProtagonist().getY())  {
            y = 1;
        } else if (self.getY() > self.getWorld().getProtagonist().getY()) {
            y = -1;
        }
        
        self.move(x, y);
    
    }
    
    public void moveCautiously() {
        // move one tile away from range to counterpunch
    }
    
    public void escape() {
        // move away
    }
}
