package World;

import UI.CombatLog;

/**
 * Creating the world in which levels are contained.
 */
public class Environment {
    
    private Creature protagonist;
    private int worldSize;
    private CombatLog combatLog;
    private Level level;
    private int levelDepth;
    
    public Environment(int levelDepth, int worldSize) {
        this.protagonist = new Creature(this, 1, 1, "Protagonist");
        this.protagonist.setIcon('@');
        this.protagonist.setAIStatus(false);
        this.combatLog = new CombatLog();
        this.worldSize = worldSize;
        this.levelDepth = levelDepth;

        this.level = new Level(this, levelDepth);
        level.packWorld();
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
                    protagonist.calculateDamage(i);
                }
                protagonist.clearAttack();
                
                // If we're kicking, check that we're close enough and kick
            } else if (protagonist.isKicking()
                    && Math.abs(protagonist.getX() - protagonist.getTargetX()) <= 1
                    && Math.abs(protagonist.getY() - protagonist.getTargetY()) <= 1) {
                protagonist.kick(protagonist.getTargetX(), protagonist.getTargetY());
            } else if (protagonist.isKicking()) {
                report("Too far!");
                protagonist.setKicking(false);
            }

            // enemy attacks
            for (Creature enemy : level.getAntagonists()) {
                if (enemy.getAttackStatus()) {
                    for (Item i : enemy.getWeapons()) {
                        enemy.calculateDamage(i);
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

    @Override
    public String toString() {
        return level.toString();
    }
}
