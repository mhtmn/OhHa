package UI;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
        
import Domain.World;

/**
 * Class taking care of handling and updating the highscore
 * @author eniirane
 */
public class HighScore {

    ArrayList<String> highScoreArray;
    File highScoreFile = new File("highscore.txt");
    World world;
            
    public HighScore(World world) {
        this.world = world;
        this.highScoreArray = new ArrayList<String>();
        
    }
    
    /**
     * Format an entry to highscore table
     * @return String a string consisting of name, score, level and weapons
     */
    public String constructEntry() {
        return world.getProtagonist().getName() + " " 
             + world.getProtagonist().getScore() + " " 
             + world.getLevelDepth() + " "
             + " wielding " + world.getProtagonist().getWeapons();
    }
    
    /**
     * Parses the score from a string (a line from the highscore.txt file)
     * @param entry 
     * @return int score
     */
    public int parseScore(String entry) {
        return Integer.parseInt(entry.split("\\s+")[1]);
    }    

    /**
     * Adds player's score to the right index in highscore array
     */
    public void addPlayerToHighScore() {   
        for (int i=0;i < highScoreArray.size();i++) {
            if (world.getProtagonist().getScore() > parseScore(highScoreArray.get(i))) {
                highScoreArray.add(i, constructEntry());
                break;
            }            
        }
    }
    
    public void reWrite() {
        System.out.println("Writing highscore...");
        addPlayerToHighScore();
        try {
            this.writeFile();
        } catch (Exception e) {
            System.out.println("Exception when writing highscore: " + e);
        }
    }
    
    // Handling files
    
    /**
     * Opens a file and reads it to highscorearray
     * @throws Exception 
     */
    public void readFile() throws Exception {
        Scanner reader = new Scanner(highScoreFile);

        while (reader.hasNextLine()) {
            String row = reader.nextLine();
            System.out.println(row);
        }
        reader.close();
    }            
    
    /**
     * Rewrites the highscore.txt
     * @throws Exception
     */
    public void writeFile() throws Exception {
        FileWriter writer = new FileWriter(highScoreFile);
        
        for (String line : this.highScoreArray) {
            writer.write(line + "\n");
        }

        writer.close();        
    }
        
    @Override
    public String toString() {
        String output = "";
        for (String i : this.highScoreArray) {
            output += i + "\n";
        }
        
        return output;
    }
}
