/**
 * Creating the playfield.
 */
package World;

import java.util.ArrayList;
import UI.CombatLog;

public class Environment {
    
    private Creature protagonist;
    private ArrayList<Creature> antagonists;
    private int worldSize;
    private CombatLog combatLog;
    private Level level;

    public Environment(int levelDepth, int worldSize) {
        this.protagonist = new Creature(this,1,1,"Protagonist");
        this.combatLog = new CombatLog();
        this.worldSize = worldSize;

        this.level = new Level(this, levelDepth);
        level.packWorld();
    }


    /**
     * updater function for world
     */
    public void update() {

        // if all enemies are dead, create exit
        for (Creature enemy : level.getAntagonists()) {
            if (enemy.isAlive()) {
                break;
            } else if (!level.exitExists()) {
                level.createExit();
            }
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
            }

            // enemy attacks
            for (Creature enemy : antagonists) {
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

        for (Creature enemy : antagonists) {
            checkDebuffs(enemy);
        }

        // smooshing the character and enemy icons into the world
        level.packWorld();
    }
    
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
