/*
 * A placeholder class for possible items.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Item {
    private Creature owner;
    
    private int damage = 10;
    private int maxRange = 2;
    private int minRange = 1;
    
    private String name;
    private String description;
    
    public Item() {
        this.owner       = null;
        this.name        = "an item";
        this.description = "A somewhat ordinary item.";
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
