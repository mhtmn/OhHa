/*
 * Creating the playfield.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Environment {
    private String obstacleIcon        = "#";
    private String floorIcon           = ".";
    
    private String obstacleDescription = "This is a wall.  You cannot pass it.";
    private String floorDescription    = "You see just smooth floor.";

    private int worldSize = 20;
    
    private int[][] world;
    
    public Environment() {
        buildWorld();
        populate();
    }
    
    private void buildWorld() {
        /* 
         * Building world geometry.  0 is interpreted as floor, 1 as wall.
         */
        for (int i=0;i<worldSize;i++) {
            for (int j=0;i<worldSize;j++) {
                world[i][j] = 0;
            }
        }

        // Setting walls to outer limits of the world.
        for (int i=0;i<worldSize;i++) {
            world[i][0]  = 1;
            world[i][worldSize] = 1;
            world[0][i]  = 1;
            world[worldSize][i] = 1;
        }
    }
    
    private void populate() {
        Creature protagonist = new Creature(worldSize/4, worldSize/4, "protagonist");
        Creature antagonist = new Creature((worldSize/4)*3, (worldSize/4)*3, "antagonist");
    }
}
