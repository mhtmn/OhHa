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
    private Creature owner;
    
    private int damage   = 10;
    private int maxRange = 1;
    private int minRange = 1;
    
    private String name = "Daggers";
    private String description = "Short blades.";
    
    private boolean sharp = true;
    
    public Dagger() {
        super();
        this.damage   = 10;
        this.maxRange = 1;
        this.minRange = 1;
        this.name = "Daggers";
        this.description = "Short blades.";    
        this.sharp = true;
    }
    
    public Dagger(Creature owner) {
        this();
        this.owner       = owner;
    }
}
