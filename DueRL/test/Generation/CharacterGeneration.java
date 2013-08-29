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
    public void constructorOneArgument() {
        Creature first = new Creature(new World(25));
    }

    @Test
    public void constructorCoordinates() {
        int x = 5;
        int y = 8;
        Creature second = new Creature(new World(25), x, y);
    }

    @Test
    public void constructorAllArguments() {
        int x = 8;
        int y = 5;
        String name = "Clyde";
        Creature first = new Creature(new World(25), x, y, name);
    }
    
    @Test
    public void equipmentGenerated() {
        Creature first = new Creature(new World(25));
        assert(!first.getWeapons().isEmpty());
    }

}
