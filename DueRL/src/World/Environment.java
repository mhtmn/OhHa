/*
 * Creating the playfield.
 */
package World;

import java.util.ArrayList;

/**
 *
 * @author eniirane
 */
public class Environment {
    private String obstacleIcon        = "#";
    private String floorIcon           = ".";
    
    private String obstacleDescription = "This is a wall.  You cannot pass it.";
    private String floorDescription    = "You see just smooth floor.";

    public Creature protagonist;
    public ArrayList<Creature> antagonists; 
    private int worldSize = 20;
    public char[][] world;
    
        
    public Environment() {
        buildWorld();
        populate();
    }
    
    private void buildWorld() {
        /* 
         * Building world geometry.  . is interpreted as floor, # as wall.
         */
        world = new char[worldSize][worldSize];

        this.drawFloor();
        
        // Setting walls to outer limits of the world.
        for (int i=0;i<worldSize;i++) {
            world[i][0]  = '#';
            world[i][worldSize-1] = '#';
            world[0][i]  = '#';
            world[worldSize-1][i] = '#';
        }        
    }
    
    private void populate() {
        this.protagonist = new Creature(2, 2, "Protagonist");
        //this.antagonists.add(new Creature(3, 3, "Antagonist"));
    }
    
    private void drawFloor() {
        for (int i=1;i<worldSize-1;i++) {
            for (int j=1;j<worldSize-1;j++) {
                world[i][j] = '.';
            }
        }
    }
    
    public void update() {
        this.drawFloor();
        
        world[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();
    }

    @Override
    public String toString() {
        String returnString = "";
        
        for (int i=0;i<worldSize;i++) {
            for (int j=0;j<worldSize;j++) {
                returnString += "" + this.world[i][j];
            }
            returnString += "\n";
        }

        return returnString;
    }
}
