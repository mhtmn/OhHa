package World;
/**
 * A class for creating items, so far mostly weapons.
 */

public class Item {
    private Creature owner;
    
    private int damage   = 10;
    private int maxRange = 3;
    private int minRange = 2;
    
    private String name;
    private String description;
    
    private boolean sharp;
    private boolean defensive = false;
    
    public Item() {
        this.owner       = null;
    }
    
    public Item(Creature owner) {
        this();
        this.owner       = owner;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public int getMaxRange() {
        return this.maxRange;
    }
    
    public int getMinRange() {
        return this.minRange;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
