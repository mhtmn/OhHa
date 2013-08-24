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
    private Creature owner;
    
    private int damage   = 20;
    private int maxRange = 2;
    private int minRange = 1;
    
    private String name = "A sword";
    private String description = "A mid-range blade.";
    
    private boolean sharp = true;
    
    public Sword() {
        this.owner       = null;
    }
    
    public Sword(Creature owner) {
        this();
        this.owner       = owner;
    }
}
