/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
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
public class AdventurerDaoTest {
    
    public AdventurerDaoTest() {
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
     * Test of saveAdventurer method, of class AdventurerDao.
     */
    @Test
    public void testSaveAdventurer() throws Exception {
        System.out.println("saveAdventurer");
        Adventurer adventurer = null;
        AdventurerDao.saveAdventurer(adventurer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdventurer method, of class AdventurerDao.
     */
    @Test
    public void testGetAdventurer() throws Exception {
        System.out.println("getAdventurer");
        String name = "";
        Adventurer expResult = null;
        Adventurer result = AdventurerDao.getAdventurer(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAdventurer method, of class AdventurerDao.
     */
    @Test
    public void testGetAllAdventurer() throws Exception {
        System.out.println("getAllAdventurer");
        List<Adventurer> expResult = null;
        List<Adventurer> result = AdventurerDao.getAllAdventurer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAdventurer method, of class AdventurerDao.
     */
    @Test
    public void testDeleteAdventurer() throws Exception {
        System.out.println("deleteAdventurer");
        String name = "";
        AdventurerDao.deleteAdventurer(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
