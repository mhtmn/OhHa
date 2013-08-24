/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Mace extends Item {

    public Mace() {
        this.damage   = 10;
        this.maxRange = 3;
        this.minRange = 2;
        this.name = "Mace";
        this.description = "A mid-range blunt weapon.";
        this.sharp = false;
    }
    
    public Mace(Creature owner) {
        this();
        this.owner       = owner;
    }
}
