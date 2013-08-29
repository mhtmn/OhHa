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
    
    private World world;
    
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
        this.world = new World(25);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void generateLevel() {
        assert(world.getLevel() != null);
    }
    
    @Test
    public void levelIsPopulated() {
        assert(!world.getLevel().getAntagonists().isEmpty());
    }
}
