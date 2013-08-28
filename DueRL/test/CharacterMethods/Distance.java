/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterMethods;

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
public class Distance {
    
    public Distance() {
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
    public void distanceWhenOneTileAway() {
        World world = new World(1, 25);
        Creature first = new Creature(world, 2, 1);
        Creature second = new Creature(world, 2, 2);
        assert(first.getDistance(second.getX(), second.getY()) == 1.0);
    }

    @Test
    public void distanceWhenDiagonalTileAway() {
        World world = new World(1, 25);
        Creature first = new Creature(world, 1, 1);
        Creature second = new Creature(world, 2, 2);
        assert(first.getDistance(second.getX(), second.getY()) == 1.41);
    }
}
