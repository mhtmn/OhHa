/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Container;

import World.Environment;

/**
 *
 * @author eniirane
 */
public class Interface implements Runnable {
    
    private JFrame frame;
    
    private Environment playfield;
    
    private Font font = new Font("Courier", Font.BOLD,12);

    
    public Interface(Environment playfield) {
        this.playfield = playfield;
    }
    
    @Override
    public void run() {
        frame = new JFrame("DueRL");
        frame.setPreferredSize(new Dimension(150, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        JTextArea matrix = new JTextArea(playfield.toString());
        matrix.setEditable(false);
        matrix.setFont(font);
        container.add(matrix);
        
        frame.addKeyListener(new EventHandler(playfield, matrix));
    }

    public JFrame getFrame() {
        return frame;
    }
}
