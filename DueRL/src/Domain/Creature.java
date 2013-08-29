package Domain;
import AI.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

/**
 * A base class for things moving about.  Both the protagonist and antagonists are
 * instances of this class.
 */
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
    private World world;

    /**
     * Creature's combat abilities
     */
    private Combat combat;
    
    // Items
    
    /**
     * The mainhand weapon.
     */
    private Item mainHand;
    
    /**
     * The offhand weapon or shield.  So far of little use.
     */
    private Item offHand;

    /**
     * Collection for all the items the creature instance carries.
     */
    private ArrayList<Item> inventory;
    
    // Stats
    
    /**
     * Strength is used to determine damage dealt.
     */
    private int strength = 10;
    
    /**
     * Agility is used to determine dodge, block and critical hit chances.
     */
    private int agility = 10;
    
    /**
     * Health equals hitpoints.
     */
    private int health = 100;

    /**
     * Maximum health is reset between levels.
     */
    private int maxHealth = health;
    
    /**
     * Score is kept track of in highscore.txt
     */
    private int score;
    
    /**
     * Is the creature alive or not.
     */
    private boolean alive = true;
        
    /** Since same class is used for the player and their opponent, ai flag is 
    * used for differentiating between the two.
    */
    private boolean aiFlag = true;
    
    /**
     * Escaping is flagged when creature is low on hitpoints.  Used by AI.
     */
    private boolean escaping = false;
    
    /**
     * The AI instance linked to this creature instance.
     */
    private AI ai;
    
    /**
     * Flagged when dealt a critical hit from a blunt weapon.
     */
    private boolean stunned = false;
    
    /**
     * Flagged when dealt a critical hit form a sharp weapon.
     */
    private boolean bleeding = false;
    
    /**
     * Keeps track of if we're moving the crosshair or the player
     */
    private boolean targeting = false;
    
    /**
     * Targeted x coordinate
     */
    private int targetX;
    
    /**
     * Targeted y coordinate
     */
    private int targetY;

    /**
     * Keeps track of whether an attack has been declared.
     */
    private boolean attacking = false;
    
    /**
     * Keeps track of whether a kick has been declared.
     */
    private boolean kicking = false;

    /**
     * Keeps track of the cooldown associated with kick ability.
     */
    private int kickCoolDown = 0;
    
    public Creature(World world) {
        this.world = world;
        this.x = 1;
        this.y = 1;
        this.name        = "A creature";
        this.inventory = new ArrayList<Item>();
        this.combat = new Combat(this);
        this.score = 0;
        this.equipRandomWeapon();
    }
    
    public Creature(World world, int newX, int newY) {
        this(world);
        this.x = newX;
        this.y = newY;
    }

    public Creature(World world, String newName) {
        this(world);
        this.name = newName;
    }

    public Creature(World world, int newX, int newY, String newName) {
        this(world, newX, newY);
        this.name = newName;
    }
    
    /**
     * Giving the char an AI.
     */
    public void makeSentient() {
        this.ai = new AI(this);
    }
    

    /**
     * Giving the char a weapon.
     */
    private void equip(Item item) {
        this.inventory.add(item);
        if (mainHand == null) {
            this.mainHand = item;
        } else {
            this.offHand = item;
        }
    }

    /**
     * Equips the creature with a randomly selected set of weapons.
     */
    private void equipRandomWeapon() {
        double d = Math.random();
        if (d < 0.2) {
            // Two daggers
            this.equip(new Item(this, 2));
            this.equip(new Item(this, 2));
        } else if (d < 0.4 && d > 0.2) {
            // Two knuckledusters
            this.equip(new Item(this, 3));
            this.equip(new Item(this, 3));
        } else if (d < 0.6 && d > 0.4) {
            // Sword and shield
            this.equip(new Item(this, 5));
            this.equip(new Item(this, 6));            
        } else if (d < 0.8 && d > 0.6) {
            // Mace and shield
            this.equip(new Item(this, 4));
            this.equip(new Item(this, 6));            
        } else {
            // Greataxe
            this.equip(new Item(this, 1));
        }
    }
    
    /**
     * Attacks the target coordinates.
     * I changed stuff around a bit.  Now this method just declares that an 
     * attack has been made.  Combat class takes care of rest.
     */
    public void attack() {
        this.attacking = true;
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
        if (!this.getCombat().dodge() || !this.getCombat().block()) {
            this.health -= amount;
        }
        
        if (health <= 20 && this.aiFlag) {
            this.escaping = true;
        }
        
        if (health <= 0 && this.alive) {
            die();
        }
    }
                
    /**
     * Helper function for preparing targeting.
     */
    public void startTargeting() {
        this.setTargeting(true);
        this.setTargetX(this.x);
        this.setTargetY(this.y);
    }
    
    /**
     * Setting targeting flag to true;
     */
    public void setTargeting(boolean flip) {
        this.targeting = flip;
    }
    
    /**
     * Method for increasing stats, clearing debuffs and returning to max health
     * when proceeding to next level.
     */
    public void gainLevel() {
        this.increaseScore(100);
        this.maxHealth += 10;
        this.strength += 5;
        this.agility += 5;
        this.stunned = false;
        this.bleeding = false;
        this.health = this.maxHealth;
    }
    
    /**
     * Increases characters score by n.
     * @param n amount of increase.
     */
    public void increaseScore(int n) {
        this.score += n;
    }

    /**
     * A method for readying the char for afterlife.
     */
    private void die() {
        world.report(this.name + " dies!");
        this.icon = '%';
        this.description = "Remains of " + this.name + ".";        
        this.setBleed(false);
        this.setStun(false);
        this.alive = false;
        if (this.getAIStatus()) {
            this.name = "A corpse";            
            this.getWorld().getProtagonist().increaseScore(10);
        } else {        
            world.getHighScore().reWrite();
            world.report("HighScores:\n" + world.getHighScore().toString());
        }
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
        
        if (xChange > 1 || yChange > 1) {
            return false;
        }
                
        // If we're not targeting, we're moving the creature.
        if (!this.isTargeting()) {
            int newX = this.x + xChange;
            int newY = this.y + yChange;

            if (world.getLevel().isFree(newX, newY)) {
                this.setCoordinates(newX, newY);
                return true;
            
            } else {
                return false;
            }
            
            // Otherwise we're mocing the crosshair
        } else {
            if (world.getLevel().contains(this.targetX + xChange, this.targetY + yChange)) {
                this.targetX = this.targetX + xChange;
                this.targetY = this.targetY + yChange;
            }
            
            // Display enemy stats when hovering with the crosshair.
            for (Creature creature : world.getLevel().getAntagonists()) {
                if (creature.getX() == this.targetX && creature.getY() == this.targetY) {
                    world.report("  wielding " + creature.getWeapons().toString() + ".");
                    world.report(creature.getName() + " (" +creature.getHealth() + " hitpoints.)");
                }
            }
            return true;
        }
    }

    /**
     * Character gets bleeding damage each turn if this.bleeding is set to true.
     */
    public void bleed() {
        world.report(this.name + " is bleeding.");
        this.damage(5);
    }

    public void setCoordinates(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
        
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
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
    
    public void setEscaping(boolean flip) {
        this.escaping = flip;
    }
    
    public boolean getEscaping() {
        return this.escaping;
    }
        
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
    
    /**
     * Tells whether char is alive or not
     * @return boolean
     */
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setAIStatus(boolean aiStatus) {
        this.aiFlag = aiStatus;
    }

    /**
     * Calculates characters distance to given coordinates using pythagoras.
     * @param fromX
     * @param fromY
     * @return double
     */
    public Double getDistance(int fromX, int fromY) {
        int a = Math.abs(this.x - fromX);
        int b = Math.abs(this.y - fromY);
        double d = Math.sqrt(a * a + b * b);

        // apparently this is the fastest rounding available
        d = Math.round(d*100)/100.0d;
        
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
    
    public Combat getCombat() {
        return this.combat;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public int getStrength() {
        return this.strength;
    }
    
    /**
     * Used for debugging. :----)
     */
    public void setHeroMode() {
        this.strength = 50;
        this.agility = 50;
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
    
    public boolean isKicking() {
        return this.kicking;
    }
    
    public void setKicking(boolean flip) {
        this.kicking = flip;
    }
    
    public boolean getKickCoolDown() {
        return this.kickCoolDown == 0;
    }
    
    public void setKickCoolDown(int turns) {
        this.kickCoolDown = turns;
    }
    
    /**
     * Decrease all cooldowns by one.
     */
    public void decreaseCoolDowns() {
        if (this.kickCoolDown > 0) {
            kickCoolDown -= 1;
        }
    }
    
    /**
     * Checks if given coordinates are inside weapon range.
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isInRange(int x, int y) {
        if (this.getDistance(x, y) > this.getWeapons().get(0).getMinRange()
         && this.getDistance(x, y) < this.getWeapons().get(0).getMaxRange() ) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Creates a name for the NPC's :-) 
     * For extra silliness.
     * @return name as a string
     */
    public void createName() {
        Random r = new Random();
        
        String returnName = "";
        ArrayList<String> closedSyllables = new ArrayList<String>();
        ArrayList<String> openSyllables = new ArrayList<String>(); 

        closedSyllables.add("gol");
        closedSyllables.add("grol");
        closedSyllables.add("bal");
        closedSyllables.add("bol");
        closedSyllables.add("zug");
        closedSyllables.add("goth");
        openSyllables.add("du");
        openSyllables.add("ba");
        openSyllables.add("ro");
        openSyllables.add("zu");
        
        returnName += closedSyllables.get(r.nextInt(closedSyllables.size()));
        
        if (r.nextBoolean()) {
            returnName += openSyllables.get(r.nextInt(openSyllables.size()));
        }
        
        returnName += closedSyllables.get(r.nextInt(closedSyllables.size()));
        
        // Capitalization added
        this.name = Character.toUpperCase(returnName.charAt(0)) + returnName.substring(1);
    }
    
    /**
     * Getting a string of stunned status for UI.
     * @return String 
     */
    public String getStunnedString() {
        if (this.stunned) {
            return "Stunned!";
        } else {
            return "";
        }
    }
    
    /**
     * Getting a string of bleeding status for UI
     * @return 
     */
    public String getBleedingString() {
        if (this.bleeding) {
            return "Bleeding!";
        } else {
            return "";
        }        
    }

    /**
     * Getting a string representation of alive status for UI
     * @return 
     */
    public String getAliveString() {
        if (!this.alive) {
            return "DEAD";
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
