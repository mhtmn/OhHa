/**
 * Ai module.  Responsible for making decisions for enemies.
 */
package AI;

import World.Creature;

import java.util.Random;

public class AI {

    private Creature self;
    private Random random;
    int protagonistX = 1;
    int protagonistY = 1;
    boolean escaping = false;

    public AI(Creature self) {
        this.self = self;
        this.random = new Random();
    }

    /**
     * This method is for taking one turn
     */
    public void step() {
        Creature protagonist = self.getWorld().getProtagonist();
        protagonistX = self.getWorld().getProtagonist().getX();
        protagonistY = self.getWorld().getProtagonist().getY();

        if (self.isAlive()) {
            
            
            // First we check if AI is panicking or stunned (and if player is alive) 
            if (!protagonist.isAlive()) {
                moveRandomly();
                
            } else if (self.getHealth() < 20) {
                self.getWorld().report(self.getName() + " is escaping.");                                        
                this.escaping = true;

            } else if (escaping && Math.random() < 0.25) {
                this.escaping = false;
                                
            } else if (self.getStunnedStatus()) {
                if (Math.random() < 0.33) {
                    moveRandomly();
                }
                self.getWorld().report(self.getName() + " appears to be stunned.");        

            } else if (escaping) {
                if (Math.random() < 0.5) {
                    escape();
                } else {
                    moveRandomly();
                }
                
            // if none of those is the case, we check for other possibilities 
            } else {
                // If the target is close enough, attack those coordinates
                if (canAttack(protagonistX, protagonistY)) {
                    self.attack();
                    
                // If we're too close, move away.
                } else if (self.getDistance(protagonistX, protagonistY) < self.getWeapons().get(0).getMinRange()) {
                    escape();
                    
                // If we're not that far, i.e. just outside opponent's range, move mostly cautiously or go for surprise attack
                } else if (self.getDistance(protagonistX, protagonistY) > protagonist.getWeapons().get(0).getMaxRange()
                        && self.getDistance(protagonistX, protagonistY) < protagonist.getWeapons().get(0).getMaxRange() + 1) {

                    if (Math.random() < 0.33) {
                        self.getWorld().report(self.getName() + " leaps towards you!");
                        moveGreedily();

                    } else {
                        moveCautiously();
                    }

                // Else just run at the protagonist
                } else {
                    moveGreedily();
                }
            }
        }
    }

    /**
     * Method for a random move.
     */
    public void moveRandomly() {
        int x = (random.nextInt(3) - 1);
        int y = (random.nextInt(3) - 1);
        self.move(x, y);
    }

    /**
     * Method for running towards the player.
     */
    public void moveGreedily() {
        // move straight towards
        // i'll implement some smarter pathfinding once the arena has obstacles :D
        int x = 0;
        int y = 0;

        if (self.getX() < protagonistX) {
            x = 1;
        } else if (self.getX() > protagonistX) {
            x = -1;
        }

        if (self.getY() < protagonistY) {
            y = 1;
        } else if (self.getY() > protagonistY) {
            y = -1;
        }

        
        
        self.move(x, y);
    }

    /**
     * Moving cautiously, waiting for an opportunity to counterpunch.
     */
    public void moveCautiously() {
        self.getWorld().report(self.getName() + " is cautious.");
    }

    /**
     * Run away from the player, scared.
     */
    public void escape() {
        int x = 0;
        int y = 0;

        if (self.getX() < protagonistX) {
            x = -1;
        } else if (self.getX() > protagonistX) {
            x = 1;
        }

        if (self.getY() < protagonistY) {
            y = -1;
        } else if (self.getY() > protagonistY) {
            y = 1;
        }

        self.move(x, y);
    }
    
    public void setEscaping(boolean flip) {
        this.escaping = flip;
    }

    /**
     * Checking whether an attack is possible.
     * @param x coordinate
     * @param y coordinate
     * @return boolean
     */
    public boolean canAttack(int x, int y) {
        if (self.getWeapons().get(0).getMaxRange() >= self.getDistance(protagonistX, protagonistY)
                && self.getWeapons().get(0).getMinRange() <= self.getDistance(protagonistX, protagonistY)) {
            return true;
        } else {
            return false;
        }
    }
}
