package UI;

import java.util.ArrayList;

/**
 * Class for creating and updating a combat log panel in the UI.
 */
public class CombatLog {
    private ArrayList<String> log;
    
    public CombatLog() {
        this.log = new ArrayList<String>();

        // This is bubblegum, clean it.
        for (int i=0;i<7;i++) {
            log.add("");
        }
    }
    
    /**
     * Add a string to combat log.
     */
    public void add(String string) {
        this.log.add(0, string);
    }
    
    @Override
    public String toString() {
        String returnString = "";
        for (int i=0;i<7.;i++) {
            returnString += log.get(i) + "\n";
        }
        return returnString;
    }
}
