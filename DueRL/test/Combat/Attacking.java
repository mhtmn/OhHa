/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Combat;

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
public class Attacking {
    
    private Creature creature;
    private Creature target;
    
    public Attacking() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.creature = new Creature ( new World(25), 1, 1 );
        this.target   = new Creature ( new World(25), 1, 2 );
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
    public void declareAttack() {
        creature.attack();
        assert(creature.getAttackStatus());
    }

    @Test
    public void kickingPossible() {
        creature.getCombat().kick(target.getX(), target.getY());
    }
    
    @Test
    public void critical() {
        creature.setHeroMode();
        assert(creature.getCombat().critical());
    }
}
