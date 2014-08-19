/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
      AdventurerDao ad = new AdventurerDao();
      Adventurer adventurer = ad.getAdventurer("Mordred");
      ad.saveAdventurer(adventurer);
    }
    
}
