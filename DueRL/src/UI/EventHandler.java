/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Component;
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
    private Creature character;
    private Environment world;

    public EventHandler(Environment world, Component component) {
        this.character = world.protagonist;
        this.world = world;
        this.component = component;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT ||
            e.getKeyCode() == KeyEvent.VK_A) {
            character.move(-1, 0);
            System.out.println("vasen!");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
                   e.getKeyCode() == KeyEvent.VK_D) {
            character.move(1, 0);
            System.out.println("oikea!");
        } else if (e.getKeyCode() == KeyEvent.VK_UP ||
                   e.getKeyCode() == KeyEvent.VK_W) {
            character.move(0, -1);
            System.out.println("ylös!");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ||
                   e.getKeyCode() == KeyEvent.VK_S) {
            character.move(0, 1);
            System.out.println("alas!");
        }

        world.update();
        System.out.println("event handled!");
        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
