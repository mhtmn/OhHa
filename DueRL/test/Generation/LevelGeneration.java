/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation;

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
public class LevelGeneration {
    
    public LevelGeneration() {
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
    public void generateLevel() {
        World world = new World(25);
        assert(world.getLevel() != null);
    }
    
    @Test
    public void levelIsPopulated() {
        World world = new World(25);
        assert(!world.getLevel().getAntagonists().isEmpty());
    }
}
