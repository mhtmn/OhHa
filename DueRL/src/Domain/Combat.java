/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author eniirane
 */
public class Combat {
    private Environment world;
    private Creature self;
    
    public Combat(Creature creature) {
        this.self = creature;
        this.world = creature.getWorld();
    }
    
    /**
     * When an attack has been declared, this function takes care of 
     * calculating the hit and impact.
     */
    public void calculateDamage(Item weapon) {    
        // THIS METHOD IS SUSPICIOUSLY BLOATED, REFACTOR
        
        Creature target = acquireTarget();
        
        double hitmodifier = hitModifier(weapon, target);
                
        // calculating weapon damage
        double finalDamage = self.getStrength() + weapon.getDamage();
        finalDamage = (int)(finalDamage * hitmodifier);

        // Checking critical hits
        if (critical() && target != null) {
            world.report(self.getName() + " hits critically!");
            finalDamage += weapon.getDamage();
            weapon.dealCritical(target);
        }

        // Wrapping up the damage and reporting
        if (target != null && finalDamage > 0) {
            world.report("  for " + finalDamage + " damage!");
            world.report(self.getName() + " hits " + target.getName());
            target.damage((int)finalDamage);
        } else if (target != null && finalDamage <= 0) {
            world.report(self.getName() + "'s hit glances away!");
        }

        if (!self.getAIStatus()) {
            clearTarget();
        }
    }

    public Creature acquireTarget() {
        Creature target = null;
        
        if (self.getAIStatus()) {
            self.setTargetX( self.getWorld().getProtagonist().getX() );
            self.setTargetY( self.getWorld().getProtagonist().getY() );            
            target = this.world.getProtagonist();
        } else {
            // else check what the player is targeting
            for (Creature enemy : world.getLevel().getAntagonists()) {
                if (enemy.getX() == self.getTargetX() 
                && enemy.getY() == self.getTargetY()) {
                    target = enemy;
                } else {
                    world.report(self.getName() + " hits thin air!");
                }
            }            
        }
        
        return target;

    }
    
    /**
     * Calculates how distace effects the hit.
     * @param weapon 
     * @param target the targeted creature
     * @return double which is added to damage later
     */
    public double hitModifier(Item weapon, Creature target) {
        
        // If there's no target, return zero
        if (target == null) {
            return 0.0;
        }
        
        // Check if opponent is on range
        if (self.getDistance(target.getX(), target.getY()) < (weapon.getMinRange() - 0.5)) {
            world.report(self.getName() + " is too close!");
            return 0.0;
        } else if (self.getDistance(target.getX(), target.getY()) > (weapon.getMaxRange() + 0.5)) {
            world.report(self.getName() + " is too far!");
            return 0.0;            
        }

        // Check how well we are in range
        return self.getWeapons().get(0).getMaxRange() - self.getDistance(target.getX(), target.getY());
    }
    
    /**
     * Calculates if the hit is a critical.  Based on creatures agility.
     * @return boolean 
     */
    public boolean critical() {
        if (Math.random() < ((double)self.getAgility()/100) ) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Clearing char's targets.
     */
    public void clearTarget() {
        self.setTargetX(0);
        self.setTargetY(0);
        self.setTargeting(false);
    }

    public void kick(int x, int y) {
        int vectorX = 0;
        int vectorY = 0;

        if (self.getX() < x) {
            vectorX = 1;
        } else if (self.getX() > x) {
            vectorX = -1;
        }
        
        if (self.getY() < y) {
            vectorY = 1;
        } else if (self.getY() > y) {
            vectorY = -1;
        }
        
        for (Creature e : self.getWorld().getLevel().getAntagonists()) {
            if (e.getX() == x && e.getY() == y) {
                e.move(vectorX, vectorY);
                world.report(self.getName() + " kicks " + e.getName() + " away!");
                if (Math.random() < 0.33) {
                    e.setStun(true);
                }
            }
        }
 
        if (!self.getAIStatus()) {
            clearTarget();
            self.setKicking(false);
        }
        
        self.setKickCoolDown(3);
    }
    /**
     * When receiving damage, dodge checks against agility to determine whether
     * that damage is avoided.
     * @return boolean 
     */
    public boolean dodge() {
        if (Math.random() < ((double)self.getAgility() / 100)) {
            world.report(self.getName() + " dodges!");
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * When receiving damage, block checks against agility to determine whether
     * that damage is avoided.
     * @return boolean 
     */
    public boolean block() {
        for (Item i : self.getInventory()) {
            if (i.isDefensive() && Math.random() < ((double)self.getAgility() / 100)) {
                world.report(self.getName() + " blocks!");
                return true;
                
            }
        }
        
        return false;
    }
    
}
