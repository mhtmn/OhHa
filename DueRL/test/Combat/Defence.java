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
public class Defence {
    
    private Creature creature;
    
    public Defence() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.creature = new Creature ( new World(25) );
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
    public void dodgePossible() {
        boolean dodge = creature.getCombat().dodge();
    }
    
    @Test
    public void blockPossible() {
        boolean block = creature.getCombat().dodge();
    }
    
    @Test 
    public void dodgeWorks() {
        creature.setHeroMode();
        assert(creature.getCombat().dodge());
    }

    @Test 
    public void blockWorks() {
        while (!creature.getInventory().get(1).isDefensive()) {
            creature = new Creature( new World(25) );
        }

        creature.setHeroMode();        

        if (creature.getInventory().get(1).isDefensive()) {
            assert(creature.getCombat().block());
        }
    }
}
