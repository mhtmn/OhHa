/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Generation;

import Domain.Creature;
import Domain.World;
import Domain.Item;
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
public class ItemGeneration {
    
    private World world;
    private Creature creature;
    
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
        this.world = new World(25);
        this.creature = new Creature(world);
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
        Item epic = new Item(creature, 2);
        assert(epic != null);
    }

    @Test
    public void daggerGeneration() {
        Item epic = new Item(creature, 2);
        assert(epic.getName()=="A dagger");
    }
    
    @Test
    public void knuckledusterGeneration() {
        Item epic = new Item(creature, 3);
        assert(epic.getName()=="A knuckleduster");
    }    
    
    @Test
    public void swordGeneration() {
        Item epic = new Item(creature, 5);
        assert(epic.getName()=="A sword");
    }

    @Test
    public void maceGeneration() {
        Item epic = new Item(creature, 4);
        assert(epic.getName()=="A mace");
    }

    @Test
    public void greataxeGeneration() {
        Item epic = new Item(creature, 1);
        assert(epic.getName()=="A greataxe");
    }
    
    @Test
    public void shieldGeneration() {
        Item epic = new Item(creature, 6);
        assert(epic.getName()=="A shield");
    }

    @Test
    public void getDamage() {
        Item epic = new Item(creature, 3);
        assert(epic.getDamage() == 7);
    }
    
    @Test
    public void getMaxRange() {
        Item epic = new Item(creature, 5);
        assert(epic.getMaxRange() == 2.4);
    }


    @Test
    public void getMinRange() {
        Item epic = new Item(creature, 4);
        assert(epic.getMinRange()==1.4);
    }
    
    @Test
    public void bluntCritical() {
        Item epic = new Item(creature, 4);
        epic.dealCritical(creature);
    }
    
    @Test
    public void sharpCritical() {
        Item epic = new Item(creature, 5);
        epic.dealCritical(creature);
    }

}

