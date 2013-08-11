/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import World.Creature;
import World.Environment;

/**
 *
 * @author eniirane
 */
public class EventHandler implements KeyListener {
    
    private Component component;
    private Environment world;

    public EventHandler(Environment world, Component component) {
        this.world = world;
        this.component = component;
        System.out.println("Event handler created.");
    }

    @Override
    // Todo: make these into a hashmap, this is ugly...
    public void keyPressed(KeyEvent e) {
        if        (e.getKeyCode() == KeyEvent.VK_LEFT ||
                   e.getKeyCode() == KeyEvent.VK_A) {
            world.getProtagonist().move(-1, 0);
            world.getProtagonist().clearTarget();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
                   e.getKeyCode() == KeyEvent.VK_D) {
            world.getProtagonist().move(1, 0);
            world.getProtagonist().clearTarget();
        } else if (e.getKeyCode() == KeyEvent.VK_UP ||
                   e.getKeyCode() == KeyEvent.VK_W) {
            world.getProtagonist().move(0, -1);
            world.getProtagonist().clearTarget();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ||
                   e.getKeyCode() == KeyEvent.VK_S) {
            world.getProtagonist().move(0, 1);
            world.getProtagonist().clearTarget();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // if protagonist has a target, attack
            // else go into targetting mode
            if (world.getProtagonist().hasTarget()) {
                world.getProtagonist().attack();
            } else {
                // Todo: targetting mode.
            }
        }

        world.update();
        System.out.println("Event handled!");
        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
