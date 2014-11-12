/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rpg;

import dao.AdventurerDao;
import java.io.IOException;
import objects.Adventurer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dszabo1
 */
public class LevelingTest {
    
    public LevelingTest() {
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

    /**
     * Test of gainXp method, of class Leveling.
     */
    @Test
    public void testGainXp() throws Exception {
        System.out.println("gainXp");
        Adventurer adventurer = null;
        int xpGained = 0;
        Adventurer expResult = null;
        Adventurer result = Leveling.gainXp(adventurer, xpGained);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of levelUp method, of class Leveling.
     */
    @Test
    public void testLevelUp() {
        System.out.println("levelUp");
        Adventurer adventurer = null;
        Adventurer expResult = null;
        Adventurer result = Leveling.levelUp(adventurer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateXpGain method, of class Leveling.
     */
    @Test
    public void testCalculateXpGain() throws IOException {
        System.out.println("calculateXpGain");
        Adventurer winner = AdventurerDao.getAdventurer("Mordred");
        Adventurer loser = AdventurerDao.getAdventurer("Darton");
        winner.setFp(20);
        winner.setÉp(5);
        int expResult = 0;
        int result = Leveling.calculateXpGain(winner, loser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
