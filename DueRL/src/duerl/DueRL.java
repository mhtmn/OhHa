package duerl;

import Domain.Environment;
import UI.Interface;
import UI.WelcomeScreen;
import javax.swing.SwingUtilities;

/**
 *
 */
public class DueRL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        Environment world = new Environment(1, 20);
                
        Interface userInterface = new Interface(world);
        SwingUtilities.invokeLater(userInterface); 
    }
}
