/*
 * A base class for things moving about.
 */
package World;
import java.lang.Math;
import AI.*;

/**
 *
 * @author eniirane
 */
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
    
    public void makeSentient() {
        this.ai = new AI(this);
    }
    

    // Game mechanics relating to combat
    // ---------------------------------
    public void equip(Item item) {
        if (mainHand == null) {
            this.mainHand = item;
        } else {
            this.offHand = item;
        }
    }
    
    public void attack() {
        /*
         * Attacks the target coordinates.
         * I changed stuff around a bit.  Now this method just declares that an 
         * attack has been made.
         */
        this.attacking = true;
    }
    
    public void calculateDamage() {
        /*
         * When an attack has been declared, this function takes care of 
         * calculating the hit and impact.
         */
        
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
        
        // calculating damage
        int finalDamage = this.strength + this.mainHand.getDamage();
        boolean crit = this.agility > Math.random() * 10;
        
        if (crit) {
            finalDamage += this.strength;
        }

        if (target != null) {
            world.report("  for " + finalDamage + " damage!");
            world.report(this.name + " hits " + target.getName());
            target.damage(finalDamage);
        }

        if (!this.aiFlag) {
            clearTarget();
        }
    }
    
    public void clearTarget() {
        this.targetX = 0;
        this.targetY = 0;
        this.targeting = false;
    }
        
    public void damage(int amount) {
        /*
         * Method for receiving damage.
         */
        this.health -= amount;
        if (health <= 0) {
            die();
        }
    }
    
    public void startTargeting() {
        flagTargeting();
        this.setTargetX(this.x);
        this.setTargetY(this.y);
    }
    
    public void flagTargeting() {
        this.targeting = true;
    }
    
    public void die() {
        world.report(this.name + " dies!");
        this.icon = '%';
        this.alive = false;
    }
    
    // Moving about, setting coordinates, etc.
    // ---------------------------------------
    
    public boolean move(int xChange, int yChange) {
        /*
         * Moves either the character or the targeting crosshair.
         * 
         * Receives the movement as a vector, updates coordinates accordingly
         * and returns true.  If movement is illegal, returns false.
         */
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
    
    public void clearAttack() {
        this.attacking = false;
    }
    
    @Override
    public String toString() {
        return name + " --- " + description;
    }
}
