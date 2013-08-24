/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

/**
 *
 * @author eniirane
 */
public class Greataxe extends Item {

    public Greataxe() {
        super();
        this.damage   = 15;
        this.maxRange = 4;
        this.minRange = 3;    
        this.name = "A greataxe";
        this.description = "A heavy long range blade.";    
        this.sharp = true;
    }
    
    public Greataxe(Creature owner) {
        this();
        this.owner       = owner;
    }
}
