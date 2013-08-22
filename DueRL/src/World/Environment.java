/*
 * Creating the playfield.
 */
package World;

import java.util.ArrayList;
import java.util.Random;
import UI.CombatLog;

/**
 *
 * @author eniirane
 */
public class Environment {

    private char obstacleIcon = '#';
    private char floorIcon = '.';
    private String obstacleDescription = "This is a wall.  You cannot pass it.";
    private String floorDescription = "You see just smooth floor.";
    private Creature protagonist;
    private ArrayList<Creature> antagonists;
    private int worldSize = 20;
    private char[][] world;
    private CombatLog combatLog;
    private Random random;

    public Environment() {
        this.random = new Random();
        this.antagonists = new ArrayList<Creature>();
        this.combatLog = new CombatLog();
        buildWorld();
        populate();
    }

    private void buildWorld() {
        /* 
         * Building world geometry.  . is interpreted as floor, # as wall.
         */
        System.out.println("Building world...");
        world = new char[worldSize][worldSize];

        this.drawFloor();

        // Setting walls to outer limits of the world.
        for (int i = 0; i < worldSize; i++) {
            world[i][0] = obstacleIcon;
            world[i][worldSize - 1] = obstacleIcon;
            world[0][i] = obstacleIcon;
            world[worldSize - 1][i] = obstacleIcon;
        }
    }

    private void populate() {
        System.out.println("Populating world...");

        // Creating the player character
        this.protagonist = new Creature(this, random.nextInt(worldSize - 2) + 1, random.nextInt(worldSize - 2) + 1, "Protagonist");
        this.protagonist.setAIStatus(false);
        //this.protagonist.setHeroMode();
        this.protagonist.setIcon('@');

        // Creating the opponent(s).
        this.antagonists.add(new Creature(this, random.nextInt(worldSize - 2) + 1, random.nextInt(worldSize - 2) + 1, "Antagonist"));
        for (Creature enemy : antagonists) {
            enemy.makeSentient();
        }
    }

    private void drawFloor() {
        for (int i = 1; i < worldSize - 1; i++) {
            for (int j = 1; j < worldSize - 1; j++) {
                world[i][j] = floorIcon;
            }
        }
    }

    public void update() {
        /*
         * updater function for world
         */

        this.drawFloor();

        if (this.protagonist.isAlive()) {

            // enemy moving
            if (!protagonist.isTargeting()) {
                for (Creature enemy : antagonists) {
                    enemy.getAI().step();
                }
            }

            // player attack here
            if (protagonist.getAttackStatus()) {
                protagonist.calculateDamage();
                protagonist.clearAttack();
            }

            // enemy attack here
            for (Creature enemy : antagonists) {
                if (enemy.getAttackStatus()) {
                    enemy.calculateDamage();
                    enemy.clearAttack();
                }
            }
        }

        // smooshing the character and enemy icons into the world
        world[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();

        for (Creature enemy : antagonists) {
            world[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }

        if (protagonist.isTargeting()) {
            world[protagonist.getTargetX()][protagonist.getTargetY()] = 'X';
        }
    }

    // getters    
    public Creature getProtagonist() {
        return this.protagonist;
    }

    public ArrayList<Creature> getAntagonists() {
        return this.antagonists;
    }

    public int getSize() {
        return this.worldSize;
    }

    public boolean isFree(int x, int y) {
        if (x <= 0 || y <= 0 || x > this.worldSize - 1 || y > this.worldSize - 1) {
            return false;
        } else if (this.world[x][y] == floorIcon) {
            return true;
        } else {
            return false;
        }
    }

    public void report(String string) {
        combatLog.add(string);
    }

    public CombatLog getCombatLog() {
        return this.combatLog;
    }

    @Override
    public String toString() {
        String returnString = "";

        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                returnString += "" + this.world[i][j];
            }
            returnString += "\n";
        }

        return returnString;
    }
}
