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
    /*private Creature owner;
    
    private int damage   = 20;
    private int maxRange = 3;
    private int minRange = 2;
    
    private String name = "A sword";
    private String description = "A mid-range blade.";
    
    private boolean sharp = true;
    */
    public Sword() {
        super();
        this.damage   = 20;
        this.maxRange = 3;
        this.minRange = 2;
        this.name = "A sword";
        this.description = "A mid-range blade.";
        this.sharp = false;
    }
    
    public Sword(Creature owner) {
        this();
        this.owner       = owner;
    }
}
