/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation;

import World.Creature;

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
public class CharacterGeneration {
    
    public CharacterGeneration() {
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
    public void constructorNoArguments() {
        Creature first = new Creature();
    }

    @Test
    public void constructorCoordinates() {
        int x = 5;
        int y = 8;
        Creature second = new Creature(x, y);
    }

    @Test
    public void constructorAllArguments() {
        int x = 8;
        int y = 5;
        String name = "Clyde";
        Creature first = new Creature(x, y, name);
    }

}
