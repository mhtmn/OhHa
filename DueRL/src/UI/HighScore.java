package UI;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import Domain.Environment;

/**
 * Class taking care of handling and updating the highscore
 */
public class HighScore {

    ArrayList<String> highscoreArray;
    File highscoreFile = new File("highscore.txt");
    Environment world;
            
    public HighScore(Environment world) {
        this.world = world;
        this.highscoreArray = new ArrayList<String>();
    }
    
    public void readFile() throws Exception {
        Scanner reader = new Scanner(highscoreFile);

        while (reader.hasNextLine()) {
            String rivi = reader.nextLine();
            System.out.println(rivi);
        }

        reader.close();
    }            
    
    public void writeFile() {
        
    }
}
