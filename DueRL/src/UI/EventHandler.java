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
    private Interface ui;

    public EventHandler(Environment world, Component component, Interface ui) {
        this.world = world;
        this.ui = ui;
        this.component = component;
        System.out.println("Event handler created.");
    }

    @Override
    // Todo: make these into a hashmap, this is ugly...
    public void keyPressed(KeyEvent e) {
        boolean success;
        
        if        (e.getKeyCode() == KeyEvent.VK_LEFT ||
                   e.getKeyCode() == KeyEvent.VK_A) {
            success = world.getProtagonist().move(0, -1);
            if (success) {
                world.getProtagonist().clearTarget();
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
                   e.getKeyCode() == KeyEvent.VK_D) {
            success = world.getProtagonist().move(0, 1);
            if (success) {
                world.getProtagonist().clearTarget();
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_UP ||
                   e.getKeyCode() == KeyEvent.VK_W) {
            success = world.getProtagonist().move(-1, 0);
            if (success) {
                world.getProtagonist().clearTarget();
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ||
                   e.getKeyCode() == KeyEvent.VK_S) {
            success = world.getProtagonist().move(1, 0);
            if (success) {
                world.getProtagonist().clearTarget();
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // if protagonist has a target, attack
            // else go into targetting mode
            if (world.getProtagonist().hasTarget()) {
                world.getProtagonist().attack();
            } else {
                // Todo: targeting mode.
            }
        }

        world.update();
        System.out.println("Event handled!");
        component.repaint();
        ui.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
