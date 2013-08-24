/**
 * A base class for things moving about.  Both the protagonist and antagonists are
 * instances of this class.
 */
package World;
import java.lang.Math;
import java.util.ArrayList;
import AI.*;

public class Creature {

    /**
     * Character (later char) x coordinate
     */
    private int x;

    /**
     * Char y coordinate
     */
    private int y;

    /**
     * Default char icon (for enemy)
     */
    private char icon = 'E';

    /**
     * Char name, displayed in combat and in brief inspection
     */
    private String name;

    /**
     * Char description for
     */
    private String description;

    /**
     * The world in which the character resides.
     */
    private Environment world;

    // Items
    
    /**
     * The mainhand weapon.
     */
    private Item mainHand;
    
    /**
     * The offhand weapon or shield.
     */
    private Item offHand;

    private ArrayList<Item> inventory;
    
    // Stats
    private int strength = 10;
    private int agility = 10;
    private int health = 100;
    private int maxHealth = health;
    private int score = 0;
    private boolean alive = true;
        
    // Since same class is used for the player and their opponent, ai flag is 
    // used for differentiating between the two.
    private boolean aiFlag = true;
    private AI ai;
    private boolean stunned = false;
    private boolean bleeding = false;
    
    // Negative int as target values represent no target.
    private boolean targeting = false;
    private int targetX = -1;
    private int targetY = -1;
    private boolean attacking = false;

    public Creature(Environment world) {
        this.world = world;
        this.x = 1;
        this.y = 1;
        this.name        = "A creature";
        this.description = "A somewhat ugly creature.";
        this.inventory = new ArrayList<Item>();
        this.equipRandomWeapon();
        //this.equip(new Dagger(this));
        //this.equip(new Dagger(this));
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
        this.inventory.add(item);
        if (mainHand == null) {
            this.mainHand = item;
        } else {
            this.offHand = item;
        }
    }

    public void equipRandomWeapon() {
        double d = Math.random();
        if (d < 0.2) {
            this.equip(new Dagger(this));
            this.equip(new Dagger(this));
        } else if (d < 0.4 && d > 0.2) {
            this.equip(new Knuckleduster(this));
            this.equip(new Knuckleduster(this));
        } else if (d < 0.6 && d > 0.4) {
            this.equip(new Sword(this));
            this.equip(new Shield(this));            
        } else if (d < 0.8 && d > 0.6) {
            this.equip(new Mace(this));
            this.equip(new Shield(this));            
        } else {
            this.equip(new Greataxe(this));
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
    public void calculateDamage(Item weapon) {    
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
                    world.report(this.name + " misses!");
                }
            }            
        }
        
        // damage is affected by 1) attacker's strength
        //                       2) weapon's damage rating
        //                       3) distance versus weapon's range
        //                       4) chance of a critical hit
        //                     ( 5) dodge chance? )
        
        // calculating distance modifier
        double hitmodifier = 0.0;
        if (target == null) {
            world.report(this. name + " swings wildly and misses!");
        } else {
            hitmodifier = weapon.getMaxRange() - this.getDistance(target.getX(), target.getY());
        }
        
        // calculating damage
        double finalDamage = this.strength + weapon.getDamage();
        finalDamage = (int)(finalDamage * hitmodifier);
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        
        if (critical() && target != null) {
            world.report(this.name + " hits critically!");
            finalDamage += this.strength;
            weapon.dealCritical(target);
        }

        if (target != null && finalDamage > 0) {
            world.report("  for " + finalDamage + " damage!");
            world.report(this.name + " hits " + target.getName());
            target.damage((int)finalDamage);
        } else {
            world.report(this.name + " swings and misses!");
        }

        if (!this.aiFlag) {
            clearTarget();
        }
    }
    
    public boolean critical() {
        if (Math.random() < ((double)agility/100) ) {
            return true;
        } else {
            return false;
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
     * Receiving damage after checking for dodge.  Changes the alive -state of char when less than 0 hp.
     */
    public void damage(int amount) {
        
        // first check for dodge
        if (!dodge() || !block()) {
            this.health -= amount;
        }
        if (health <= 0) {
            die();
        }
    }
        
    public boolean dodge() {
        if (Math.random() < ((double)agility / 100)) {
            world.report(this.name + " dodges!");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean block() {
        for (Item i : this.inventory) {
            if (i.isDefensive() && Math.random() < ((double)agility / 100)) {
                world.report(this.name + " blocks!");
                return true;
                
            }
        }
        
        return false;
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
            if (inRange(targetX, targetY)) {
                this.targetX = this.targetX + xChange;
                this.targetY = this.targetY + yChange;
            }
            
            for (Creature creature : world.getAntagonists()) {
                if (creature.getX() == this.targetX && creature.getY() == this.targetY) {
                    world.report(creature.getName() + " wielding " + creature.getWeapons().toString() + ".");
                }
            }
            return true;
        }
        
    }

    public boolean inRange(int x, int y) {
        if (this.getDistance(x, y) <= this.getWeapons().get(0).getMaxRange()) {
            return true;
        }
        return false;
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
    public boolean getStunnedStatus() {
        return this.stunned;
    }
    
    public boolean getBleedingStatus() {
        return this.bleeding;
    }
    
    public void setStun(boolean flip) {
        this.stunned = flip;
    }
    
    public void setBleed(boolean flip) {
        this.bleeding = flip;
    }
    
    public void bleed() {
        this.health -= 5;
        world.report(this.name + " is bleeding.");
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
        double d = Math.sqrt(a * a + b * b);
        d = Math.round(d*100)/100.0d;
        System.out.println(d);
        return d;
    }
    
    public ArrayList<Item> getWeapons() {
        ArrayList<Item> weapons = new ArrayList<Item>();
        for (Item i : inventory) {
            if (!i.isDefensive()) {
                weapons.add(i);
            }
        }
        
        return weapons;        
    }
    
    public ArrayList<Item> getInventory() {
        return this.inventory;
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
    
    public String getBleedingString() {
        if (this.bleeding) {
            return "Bleeding!";
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
