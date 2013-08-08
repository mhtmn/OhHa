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
    
    
    
    private String name;
    private String description;
    
    public Item() {
        this.owner       = null;
        this.name        = "An item";
        this.description = "A somewhat ordinary item.";
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
