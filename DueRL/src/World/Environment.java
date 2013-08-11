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

    private Creature protagonist;
    private ArrayList<Creature> antagonists; 
    private int worldSize = 20;
    private char[][] world;
    
        
    public Environment() {
        this.antagonists = new ArrayList<Creature>();
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
        for (int i=0;i<worldSize;i++) {
            world[i][0]  = '#';
            world[i][worldSize-1] = '#';
            world[0][i]  = '#';
            world[worldSize-1][i] = '#';
        }        
    }
    
    private void populate() {
        System.out.println("Populating world...");
        this.protagonist = new Creature(this, 2, 2, "Protagonist");
        this.protagonist.setAIStatus(false);
        this.protagonist.setIcon('@');
        this.antagonists.add(new Creature(this, 3, 3, "Antagonist"));
    }
    
    private void drawFloor() {
        for (int i=1;i<worldSize-1;i++) {
            for (int j=1;j<worldSize-1;j++) {
                world[i][j] = '.';
            }
        }
    }
    
    public void update() {
        /*
         * updater function for re-drawing
         */
        this.drawFloor();
                
        world[protagonist.getX()][protagonist.getY()] = protagonist.getIcon();
        
        for (Creature enemy : antagonists) {
            world[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }
        
    }

    // getters
    
    public Creature getProtagonist() {
        return this.protagonist;
    }
    
    public int getSize() {
        return this.worldSize;
    }
    
    public boolean isFree(int x, int y) {
        if (x <= 0 || y <= 0 || x > this.worldSize-1 || y > this.worldSize-1) {
            return false;
        } else if (this.world[x][y] == '.') {
            return true;
        } else {
            return false;
        }
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
