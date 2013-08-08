/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package duerl;

import World.Environment;
import javax.swing.SwingUtilities;
import UI.Interface;

/**
 *
 * @author eniirane
 */
public class DueRL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Environment playfield = new Environment();
        
        //System.out.println(playfield.toString());
        
        Interface userInterface = new Interface(playfield);
        SwingUtilities.invokeLater(userInterface);                
    }
}
