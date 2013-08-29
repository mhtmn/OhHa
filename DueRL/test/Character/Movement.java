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
public class Movement {
    
    public Movement() {
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
    public void stayInPlace() {
        Creature creature = new Creature(new World(25));

        assert(creature.move(0, 0));
    }
    
    @Test
    public void tryToMoveTooMuch() {
        Creature creature = new Creature(new World(25));
        assert(!creature.move(-2, 2));        
    }

    @Test
    public void moveNW() {
        Creature a = new Creature(new World(25), 5,5);
        a.move(-1, -1);
        
        assert(a.getX() == 4);
        assert(a.getY() == 4);
    }

    @Test
    public void moveN() {
        Creature a = new Creature(new World(25), 5,5);
        a.move(0, -1);
        
        assert(a.getX() == 5);
        assert(a.getY() == 4);
    }

    @Test
    public void moveS() {
        Creature a = new Creature(new World(25), 5,5);
        a.move(0, +1);
        
        assert(a.getX() == 5);
        assert(a.getY() == 6);
    }

    @Test
    public void tryToMoveOutOfWorld() {
        World world = new World(25);
        Creature creature = new Creature(world, 1, 1);
        
        creature.move(-1, -1);
        
        int x = creature.getX();
        int y = creature.getY();
        assert(x == 1);
        assert(y == 1);
    }
}
