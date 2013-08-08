/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Movement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import World.Creature;

/**
 *
 * @author eniirane
 */
public class CharacterMovement {
    
    public CharacterMovement() {
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
    public void moveNW() {
        Creature a = new Creature(5,5);
        a.move(-1, -1);
        
        assert(a.getX() == 4);
        assert(a.getY() == 4);
    }

    @Test
    public void moveN() {
        Creature a = new Creature(5,5);
        a.move(0, -1);
        
        assert(a.getX() == 5);
        assert(a.getY() == 4);
    }

    @Test
    public void moveS() {
        Creature a = new Creature(5,5);
        a.move(0, +1);
        
        assert(a.getX() == 5);
        assert(a.getY() == 6);
    }

}
