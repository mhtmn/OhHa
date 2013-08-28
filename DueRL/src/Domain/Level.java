package Domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Level is the playfield in which the action takes place.
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
    private World world;
    private char[][] level;
    private Random random;

    public Level(World world, int level) {
        this.world = world;
        this.worldSize = world.getSize();
        this.protagonist = world.getProtagonist();
        this.antagonists = new ArrayList<Creature>();
        this.random = new Random();

        buildWorld();
        populate(level);
    }

    /**
     * Building world geometry. . is interpreted as floor, # as wall.
     */
    private void buildWorld() {
        System.out.println("Building world...");
        level = new char[worldSize][worldSize];

        this.drawFloor();

        this.drawWalls();        
    }
    
    /**
     * Populate the playfield with 1 protagonist (player) and a number of
     * antagonist characters.
     * @param int numberOfEnemies controls the amount of enemies appropriate for
     * the created level.
     */
    private void populate(int numberOfEnemies) {
        System.out.println("Populating a " + worldSize + "x" + worldSize + " level with " + numberOfEnemies + " enemies...");

        // Place the player on map
        this.protagonist.setCoordinates(( random.nextInt(worldSize - 2) + 1 ), ( random.nextInt(worldSize - 2) + 1 ));

        // Creating the opponent(s).
        for (int i = 0;i < numberOfEnemies;i++) {
            this.antagonists.add(new Creature(this.world, ( random.nextInt(worldSize - 2) + 1 ), 
                                                          ( random.nextInt(worldSize - 2) + 1 ), "Antagonist"));
        }
        
        for (Creature enemy : antagonists) {
            enemy.makeSentient();
            enemy.createName();
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

    private void drawWalls() {
        // Setting walls to outer limits of the world.
        for (int i = 0; i < world.getSize(); i++) {
            level[i][0] = obstacleIcon;
            level[i][worldSize - 1] = obstacleIcon;
            level[0][i] = obstacleIcon;
            level[worldSize - 1][i] = obstacleIcon;
        }        
    }
    /**
     * When enemies are killed, this function creates a way out of current level.
     */
    public void createExit() {
        this.exitExists = true;
        this.exitX = random.nextInt(worldSize-2)+1;
        this.exitY = random.nextInt(worldSize-2)+1;
        world.report("(Press enter on stairs to continue)");
        world.report("Stairs have emerged!");
    }
    
    /**
     * Getter for whether an exit exists.
     * @return boolean
     */
    public boolean exitExists() {
        return this.exitExists;
    }

    /**
     * Creates a representation of the level for UI
     */
    public void packWorld() {
        this.drawFloor();
        this.drawWalls();

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
    
    public ArrayList<Creature> getAntagonists() {
        return this.antagonists;
    }
    
    /**
     * Method for determining if a tile is walkable.
     * @return boolean
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

    /**
     * Method for figuring out if given coordinates are inside the level 
     * @param x
     * @param y
     * @return boolean
     */
    public boolean contains(int x, int y) {
        if ((x >= 0 && y >= 0) && (x <= this.worldSize-1 && y <= this.worldSize-1)) {
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
   
