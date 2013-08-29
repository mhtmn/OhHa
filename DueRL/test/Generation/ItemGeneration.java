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
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 2);
        assert(epic != null);
    }

    @Test
    public void daggerGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 2);
        assert(epic.getName()=="A dagger");
    }
    
    @Test
    public void knuckledusterGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 3);
        assert(epic.getName()=="A knuckleduster");
    }    
    
    @Test
    public void swordGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 5);
        assert(epic.getName()=="A sword");
    }

    @Test
    public void maceGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 4);
        assert(epic.getName()=="A mace");
    }

    @Test
    public void greataxeGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 1);
        assert(epic.getName()=="A greataxe");
    }
    
    @Test
    public void shieldGeneration() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 6);
        assert(epic.getName()=="A shield");
    }

    @Test
    public void getDamage() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 3);
        assert(epic.getDamage() == 7);
    }
    
    @Test
    public void getMaxRange() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 5);
        assert(epic.getMaxRange() == 2.4);
    }


    @Test
    public void getMinRange() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 4);
        assert(epic.getMinRange()==1.4);
    }
    
    @Test
    public void bluntCritical() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 4);
        epic.dealCritical(creature);
    }
    
    @Test
    public void sharpCritical() {
        World world = new World(25);
        Creature creature = new Creature(world);
        Item epic = new Item(creature, 5);
        epic.dealCritical(creature);
    }

}

