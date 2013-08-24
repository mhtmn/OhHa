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
    private Creature owner;
    
    private int damage   = 10;
    private int maxRange = 1;
    private int minRange = 1;
    
    private String name = "Knuckleduster";
    private String description = "Short-range blunt weapons.";
    
    private boolean sharp = true;
    
    public Knuckleduster() {
        super();
    }
    
    public Knuckleduster(Creature owner) {
        this();
        this.owner       = owner;
    }
}

