/**
 * A class for creating a text representation of character information for UI.
 */
package UI;

import World.Environment;

public class StatDisplay {

    private Environment world;
    
    public StatDisplay(Environment world) {
        this.world = world;
    }
    
    @Override
    public String toString() {
        return "Health: " + world.getProtagonist().getHealth() + "\n" +
               "Strength: " + world.getProtagonist().getStrength() + "\n" +
               "Agility: " + world.getProtagonist().getAgility() + "\n" +
               "Score: " + world.getProtagonist().getScore() + "\n" +
               "Wielding: " + world.getProtagonist().getInventory().toString() + "\n" +
               world.getProtagonist().getStunnedString() + "\n" +
               world.getProtagonist().getBleedingString();
    }
}
