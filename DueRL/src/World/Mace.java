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
    private Creature owner;
    
    private int damage   = 20;
    private int maxRange = 2;
    private int minRange = 1;
    
    private String name = "Mace";
    private String description = "A mid-range blunt weapon.";
    
    private boolean sharp = false;

    public Mace() {
        this.owner       = null;
    }
    
    public Mace(Creature owner) {
        this();
        this.owner       = owner;
    }
}
