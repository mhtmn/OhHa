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
    
    private char icon = 'E';
    private String name;
    private String description;
    private Item weapon;
    private Environment world;
    
    // Since same class is used for the player and their opponent, ai flag is 
    // used for differentiating between the two.
    private boolean ai = true;
    
    // Negative int as target values represent no target.
    private int targetX = -1;
    private int targetY = -1;

    public Creature(Environment world) {
        System.out.println("Creature spawned...");
        this.world = world;
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
    

    // Game mechanics relating to combat
    // ---------------------------------
    
    public void equip(Item item) {
        this.weapon = item;
    }
    
    public void attack() {
        /*
         * Attacks the target coordinates.
         * 
         * This means checking what's on the coordinates and calculating impact 
         * to everything present there.
         */
    }
    
    public boolean hasTarget() {
        return (targetX >= 0 && targetY >= 0);
    }
    
    public void clearTarget() {
        this.targetX = -1;
        this.targetY = -1;
    }
    
    public void damage() {
        /*
         * Method for receiving damage.
         */
    }
    
    // Moving about, setting coordinates
    // ---------------------------------
    
    public boolean move(int xChange, int yChange) {
        /*
         * Receives the movement as a vector, updates coordinates accordingly
         * and returns true.  If movement is illegal, returns false.
         */
        
        int newX = this.x + xChange;
        int newY = this.y + yChange;
        
        if (world.isFree(newX, newY)) {
            System.out.println("Moving player from " +  this.x + ", " + this.y + " to " + newX + ", " + newY);        
            this.setCoordinate(newX, newY);
            return true;
            
        } else {
            System.out.println("Collision.  Coordinates " + this.x + ", " + this.y);
            return false;
        }
    }
    
    public void setCoordinate(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    public void setTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    // Other getters/setters
    // ---------------------
    
    public String getName() {
        return this.name;
    }
    
    public char getIcon() {
        return this.icon;
    }
    
    public void setIcon(char icon) {
        this.icon = icon;
    }
    
    public boolean getAIStatus() {
        return this.ai;
    }
    
    public void setAIStatus(boolean aiStatus) {
        this.ai = aiStatus;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
