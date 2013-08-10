/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
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
    
    private Font font = new Font("Courier", Font.BOLD,16);
    public JTextArea matrix;
    
    public Interface(Environment playfield) {
        this.playfield = playfield;
    }
    
    @Override
    public void run() {
        frame = new JFrame("DueRL");
        frame.setPreferredSize(new Dimension(200, 365));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        this.matrix = new JTextArea("");
        matrix.setEditable(false);
        matrix.setFont(font);
        container.add(matrix);
                
        frame.setFocusable(true);
        matrix.setFocusable(true);

        this.repaint();
               
        matrix.addKeyListener(new EventHandler(playfield, matrix));
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void repaint() {
        System.out.println("UI repainted.");
        matrix.setText(playfield.toString());
    }
}
