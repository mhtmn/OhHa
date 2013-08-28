package UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A query for asking character name.
 * @author eniirane
 */
public class WelcomeScreen {
    private String name;
    private JFrame parent;
    
    public WelcomeScreen() {
        parent = new JFrame();
    }
    
    public void close() {
        parent.dispose();
    }
    
    public String getName() {
        return JOptionPane.showInputDialog(parent,"What is your name?",null);
    }
}

