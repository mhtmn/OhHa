/*
 * A base class for things moving about.
 */
package World;
import java.lang.Math;
import AI.*;

/**
 *
 * @author eniirane
 */
public class Creature {
    // Creature basics
    private int x;
    private int y;
    private char icon = 'E';
    private String name;
    private String description;
    private Environment world;

    // Items
    private Item mainHand;
    private Item offHand;
    
    // Stats
    private int strength = 10;
    private int agility = 10;
    private int health = 100;
    private int score = 0;
        
    // Since same class is used for the player and their opponent, ai flag is 
    // used for differentiating between the two.
    private boolean aiFlag = true;
    private AI ai;
    
    // Negative int as target values represent no target.
    private boolean targeting = false;
    private int targetX = -1;
    private int targetY = -1;

    public Creature(Environment world) {
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
    
    public void makeSentient() {
        this.ai = new AI(this);
    }
    

    // Game mechanics relating to combat
    // ---------------------------------
    public void equip(Item item) {
        if (mainHand == null) {
            this.mainHand = item;
        } else {
            this.offHand = item;
        }
    }
    
    public int attack() {
        /*
         * NOTE: needs to be figured out better.  From where is this function
         * called?  Where is damage calculated?
         * 
         * Attacks the target coordinates.
         * 
         * This means checking what's on the coordinates and calculating impact 
         * to everything present there.
         * 
         * Damage is dealt according to strength.  If the hit is a critical, 
         * damage is doubled.
         */
        
        int critical = (int) Math.random() * 10;
        int weaponDamage = this.mainHand.getDamage() + this.offHand.getDamage();
        int bonus = 0;
        
        if (critical < agility) {
            bonus = strength;
        }       

        int attackPower = this.strength + weaponDamage +  bonus;
        System.out.println("Attack!");
        
        this.targeting = false;
        
        return attackPower;
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
        System.out.println("Damaged!");
    }
    
    public void flagTargeting() {
        this.targeting = true;
    }
    
    // Moving about, setting coordinates, etc.
    // ---------------------------------------
    
    public boolean move(int xChange, int yChange) {
        /*
         * Moves either the character or the targeting crosshair.
         * 
         * Receives the movement as a vector, updates coordinates accordingly
         * and returns true.  If movement is illegal, returns false.
         */
        
        int newX = this.x + xChange;
        int newY = this.y + yChange;
        
        if (world.isFree(newX, newY)) {
            this.setCoordinate(newX, newY);
            return true;
            
        } else {
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
    
    public AI getAI() {
        return this.ai;
    }
    
    public boolean getAIStatus() {
        return this.aiFlag;
    }
    
    public void setAIStatus(boolean aiStatus) {
        this.aiFlag = aiStatus;
    }

    public Double getDistance(int fromX, int fromY) {
        // a^2 + b^2 = c^2
        int a = Math.abs(this.x - fromX);
        int b = Math.abs(this.y - fromY);        
        return Math.sqrt(a * a + b * b);
    }
    
    public Environment getWorld() {
        return this.world;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
