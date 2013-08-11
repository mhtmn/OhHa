/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author eniirane
 */
import java.util.ArrayList;

public class CombatLog {
    private ArrayList<String> log;
    
    public CombatLog() {
        this.log = new ArrayList<String>();

        // This is bubblegum, clean it.
        for (int i=0;i<8;i++) {
            log.add("");
        }
    }
    
    public void add(String string) {
        this.log.add(0, string);
    }
    
    @Override
    public String toString() {
        String returnString = "";
        for (int i=0;i<8.;i++) {
            returnString += log.get(i) + "\n";
        }
        return returnString;
    }
}
