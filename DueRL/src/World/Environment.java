/**
 * Creating the playfield.
 */
package World;

import java.util.ArrayList;
import java.util.Random;
import UI.CombatLog;

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

    /** 
     * Building world geometry.  . is interpreted as floor, # as wall.
     */
    private void buildWorld() {
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

    /**
     * Populate the playfield with 1 protagonist (player) and a number of antagonist characters.
     */
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

    /**
     * Helper for making a walkable floor.
     */
    private void drawFloor() {
        for (int i = 1; i < worldSize - 1; i++) {
            for (int j = 1; j < worldSize - 1; j++) {
                world[i][j] = floorIcon;
            }
        }
    }

    /**
     * updater function for world
     */
    public void update() {

        this.drawFloor();

        if (this.protagonist.isAlive()) {
            // enemy moving
            if (!protagonist.isTargeting()) {
                for (Creature enemy : antagonists) {
                    enemy.getAI().step();
                }
            }

            // player attacks 
            if (protagonist.getAttackStatus()) {
                for (Item i : protagonist.getWeapons()) {
                    protagonist.calculateDamage(i);
                }
                protagonist.clearAttack();
            }

            // enemy attacks
            for (Creature enemy : antagonists) {
                if (enemy.getAttackStatus()) {
                    for (Item i : enemy.getWeapons()) {
                        enemy.calculateDamage(i);
                    }
                    enemy.clearAttack();
                }
            }
        }
        
        // Player and enemy bleeds and stuns
        if (protagonist.getBleedingStatus() && !protagonist.isTargeting()) {
            protagonist.bleed();
            if (Math.random() < 0.33) {
                protagonist.setBleed(false);
            }
        }
        
        for (Creature enemy : antagonists) {
            if (enemy.getBleedingStatus() && !protagonist.isTargeting()) {
                enemy.bleed();
            }
            if (Math.random() < 0.33) {
                enemy.setBleed(false);
            }
        }
        
        if (protagonist.getStunnedStatus() && !protagonist.isTargeting()) {
            if (Math.random() < 0.33) {
                protagonist.setStun(false);
                report(protagonist.getName() + " is no longer stunned.");
            }
        }

        for (Creature enemy : antagonists) {
            if (enemy.getStunnedStatus() && !protagonist.isTargeting()) {
                if (Math.random() < 0.33) {
                    enemy.setStun(false);
                    report(enemy.getName() + " is no longer stunned.");
                }
            }
        }

        // smooshing the character and enemy icons into the world
        for (Creature enemy : antagonists) {
            world[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }

        world[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();


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
    
    /**
     * Method for determining if a tile is walkable.
     */
    public boolean isFree(int x, int y) {
        if (x <= 0 || y <= 0 || x > this.worldSize - 1 || y > this.worldSize - 1) {
            return false;
        } else if (this.world[x][y] == floorIcon
                || this.world[x][y] == '%') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper function for adding messages to the combat log.
     */
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
