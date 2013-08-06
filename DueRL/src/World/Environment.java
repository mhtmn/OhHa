/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    private int[][] world;
    
    public Environment() {
    
    }
    
    public void buildWorld() {
        // Building world geometry.  0 is interpreted as floor, 1 as wall.
        for (int i=0;i<11;i++) {
            for (int j=0;i<11;j++) {
                world[i][j] = 0;
            }
        }

        for (int i=0;i<10;i++) {
            world[i][0]  = 1;
            world[i][10] = 1;
            world[0][i]  = 1;
            world[10][i] = 1;
        }
    }
}
