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
        
    public Environment() {
        buildWorld();
        populate();
    }
    
    private void buildWorld() {
        /* 
         * Building world geometry.  0 is interpreted as floor, 1 as wall.
         */
        int[][] world = new int[worldSize][worldSize];
    
        for (int i=0;i<worldSize;i++) {
            for (int j=0;j<worldSize;j++) {
                world[i][j] = 0;
            }
        }
        
        // Setting walls to outer limits of the world.
        for (int i=0;i<worldSize;i++) {
            world[i][0]  = 1;
            world[i][worldSize-1] = 1;
            world[0][i]  = 1;
            world[worldSize-1][i] = 1;
        }
        
        /*
        for (int[] row : world) {
            for (int i : row) {
                System.out.print(i);
            }
            System.out.println();
        }
        */
    }
    
    private void populate() {
        Creature protagonist = new Creature(2, 2, "Protagonist");
        Creature antagonist = new Creature(3, 3, "Antagonist");
    }
}
