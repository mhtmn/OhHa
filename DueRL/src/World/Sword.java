/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Sword extends Item {

    public Sword() {
        super();
        this.damage   = 10;
        this.maxRange = 3;
        this.minRange = 2;
        this.name = "A sword";
        this.description = "A mid-range blade.";
        this.sharp = true;
    }
    
    public Sword(Creature owner) {
        this();
        this.owner       = owner;
    }
}
