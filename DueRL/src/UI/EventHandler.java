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
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            character.move(-1, 0);
            System.out.println("vasen!");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            character.move(1, 0);
            System.out.println("oikea!");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            character.move(0, -1);
            System.out.println("ylös!");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            character.move(0, 1);
            System.out.println("alas!");
        }

        world.update();
        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
