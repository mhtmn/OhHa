package World;

/**
 * A class for creating items, so far mostly weapons.
 */

public class Item {
    public Creature owner;
    
    public int damage;
    public int maxRange;
    public int minRange;
    
    public String name;
    public String description;
    
    public boolean sharp;
    public boolean defensive;
  
    public Item() {
    }
    
    public Item(Creature owner) {
        this();
        this.owner = owner;
    }
    
    public void dealCritical(Creature target) {
        if (this.sharp) {
            target.setBleed(true);
        } else {
            target.setStun(true);
        }
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
