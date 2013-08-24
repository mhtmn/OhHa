/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Knuckleduster extends Item {

    public Knuckleduster() {
        super();
        this.damage   = 5;
        this.maxRange = 2;
        this.minRange = 1;
        this.name = "Knuckleduster";
        this.description = "Short-range blunt weapons.";
        this.sharp = false;
    }
    
    public Knuckleduster(Creature owner) {
        this();
        this.owner       = owner;
    }
}

