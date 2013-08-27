package World;

/**
 * A class for creating items, so far mostly weapons.
 */
public class Item {
    public Creature owner;
    
    public int damage;
    public double maxRange;
    public double minRange;
    
    public String name;
    public String description;
    
    public boolean sharp;
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
    public void Greataxe() {
        this.damage   = 15;
        this.maxRange = 3.5;
        this.minRange = 2.5;    
        this.name = "A greataxe";
        this.description = "A heavy long range blade.";    
        this.sharp = true;
    }

    /**
     * Sets the item's type to Dagger
     */
    public void Dagger() {
        this.damage   = 5;
        this.maxRange = 1.5;
        this.minRange = 0;
        this.name     = "Daggers";
        this.description = "Short blades.";    
        this.sharp    = true;
    }

    /**
     * Sets the item's type to Knuckleduster
     */
    public void Knuckleduster() {
        this.damage   = 5;
        this.maxRange = 1.5;
        this.minRange = 0;
        this.name = "Knuckleduster";
        this.description = "Short-range blunt weapons.";
        this.sharp = false;
    }

    /**
     * Sets the item's type to Mace
     */
    public void Mace() {
        this.damage   = 10;
        this.maxRange = 2.5;
        this.minRange = 1.5;
        this.name = "Mace";
        this.description = "A mid-range blunt weapon.";
        this.sharp = false;
    }

    /**
     * Sets the item's type to Sword
     */
    public void Sword() {
        this.damage   = 10;
        this.maxRange = 2.0;
        this.minRange = 1.5;
        this.name = "A sword";
        this.description = "A mid-range blade.";
        this.sharp = true;
    }

    /**
     * Sets the item's type to Shield
     */
    public void Shield() {
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
