/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import World.*;

/**
 *
 * @author eniirane
 */
public class ItemGeneration {
    
    public ItemGeneration() {
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
    public void generation() {
        Environment world = new Environment(1, 25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 2);
        assert(epic != null);
    }
    
    @Test
    public void getDamage() {
        Environment world = new Environment(1, 25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 3);
        assert(epic.getDamage() == 10);
    }
    
    @Test
    public void getMaxRange() {
        Environment world = new Environment(1, 25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 5);
        assert(epic.getMaxRange() == 2);
    }
    
    @Test
    public void getMinRange() {
        Environment world = new Environment(1, 25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 4);
        assert(epic.getMinRange()==1);
    }

}
