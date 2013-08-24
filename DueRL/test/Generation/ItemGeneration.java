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
        Item epic = new Dagger();
        assert(epic != null);
    }
    
    @Test
    public void getDamage() {
        Item epic = new Knuckleduster();
        assert(epic.getDamage() == 10);
    }
    
    @Test
    public void getMaxRange() {
        Item epic = new Sword();
        assert(epic.getMaxRange() == 2);
    }
    
    @Test
    public void getMinRange() {
        Item epic = new Mace();
        assert(epic.getMinRange()==1);
    }

}
