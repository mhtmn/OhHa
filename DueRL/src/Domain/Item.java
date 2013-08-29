package Domain;

/**
 * A class for creating items, so far mostly weapons.
 */
public class Item {

    /**
     * The creature instance linked to the current item instance
     */
    public Creature owner;
    
    /**
     * How much damage does the item inflict on target
     */
    public int damage;
    
    /**
     * How far can the item be used
     */
    public double maxRange;
    
    /**
     * How close is the item still useful
     */
    public double minRange;
    
    /**
     * The name with which the item is referenced ingame
     */
    public String name;
    
    /**
     * Not used so far.  A more in-depth description of the item.
     */
    public String description;
    
    /**
     * Tells if the item/weapon is sharp or blunt.  Determines the kind of critical it makes.
     */
    public boolean sharp;
    
    /**
     * Basically tells if we're dealing with a shield or a weapon.
     */
    public boolean defensive;
  
    public Item(Creature owner, int type) {
        switch (type) {
            case 1: Greataxe();
                    break;
                
            case 2: Dagger();  
                    break;
                
            case 3: Knuckleduster();
                    break;
                
            case 4: Mace();
                    break;
                
            case 5: Sword();
                    break;
                
            case 6: Shield();
                    break;
        }

        this.owner = owner;
    }
    
    /**
     * Critical hits are different based on weapon type.  Gives targetted creature
     * the appropriate debuff.
     * @param target 
     */
    public void dealCritical(Creature target) {
        if (this.sharp) {
            target.setBleed(true);
        } else {
            target.setStun(true);
        }
    }

    // Determining the item type
    
    /**
     * Sets the item's type to Greataxe
     */
    private void Greataxe() {
        this.damage   = 15;
        this.maxRange = 3.4;
        this.minRange = 2.4;    
        this.name = "A greataxe";
        this.description = "A heavy long range blade.";    
        this.sharp = true;
    }

    /**
     * Sets the item's type to Dagger
     */
    private void Dagger() {
        this.damage   = 7;
        this.maxRange = 1.9;
        this.minRange = 0;
        this.name     = "A dagger";
        this.description = "Short blades.";    
        this.sharp    = true;
    }

    /**
     * Sets the item's type to Knuckleduster
     */
    private void Knuckleduster() {
        this.damage   = 7;
        this.maxRange = 1.9;
        this.minRange = 0;
        this.name = "A knuckleduster";
        this.description = "Short-range blunt weapons.";
        this.sharp = false;
    }

    /**
     * Sets the item's type to Mace
     */
    private void Mace() {
        this.damage   = 10;
        this.maxRange = 2.4;
        this.minRange = 1.4;
        this.name = "A mace";
        this.description = "A mid-range blunt weapon.";
        this.sharp = false;
    }

    /**
     * Sets the item's type to Sword
     */
    private void Sword() {
        this.damage   = 10;
        this.maxRange = 2.4;
        this.minRange = 1.4;
        this.name = "A sword";
        this.description = "A mid-range blade.";
        this.sharp = true;
    }

    /**
     * Sets the item's type to Shield
     */
    private void Shield() {
        this.damage   = 0;
        this.maxRange = 0.0;
        this.minRange = 0.0;
        this.name = "A shield";
        this.description = "A round shield.";
        this.sharp = false;
        this.defensive = true;
    }
    
    // Getters
    public int getDamage() {
        return this.damage;
    }
    
    public double getMaxRange() {
        return this.maxRange;
    }
    
    public double getMinRange() {
        return this.minRange;
    }
    
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for whether the item can be used in defence
     * @return boolean
     */
    public boolean isDefensive() {
        return this.defensive;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
