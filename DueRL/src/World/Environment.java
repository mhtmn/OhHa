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
    private char exit = '>';
    
    private boolean exitExists = false;
    private int exitX;
    private int exitY;
    
    private Creature protagonist;
    private ArrayList<Creature> antagonists;
    private int worldSize;
    private char[][] world;
    private CombatLog combatLog;
    private Random random;

    public Environment(int level, int worldSize) {
        this.random = new Random();
        this.antagonists = new ArrayList<Creature>();
        this.combatLog = new CombatLog();
        this.worldSize = worldSize;
        buildWorld();
        populate(level);        
    }

    /**
     * Building world geometry. . is interpreted as floor, # as wall.
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
     * Populate the playfield with 1 protagonist (player) and a number of
     * antagonist characters.
     */
    private void populate(int numberOfEnemies) {
        System.out.println("Populating a " + worldSize + "x" + worldSize + " world with " + numberOfEnemies + " enemies...");

        // Creating the player character
        this.protagonist = new Creature(this, ( random.nextInt(worldSize - 2) + 1 ), 
                                              ( random.nextInt(worldSize - 2) + 1 ), "Protagonist");
        this.protagonist.setAIStatus(false);
        //this.protagonist.setHeroMode();
        this.protagonist.setIcon('@');

        // Creating the opponent(s).
        for (int i = 0;i < numberOfEnemies;i++) {
            this.antagonists.add(new Creature(this, random.nextInt(worldSize - 2) + 1, random.nextInt(worldSize - 2) + 1, "Antagonist"));
        }
        
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

        // if all enemies are dead, create exit
        for (Creature enemy : antagonists) {
            if (enemy.isAlive()) {
                break;
            } else if (!this.exitExists) {
                createExit();
            }
        }
        
        // enemy moving
        if (!protagonist.isTargeting()) {
            for (Creature enemy : antagonists) {
                enemy.getAI().step();
            }
        }

        if (this.protagonist.isAlive()) {

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
        checkDebuffs(protagonist);

        for (Creature enemy : antagonists) {
            checkDebuffs(enemy);
        }

        // smooshing the character and enemy icons into the world
        packWorld();
    }
    
    public void createExit() {
        this.exitExists = true;
        this.exitX = 5;
        this.exitY = 5;
        report("(Press enter on stairs to continue)");
        report("Stairs have emerged!");
    }

    public void packWorld() {
        this.drawFloor();

        for (Creature enemy : antagonists) {
            world[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }

        if (protagonist.isTargeting()) {
            if (protagonist.isInRange(protagonist.getTargetX(), protagonist.getTargetY())) {
                world[protagonist.getTargetX()][protagonist.getTargetY()] = 'X';
            } else {
                world[protagonist.getTargetX()][protagonist.getTargetY()] = 'x';
            }
        }
        
        if (exitExists) {
            world[exitX][exitY] = exit;
        }

        world[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();
        
    }

    public void checkDebuffs(Creature creature) {
        if (creature.getBleedingStatus() && !protagonist.isTargeting()) {
            creature.bleed();
            if (Math.random() < 0.33) {
                creature.setBleed(false);
            }
        }

        if (creature.getStunnedStatus() && !protagonist.isTargeting()) {
            if (Math.random() < 0.33) {
                creature.setStun(false);
                report(creature.getName() + " is no longer stunned.");
            }
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

    public int getExitX() {
        return this.exitX;
    }
    
    public int getExitY() {
        return this.exitY;
    }
    
    /**
     * Method for determining if a tile is walkable.
     */
    public boolean isFree(int x, int y) {
        if (x <= 0 || y <= 0 || x > this.worldSize - 1 || y > this.worldSize - 1) {
            return false;
        } else if (this.world[x][y] == floorIcon
                || this.world[x][y] == '%'
                || this.world[x][y] == exit) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(int x, int y) {
        if ((x >= 0 && y >= 0) && (x <= this.worldSize && y <= this.worldSize)) {
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
