/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package duerl;

import World.Environment;
import UI.Interface;
import javax.swing.SwingUtilities;

/**
 *
 * @author eniirane
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
