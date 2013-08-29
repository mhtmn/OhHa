/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import Domain.Creature;
import Domain.World;

/**
 *
 * @author eniirane
 */
public class LevelGain {
    
    public LevelGain() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void gainLevel() {
        Creature gainer = new Creature(new World(25));
        gainer.gainLevel();
    }

    @Test
    public void gainLevelAffectsScore() {
        Creature gainer = new Creature(new World(25));
        int scoreBeforeLevelGain = gainer.getScore();
        gainer.gainLevel();
        int scoreAfterLevelGain = gainer.getScore();
        assert(scoreAfterLevelGain > scoreBeforeLevelGain);
    }
    
    @Test
    public void gainLevelAffectsStats() {
        Creature gainer = new Creature(new World(25));
        int agilityBeforeLevelGain = gainer.getAgility();
        int strengthBeforeLevelGain = gainer.getStrength();
        gainer.gainLevel();
        int agilityAfterLevelGain = gainer.getAgility();
        int strengthAfterLevelGain = gainer.getStrength();
        assert(agilityAfterLevelGain > agilityBeforeLevelGain
                && strengthAfterLevelGain > strengthBeforeLevelGain);
    }
    
    @Test
    public void gainLevelAffectsHitPoints() {
        Creature gainer = new Creature(new World(25));
        int healthBeforeLevelGain = gainer.getHealth();
        gainer.gainLevel();
        int healthAfterLevelGain = gainer.getHealth();
        assert(healthAfterLevelGain > healthBeforeLevelGain);
    }
}
