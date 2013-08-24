/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Shield extends Item {

    public Shield() {
        super();
        this.damage   = 0;
        this.maxRange = 1;
        this.minRange = 0;
        this.name = "A shield";
        this.description = "A round shield.";
        this.sharp = false;
        this.defensive = true;
    }
    
    public Shield(Creature owner) {
        this();
        this.owner       = owner;
    }
}
