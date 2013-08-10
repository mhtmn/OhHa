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
    
    private char icon = '@';
    
    private String name;
    private String description;
    private Item weapon;
    private Environment world;

    public Creature(Environment world) {
        System.out.println("Creature spawned...");
        this.x = 1;
        this.y = 1;
        this.name        = "A creature";
        this.description = "A somewhat ugly creature.";
    }
    
    public Creature(Environment world, int newX, int newY) {
        this(world);
        this.x = newX;
        this.y = newY;
    }

    public Creature(Environment world, String newName) {
        this(world);
        this.name = newName;
    }

    public Creature(Environment world, int newX, int newY, String newName) {
        this(world, newX, newY);
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
         */
        
        int newX = this.x += xChange;
        int newY = this.y += yChange;
        
        if (world.isFree(newX, newY)) {
            System.out.print("Moving player from " +  this.x + " " + this.y + " to " + newX + ", " + newY);
        
            this.x = newX;
            this.y = newY;
                
            return true;
            
        } else {
            System.out.println("Collision.");
            return false;
        }
        
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
