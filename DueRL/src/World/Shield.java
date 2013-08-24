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
    private Creature owner;
    
    private int damage   = 0;
    private int maxRange = 1;
    private int minRange = 0;
    
    private String name = "A shield";
    private String description = "A round shield.";
    
    private boolean sharp = false;
    private boolean defensive = true;
    
    public Shield() {
        super();
    }
    
    public Shield(Creature owner) {
        this();
        this.owner       = owner;
    }
}
