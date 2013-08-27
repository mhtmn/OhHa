/**
 * Class for handling user input.
 */
package UI;

import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import World.Creature;
import World.Environment;

public class EventHandler implements KeyListener {

    private Component component;
    private Environment world;
    private Interface ui;

    public EventHandler(Environment world, Component component, Interface ui) {
        this.world = world;
        this.ui = ui;
        this.component = component;
        System.out.println("Event handler created.");
    }

    @Override
    /**
     * Handling the keyboard events, calling Creature class methods accordingly.
     */
    public void keyPressed(KeyEvent e) {
        boolean success;
        
        if (world.getProtagonist().isTargeting() && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            world.getProtagonist().clearTarget();
            world.getLevel().packWorld();
        }
        
        if (!world.getProtagonist().getStunnedStatus()) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT
                    || e.getKeyCode() == KeyEvent.VK_A) {
                success = world.getProtagonist().move(0, -1);
                world.update();

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT
                    || e.getKeyCode() == KeyEvent.VK_D) {
                success = world.getProtagonist().move(0, 1);
                world.update();

            } else if (e.getKeyCode() == KeyEvent.VK_UP
                    || e.getKeyCode() == KeyEvent.VK_W) {
                success = world.getProtagonist().move(-1, 0);
                world.update();

            } else if (e.getKeyCode() == KeyEvent.VK_DOWN
                    || e.getKeyCode() == KeyEvent.VK_S) {
                success = world.getProtagonist().move(1, 0);
                world.update();

            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // if protagonist has a target, attack
                // else go into targetting mode
                if (world.getProtagonist().isTargeting()) {
                    world.getProtagonist().attack();
                    world.update();
                    world.update();
                } else {
                    world.getProtagonist().startTargeting();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER
                    && world.getProtagonist().getX() == world.getLevel().getExitX()
                    && world.getProtagonist().getY() == world.getLevel().getExitY()) {
                nextLevel();
            }
            
        } else if (world.getProtagonist().getStunnedStatus()) {
            world.report("You're stunned!");
            world.update();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.out.println("Closing program...");
            ui.exit();
        }

        component.repaint();
        ui.repaint();
    }

    public void nextLevel() {
        world.nextLevel();
    }
        
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
