/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import World.Environment;

/**
 *
 * @author eniirane
 */
public class StatDisplay {

    private Environment world;
    
    public StatDisplay(Environment world) {
        this.world = world;
    }
    
    public String toString() {
        return "Health: " + world.getProtagonist().getHealth() + "\n" +
               "Strength: " + world.getProtagonist().getStrength() + "\n" +
               "Agility: " + world.getProtagonist().getAgility() + "\n" +
               "Score: " + world.getProtagonist().getScore() + "\n" +
               world.getProtagonist().getStunnedString();
    }
}
