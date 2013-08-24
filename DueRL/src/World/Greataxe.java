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
    private Creature owner;
    
    private int damage   = 40;
    private int maxRange = 3;
    private int minRange = 2;
    
    private String name = "A greataxe";
    private String description = "A heavy long range blade.";
    
    private boolean sharp = true;
    
    public Greataxe() {
        this.owner       = null;
    }
    
    public Greataxe(Creature owner) {
        this();
        this.owner       = owner;
    }
}
