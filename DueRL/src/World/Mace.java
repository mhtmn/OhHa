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
    private int maxRange = 3;
    private int minRange = 2;
    
    private String name = "Mace";
    private String description = "A mid-range blunt weapon.";
    
    private boolean sharp = false;

    public Mace() {
        super();
    }
    
    public Mace(Creature owner) {
        this();
        this.owner       = owner;
    }
}
