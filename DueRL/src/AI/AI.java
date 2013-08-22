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
        // Checking protagonist coordinates to make code a bit cleaner
        protagonistX = self.getWorld().getProtagonist().getX();
        protagonistY = self.getWorld().getProtagonist().getY();

        if (self.isAlive()) {

            // First decide whether to move or attack
            if (self.isStunned()) {
                moveRandomly();
                self.getWorld().report("Enemy is stunned.");        
            } else if (self.getHealth() < 20) {
                if (Math.random() < 0.5) {
                    escape();
                    if (Math.random() < 0.33) {
                        self.getWorld().report("Enemy is escaping.");                        
                    }
                } else {
                    moveRandomly();
                }
            } else {

                // If the target is close enough, attack those coordinates
                if (canAttack(protagonistX, protagonistY)) {
                    self.attack();

                    // Else if we're not that far, move cautiously
                } else if (self.getDistance(protagonistX, protagonistY) > self.getWeapon().getMaxRange()
                        && self.getDistance(protagonistX, protagonistY) < self.getWeapon().getMaxRange() + 1) {
                    // bad fudge)
                    if (random.nextInt(3) == 0) {
                        self.getWorld().report("Enemy runs towards you.");
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

    public void moveRandomly() {
        // Used for stuns
        int x = (random.nextInt(3) - 1);
        int y = (random.nextInt(3) - 1);
        self.move(x, y);
    }

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

    public void moveCautiously() {
        // stay one tile away from range to counterpunch
        self.getWorld().report("Enemy is cautious.");
    }

    public void escape() {
        // move away
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

    public boolean canAttack(int x, int y) {
        if (self.getWeapon().getMaxRange() >= self.getDistance(protagonistX, protagonistY)
                && self.getWeapon().getMinRange() <= self.getDistance(protagonistX, protagonistY)) {
            return true;
        } else {
            return false;
        }
    }
}
