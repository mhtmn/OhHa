/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A welcome screen for the game
 */
public class WelcomeScreen implements Runnable {
    private Font font;
    
    public WelcomeScreen() {
        Font font = new Font("Courier", Font.BOLD,16);
        
    }
    
    public void createNameQuery(Container container) {
        GridLayout layout = new GridLayout(3, 3);
        container.setLayout(layout);       
        
        JTextArea queryText = new JTextArea("What is your character's name? ");
        JTextField queryField = new JTextField();
        
        queryText.setBackground(Color.BLACK);
        queryText.setForeground(Color.WHITE);
        queryText.setEditable(false);
        queryText.setFont(font);
        
        queryField.setBackground(Color.BLACK);
        queryField.setForeground(Color.WHITE);
        queryField.setEditable(true);
        queryField.setFont(font);
        
        container.add(queryText);
        container.add(queryField);

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
