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
    /*private Creature owner;
    
    private int damage   = 40;
    private int maxRange = 4;
    private int minRange = 3;
    
    private String name = "A greataxe";
    private String description = "A heavy long range blade.";
    
    private boolean sharp = true;
    */
    public Greataxe() {
        super();
        this.damage   = 40;
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
