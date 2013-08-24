/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Dagger extends Item {    

    public Dagger(Creature owner) {
        super(owner);
        this.damage   = 5;
        this.maxRange = 2;
        this.minRange = 1;
        this.name     = "Daggers";
        this.description = "Short blades.";    
        this.sharp    = true;
    }

    public Dagger() {
        this(null);
    }
}

