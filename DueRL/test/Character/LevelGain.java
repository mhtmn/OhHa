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
    
    private Creature gainer;
    
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
        this.gainer = new Creature(new World(25));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void gainLevel() {
        gainer.gainLevel();
    }

    @Test
    public void gainLevelAffectsScore() {
        int scoreBeforeLevelGain = gainer.getScore();
        gainer.gainLevel();
        int scoreAfterLevelGain = gainer.getScore();
        assert(scoreAfterLevelGain > scoreBeforeLevelGain);
    }
    
    @Test
    public void gainLevelAffectsStats() {
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
        int healthBeforeLevelGain = gainer.getHealth();
        gainer.gainLevel();
        int healthAfterLevelGain = gainer.getHealth();
        assert(healthAfterLevelGain > healthBeforeLevelGain);
    }
}
