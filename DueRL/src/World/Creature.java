/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Creature {
    private int x;
    private int y;
    
    public String icon = "@";
    
    private String name;
    private String description;

    public Creature() {
        this.x = 0;
        this.y = 0;
        this.name        = "A creature";
        this.description = "A somewhat ugly creature.";
    }
    
    public Creature(int newX, int newY) {
        this();
        this.x = newX;
        this.y = newY;
    }

    public Creature(String newName) {
        this();
        this.name = newName;
    }

    public Creature(int newX, int newY, String newName) {
        this(newX, newY);
        this.name = newName;
    }
    
    // Moving about, setting coordinates
    
    public void move() {
        
    }
    
    public void setCoordinate(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
