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
        World world = new World(20, cleanInput(nameQuery.getName()));
        nameQuery.close();
        
        // Run the user interface with the created world instance.
        Interface userInterface = new Interface(world);
        SwingUtilities.invokeLater(userInterface); 
    }

    public static String cleanInput(String input) {
        String output = "";
        if (input == "" || input == null) {
            output = "Anonymous";
        } else {
            String[] inputSplit = input.split("\\s+");            
            
            for (int i=0;i<inputSplit.length;i++) {
                output += inputSplit[i].substring(0, 1).toUpperCase() + inputSplit[i].substring(1);
            }
        }
        
        if (output.length() > 12 ) {
            output = output.substring(0, 11);
        }
        
        return output;
    }
}
