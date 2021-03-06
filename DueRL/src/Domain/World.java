package Domain;

import UI.CombatLog;
import UI.HighScore;

/**
 * Creating the world in which levels are contained.
 */
public class World {
    
    /**
     * The player character
     */
    private Creature protagonist;
    
    /**
     * Size of the created world
     */
    private int worldSize;
    
    /**
     * CombatLog instance reporting world incidents to ui
     */
    private CombatLog combatLog;
    
    /**
     * The current Level instance
     */
    private Level level;
    
    /**
     * Keeps track of how deep into the game we are, i.e. how many levels has the player seen.
     */
    private int levelDepth;
    
    /**
     * HighScore instance linked to world.
     */
    private HighScore highscore;
    
    public World(int worldSize, String player) {
        this.protagonist = new Creature(this, 1, 1, player);
        this.protagonist.setIcon('@');
        this.protagonist.setAIStatus(false);
        this.combatLog = new CombatLog();
        this.worldSize = worldSize;
        this.levelDepth = 1;
        this.highscore = new HighScore(this);

        this.level = new Level(this, levelDepth);
        level.packWorld();
    }

    public World(int worldSize) {
        this(worldSize, "Anonymous");
    }
    
    /**
     * Updater function for world.  Consists of one turn.
     */
    public void update() {

        checkCoolDowns();
        
        // if all enemies are dead, create exit
        boolean allEnemiesDead = true;
        for (Creature enemy : level.getAntagonists()) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
            }
        }
        
        if (!level.exitExists() && allEnemiesDead) {
                level.createExit();
        }
        
        // enemy moving
        if (!protagonist.isTargeting()) {
            for (Creature enemy : level.getAntagonists()) {
                enemy.getAI().step();
            }
        }

        if (this.protagonist.isAlive()) {

            // player attacks 
            if (protagonist.getAttackStatus()) {
                for (Item i : protagonist.getWeapons()) {
                    protagonist.getCombat().calculateDamage(i);
                }
                protagonist.clearAttack();
                
                // If we're kicking, check that we're close enough and kick
            } else if (protagonist.isKicking()
                    && Math.abs(protagonist.getX() - protagonist.getTargetX()) <= 1
                    && Math.abs(protagonist.getY() - protagonist.getTargetY()) <= 1) {
                protagonist.getCombat().kick(protagonist.getTargetX(), protagonist.getTargetY());
            } else if (protagonist.isKicking()) {
                report("Too far!");
                protagonist.setKicking(false);
            }

            // enemy attacks
            for (Creature enemy : level.getAntagonists()) {
                if (enemy.getAttackStatus()) {
                    for (Item i : enemy.getWeapons()) {
                        enemy.getCombat().calculateDamage(i);
                    }
                    enemy.clearAttack();
                }
            }
        }

        // Player and enemy bleeds and stuns
        checkDebuffs(protagonist);

        for (Creature enemy : level.getAntagonists()) {
            checkDebuffs(enemy);
        }

        // smooshing the character and enemy icons into the world
        level.packWorld();
    }
    
    /**
     * Decrease all creature cooldowns.  So far only player has CD's.
     */
    public void checkCoolDowns() {
        protagonist.decreaseCoolDowns();
        
    }
    
    /**
     * Checks if given creature is suffering of debuffs and if those debuffs are
     * resisted.
     * @param creature 
     */
    public void checkDebuffs(Creature creature) {
        if (creature.getBleedingStatus() && !protagonist.isTargeting()) {
            creature.bleed();
            if (Math.random() < 0.33) {
                creature.setBleed(false);
            }
        }

        if (creature.getStunnedStatus() && !protagonist.isTargeting()) {
            if (Math.random() < 0.33) {
                creature.setStun(false);
                report(creature.getName() + " is no longer stunned.");
            }
        }
    }

    /**
     * Creates a new level for the player to proceed to.  
     */
    public void nextLevel() {
        this.levelDepth += 1;
        this.level = new Level(this, this.levelDepth);
        this.protagonist.gainLevel();
        this.level.packWorld();
    }
    
    // getters    
    public Level getLevel() {
        return this.level;
    }
    
    public Creature getProtagonist() {
        return this.protagonist;
    }

    public int getSize() {
        return this.worldSize;
    }
    
    public int getLevelDepth() {
        return this.levelDepth;
    }

    /**
     * Helper function for adding messages to the combat log.
     */
    public void report(String string) {
        combatLog.add(string);
    }

    public CombatLog getCombatLog() {
        return this.combatLog;
    }

    public HighScore getHighScore() {
        return this.highscore;
    }
    
    @Override
    public String toString() {
        return level.toString();
    }
}
