/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

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
    
    private World world;
    
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
        this.world = new World(25);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void distanceWhenOneTileAway() {
        Creature first = new Creature(world, 2, 1);
        Creature second = new Creature(world, 2, 2);
        assert(first.getDistance(second.getX(), second.getY()) == 1.0);
    }

    @Test
    public void distanceWhenDiagonalTileAway() {
        Creature first = new Creature(world, 1, 1);
        Creature second = new Creature(world, 2, 2);
        assert(first.getDistance(second.getX(), second.getY()) == 1.41);
    }

    @Test
    public void testDistanceSymmetry() {
        int x = 8;
        int y = 5;
        String name1 = "Distant Dave";
        Creature first = new Creature(world, x, y, name1);   

        int a = 5;
        int b = 8;
        String name2 = "Forlorn Frank";
        Creature second = new Creature(world, a, b, name2);   

        double distanceFirstToSecond = first.getDistance(second.getX(), second.getY());
        double distanceSecondToFirst = second.getDistance(first.getX(), first.getY());
                
        assert(distanceFirstToSecond == distanceSecondToFirst);
    }
}
