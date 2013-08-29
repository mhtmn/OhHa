/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Domain.Creature;
import Domain.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eniirane
 */
public class Level {
    
    public Level() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void nextLevelAffectsLevelDepth() {
        World world = new World(25);
        Creature gainer = new Creature(world);
        
        int dungeonLevelBeforeLevelGain = world.getLevelDepth();
        world.nextLevel();
        int dungeonLevelAfterLevelGain = world.getLevelDepth();
        
        assert(dungeonLevelAfterLevelGain > dungeonLevelBeforeLevelGain);        
    }


    
}
