package UI;

import Domain.World;

/**
 * A class for creating a text representation of character information for UI.
 */
public class StatDisplay {

    private World world;
    
    public StatDisplay(World world) {
        this.world = world;
    }
    
    @Override
    public String toString() {
        return world.getProtagonist().getName() + " ("+ world.getProtagonist().getScore() + " points)" + "\n" +
               "Level: " + world.getLevelDepth() + "\n" +
               "Health: " + world.getProtagonist().getHealth() + "\n" +
               "Strength: " + world.getProtagonist().getStrength() + "\n" +
               "Agility: " + world.getProtagonist().getAgility() + "\n" +
               "Score: " + world.getProtagonist().getScore() + "\n" +
               "Wielding: " + world.getProtagonist().getInventory().toString() + "\n" +
               world.getProtagonist().getStunnedString() + "\n" +
               world.getProtagonist().getBleedingString() + "\n" +
               world.getProtagonist().getAliveString();
    }
}
