/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation;

import World.Creature;
import World.Environment;

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
        Creature first = new Creature(new Environment(1, 25));
    }

    @Test
    public void constructorCoordinates() {
        int x = 5;
        int y = 8;
        Creature second = new Creature(new Environment(2, 25), x, y);
    }

    @Test
    public void constructorAllArguments() {
        int x = 8;
        int y = 5;
        String name = "Clyde";
        Creature first = new Creature(new Environment(3, 25), x, y, name);
    }

    @Test
    public void testDistance() {
        int x = 8;
        int y = 5;
        String name1 = "Distant Dave";
        Creature first = new Creature(new Environment(4, 25), x, y, name1);   

        int a = 5;
        int b = 8;
        String name2 = "Forlorn Frank";
        Creature second = new Creature(new Environment(5, 25), a, b, name2);   

        assert(first.getDistance(second.getX(), second.getY()) == second.getDistance(first.getX(), first.getY()));
    }
}
