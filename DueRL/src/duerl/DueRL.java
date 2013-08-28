package duerl;

import Domain.World;
import UI.Interface;
import UI.WelcomeScreen;
import javax.swing.SwingUtilities;

/**
 *
 */
public class DueRL {

    /**
     * Running the game window and a popup querying the player for a character name.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // A pop-up window asking for a character name.  The name is then given to a new world instance.
        WelcomeScreen nameQuery = new WelcomeScreen();
        World world = new World(20, nameQuery.getName());
        nameQuery.close();
        
        // Run the user interface with the created world instance.
        Interface userInterface = new Interface(world);
        SwingUtilities.invokeLater(userInterface); 
    }
}
