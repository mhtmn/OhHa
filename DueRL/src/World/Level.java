/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author eniirane
 */
public class Level {
    private char obstacleIcon = '#';
    private char floorIcon = '.';
    private char exit = '>';
    
    private boolean exitExists = false;
    private int exitX;
    private int exitY;
    
    private Creature protagonist;
    private ArrayList<Creature> antagonists;

    private int worldSize;
    private Environment world;
    private char[][] level;
    private Random random;

    public Level(Environment world, int level) {
        this.worldSize = world.getSize();
        this.protagonist = world.getProtagonist();
        this.antagonists = new ArrayList<Creature>();
        
        populate(level);
    }
    
    /**
     * Populate the playfield with 1 protagonist (player) and a number of
     * antagonist characters.
     */
    private void populate(int numberOfEnemies) {
        System.out.println("Populating a " + worldSize + "x" + worldSize + " world with " + numberOfEnemies + " enemies...");

        // Place the player on map
        this.protagonist.setCoordinate(( random.nextInt(worldSize - 2) + 1 ), ( random.nextInt(worldSize - 2) + 1 ));

        // Creating the opponent(s).
        for (int i = 0;i < numberOfEnemies;i++) {
            this.antagonists.add(new Creature(this.world, ( random.nextInt(worldSize - 2) + 1 ), 
                                                    ( random.nextInt(worldSize - 2) + 1 ), "Antagonist"));
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
                level[i][j] = floorIcon;
            }
        }
    }
    
    public void createExit() {
        this.exitExists = true;
        this.exitX = 5;
        this.exitY = 5;
        world.report("(Press enter on stairs to continue)");
        world.report("Stairs have emerged!");
    }

    public void packWorld() {
        this.drawFloor();

        for (Creature enemy : antagonists) {
            level[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }

        if (protagonist.isTargeting()) {
            if (protagonist.isInRange(protagonist.getTargetX(), protagonist.getTargetY())) {
                level[protagonist.getTargetX()][protagonist.getTargetY()] = 'X';
            } else {
                level[protagonist.getTargetX()][protagonist.getTargetY()] = 'x';
            }
        }
        
        if (exitExists) {
            level[exitX][exitY] = exit;
        }

        level[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();
        
    }

    // getters    
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
        } else if (this.level[x][y] == floorIcon
                || this.level[x][y] == '%'
                || this.level[x][y] == exit) {
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

    @Override
    public String toString() {
        String returnString = "";

        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                returnString += "" + this.level[i][j];
            }
            returnString += "\n";
        }

        return returnString;
    }
}
   
