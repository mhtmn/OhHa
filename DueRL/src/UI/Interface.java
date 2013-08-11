/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Container;

import World.Environment;
import java.awt.Color;

/**
 *
 * @author eniirane
 */
public class Interface implements Runnable {
    
    private JFrame frame;
    private Environment world;
    
    private Font playAreaFont = new Font("Courier", Font.BOLD,16);
    private Font infoAreaFont = new Font("Courier", Font.BOLD,14);
    public JTextArea playArea;
    public JTextArea infoArea;
    
    private String infoText = "WASD or arrow keys to move." + "\n" + "Space to target/attack" + "\n" + "q to quit.";
    
    public Interface(Environment world) {
        this.world = world;
    }
    
    @Override
    public void run() {
        frame = new JFrame("DueRL");
        frame.setPreferredSize(new Dimension(450, 365));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(1, 2);
        container.setLayout(layout);        
        
        this.playArea = new JTextArea("");
        playArea.setBackground(Color.BLACK);
        playArea.setForeground(Color.WHITE);
        playArea.setEditable(false);
        playArea.setFont(playAreaFont);
        container.add(playArea);
        
        this.infoArea = new JTextArea(infoText);
        infoArea.setBackground(Color.BLACK);
        infoArea.setForeground(Color.DARK_GRAY);
        infoArea.setEditable(false);
        infoArea.setFont(infoAreaFont);
        container.add(infoArea);
        
        this.repaint();
               
        playArea.addKeyListener(new EventHandler(world, playArea, this));
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void repaint() {
        playArea.setText(world.toString());
    }
    
    public void exit() {
        System.out.println("Bye!");
        frame.dispose();
    }
}
