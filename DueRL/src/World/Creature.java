/*
 * A base class for things moving about.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Creature {
    private int x;
    private int y;
    
    public char icon = '@';
    
    private String name;
    private String description;
    private Item weapon;

    public Creature() {
        this.x = 1;
        this.y = 1;
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
    
    // Game mechanics
    
    public void equip(Item item) {
        this.weapon = item;
    }
    
    public void attack(int attackX, int attackY) {
        
    }
    
    // Moving about, setting coordinates
    
    public boolean move(int xChange, int yChange) {
        /*
         * Receives the movement as a vector, updates coordinates accordingly
         * and returns true.  If movement is illegal, returns false.
         * 
         * No collision detection so far.
         */
        this.x += xChange;
        this.y += yChange;
                
        return true;
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
    
    // Other getters
    
    public String getName() {
        return this.name;
    }
    
    public char getIcon() {
        return this.icon;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
