package Generation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import World.Environment;

/**
 *
 * @author eniirane
 */
public class WorldGeneration {
    
    public WorldGeneration() {
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
    public void generateWorld() {
        Environment cosmos = new Environment();
    }
    
    @Test void populateGetProtagonist() {
        Environment cosmos = new Environment();
        assert(cosmos.getProtagonist() != null);
    }
}
