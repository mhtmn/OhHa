/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Combat;

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
public class Damage {
    
    public Damage() {
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
    public void damage() {
        Creature creature = new Creature ( new World(25) );
        int healthBeforeDamage = creature.getHealth();
        creature.damage(10);
        assert(creature.getHealth() == healthBeforeDamage - 10);
    }
    
}
