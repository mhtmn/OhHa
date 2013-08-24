package World;
/**
 * A class for creating items, so far mostly weapons.
 */

public abstract class Item {
    private Creature owner;
    
    private int damage   = 10;
    private int maxRange = 1;
    private int minRange = 1;
    
    private String name = "A bland item";
    private String description = "An item without qualities";
    
    private boolean sharp = false;
    private boolean defensive = false;
    
    public Item() {
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
    
    public boolean isDefensive() {
        return this.defensive;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
