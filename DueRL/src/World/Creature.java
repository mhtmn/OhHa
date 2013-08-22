/**
 * A base class for things moving about.  Both the protagonist and antagonists are
 * instances of this class.
 */
package World;
import java.lang.Math;
import AI.*;

public class Creature {
    // Creature basics
    private int x;
    private int y;
    private char icon = 'E';
    private String name;
    private String description;
    private Environment world;

    // Items
    private Item mainHand;
    private Item offHand;
    
    // Stats
    private int strength = 10;
    private int agility = 10;
    private int health = 100;
    private int score = 0;
    private boolean alive = true;
        
    // Since same class is used for the player and their opponent, ai flag is 
    // used for differentiating between the two.
    private boolean aiFlag = true;
    private AI ai;
    private boolean stunned = false;
    
    // Negative int as target values represent no target.
    private boolean targeting = false;
    private int targetX = -1;
    private int targetY = -1;
    private boolean attacking = false;

    public Creature(Environment world) {
        this.world = world;
        this.equip(new Item());
        this.x = 1;
        this.y = 1;
        this.name        = "A creature";
        this.description = "A somewhat ugly creature.";
    }
    
    public Creature(Environment world, int newX, int newY) {
        this(world);
        this.x = newX;
        this.y = newY;
    }

    public Creature(Environment world, String newName) {
        this(world);
        this.name = newName;
    }

    public Creature(Environment world, int newX, int newY, String newName) {
        this(world, newX, newY);
        this.name = newName;
    }
    
    /**
     * Giving the char an AI.
     */
    public void makeSentient() {
        this.ai = new AI(this);
    }
    

    // Game mechanics relating to combat
    // ---------------------------------
    
    /**
     * Giving the char a weapon.
     */
    public void equip(Item item) {
        if (mainHand == null) {
            this.mainHand = item;
        } else {
            this.offHand = item;
        }
    }
    
    /**
     * Attacks the target coordinates.
     * I changed stuff around a bit.  Now this method just declares that an 
     * attack has been made.
     */
    public void attack() {
        this.attacking = true;
    }
    
    /**
     * When an attack has been declared, this function takes care of 
     * calculating the hit and impact.
     */
    public void calculateDamage() {    
        // THIS METHOD IS SUSPICIOUSLY BLOATED, REFACTOR
        
        Creature target = null;

        // fixing the target for ai
        if (getAIStatus()) {
            this.targetX = this.world.getProtagonist().getX();
            this.targetY = this.world.getProtagonist().getY();            
            target = this.world.getProtagonist();
        } else {
            // else check what the player is targeting
            for (Creature enemy : world.getAntagonists()) {
                if (enemy.getX() == this.targetX 
                && enemy.getY() == this.targetY) {
                    target = enemy;
                } else {
                    world.report("Miss!");
                }
            }            
        }
        
        // damage is affected by 1) attacker's strength
        //                       2) weapon's damage rating
        //                       3) distance versus weapon's range
        //                       4) chance of a critical hit
        
        // calculating distance modifier
        double hitmodifier = this.getWeapon().getMaxRange() - this.getDistance(target.getX(), target.getY());
        
        // calculating damage
        double finalDamage = this.strength + this.mainHand.getDamage();
        boolean crit = this.agility < (int)(Math.random() * 10);
        finalDamage = (int)(finalDamage * hitmodifier);
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        
        if (crit) {
            finalDamage += this.strength;
            world.report("Critical!");
        }

        if (target != null && finalDamage > 0) {
            world.report("  for " + finalDamage + " damage!");
            world.report(this.name + " hits " + target.getName());
            target.damage((int)finalDamage);
        } else {
            world.report("Swing and a miss!");
        }

        if (!this.aiFlag) {
            clearTarget();
        }
    }
    
    /**
     * Clearing char's targets.
     */
    public void clearTarget() {
        this.targetX = 0;
        this.targetY = 0;
        this.targeting = false;
    }
        
    /**
     * Receiving damage.  Changes the alive -state of char when <0hp.
     */
    public void damage(int amount) {
        this.health -= amount;
        if (health <= 0) {
            die();
        }
    }
    
    /**
     * Helper function for preparing targeting.
     */
    public void startTargeting() {
        flagTargeting();
        this.setTargetX(this.x);
        this.setTargetY(this.y);
    }
    
    /**
     * Setting targeting flag to true;
     */
    public void flagTargeting() {
        this.targeting = true;
    }

    /**
     * A method for readying the char for afterlife.
     */
    public void die() {
        world.report(this.name + " dies!");
        this.icon = '%';
        this.description = "a corpse";
        this.name = "A corpse";
        this.alive = false;
    }
    
    // Moving about, setting coordinates, etc.
    // ---------------------------------------
    
        /**
         * Moves either the character or the targeting crosshair.
         * 
         * Receives the movement as a vector, updates coordinates accordingly
         * and returns true.  If movement is illegal, returns false.
         */
    public boolean move(int xChange, int yChange) {
        if (!alive) {
            return false;
        }
                
        if (!this.isTargeting()) {
            int newX = this.x + xChange;
            int newY = this.y + yChange;

            if (world.isFree(newX, newY)) {
                this.setCoordinate(newX, newY);
                return true;
            
            } else {
                return false;
            }
        } else {
            this.targetX = this.targetX + xChange;
            this.targetY = this.targetY + yChange;
            
            for (Creature creature : world.getAntagonists()) {
                if (creature.getX() == this.targetX && creature.getY() == this.targetY) {
                    world.report(creature.getName() + " wielding " + creature.getWeapon().toString() + ".");
                }
            }
            return true;
        }
        
    }

    public void setCoordinate(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    
    /*public void setTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }*/
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    /**
     * Get's the char's stunned status.
     */
    public boolean isStunned() {
        return this.stunned;
    }
        
    // Other getters/setters
    // ---------------------
    
    public String getName() {
        return this.name;
    }
    
    public char getIcon() {
        return this.icon;
    }
    
    public void setIcon(char icon) {
        this.icon = icon;
    }
    
    public AI getAI() {
        return this.ai;
    }
    
    public boolean getAIStatus() {
        return this.aiFlag;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setAIStatus(boolean aiStatus) {
        this.aiFlag = aiStatus;
    }

    public Double getDistance(int fromX, int fromY) {
        // a^2 + b^2 = c^2
        int a = Math.abs(this.x - fromX);
        int b = Math.abs(this.y - fromY);        
        return Math.sqrt(a * a + b * b);
    }
    
    public Item getWeapon() {
        return this.mainHand;
    }
    
    public Environment getWorld() {
        return this.world;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public int getStrength() {
        return this.strength;
    }
    
    public void setHeroMode() {
        this.strength = 100;
        this.agility = 100;
        this.health = 1000;
    }
    
    public int getAgility() {
        return this.agility;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getTargetX() {
        return this.targetX;
    }
    
    public int getTargetY() {
        return this.targetY;
    }

    public void setTargetX(int x) {
        this.targetX = x;
    }
    
    public void setTargetY(int y) {
        this.targetY = y;
    }

    public boolean isTargeting() {
        return this.targeting;
    }
    
    /**
     * Getting a string of stunned status for UI.
     */
    public String getStunnedString() {
        if (this.stunned) {
            return "Stunned!";
        } else {
            return "";
        }
    }
    
    public boolean getAttackStatus() {
        return this.attacking;
    }
    
    /**
     * Clear attacking status.
     */
    public void clearAttack() {
        this.attacking = false;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
