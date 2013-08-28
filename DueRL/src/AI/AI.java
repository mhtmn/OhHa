package AI;

import Domain.Creature;

import java.util.Random;

/**
 * Ai module. Responsible for making decisions for enemies.
 * @author eniirane
 */
public class AI {

    private Creature self;
    private Random random;
    private int protagonistX = 1;
    private int protagonistY = 1;
    private int previousMoveX = 0;
    private int previousMoveY = 0;

    public AI(Creature self) {
        this.self = self;
        this.random = new Random();
    }

    /**
     * This method is for taking one turn and deciding what to do with it
     */
    public void step() {
        Creature protagonist = self.getWorld().getProtagonist();
        protagonistX = self.getWorld().getProtagonist().getX();
        protagonistY = self.getWorld().getProtagonist().getY();

        if (self.isAlive()) {


            // First we check if AI is panicking or stunned (and if player is alive) 
            if (!protagonist.isAlive()) {
                moveRandomly();

                // Condition for escaping is low hp, but there's a chance of resisting
                // Make sure we're not being cornered
            } else if (self.getX() < protagonistX && self.getX() < 4
                    || self.getX() > protagonistX && self.getX() > (self.getWorld().getSize() - 4)
                    || self.getY() < protagonistY && self.getY() < 4
                    || self.getY() > protagonistY && self.getY() > (self.getWorld().getSize() - 4)) {

                moveTowardsCenter();
            } else if (self.getEscaping()) {
                escape();
            } else if (self.getEscaping() && Math.random() < 0.25) {
                self.setEscaping(false);

                // if stunned, just stand there or stagger around aimlessly
            } else if (self.getStunnedStatus()) {
                if (Math.random() < 0.25) {
                    moveRandomly();
                    self.getWorld().report(self.getName() + " staggers.");                    
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
        updatePrevious(x, y);
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
        updatePrevious(x, y);
    }

    /**
     * Dummy-method for moving cautiously, waiting for an opportunity to
     * counterpunch.
     */
    public void moveCautiously() {
        self.getWorld().report(self.getName() + " is cautious.");
    }

    /**
     * Run away from the player.
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

        if (x == this.previousMoveX && y == previousMoveY) {
            if (Math.random() < 0.5) {
               x = random.nextInt(2) - 1;
            } else {
               y = random.nextInt(2) - 1;
            }
        }  
         
        self.move(x, y);
        updatePrevious(x, y);
    }

    /**
     * Move towards the center of the field
     */
    public void moveTowardsCenter() {
        int center = (self.getWorld().getSize() / 2);
        int x = 0;
        int y = 0;

        if (self.getX() < center) {
            x = 1;
        } else if (self.getX() > center) {
            x = -1;
        }

        if (self.getY() < center) {
            y = 1;
        } else if (self.getY() > center) {
            y = -1;
        }

        self.move(x, y);
        updatePrevious(x, y);
    }
    
    public void updatePrevious(int x, int y) {
        previousMoveX = x;
        previousMoveY = y;
    }

    /**
     * Checking whether an attack is possible.
     *
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
