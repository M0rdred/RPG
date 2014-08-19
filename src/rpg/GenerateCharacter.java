/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import dao.AdventurerDao;
import dao.CastesDao;
import dao.JsonDao;
import dao.TxtDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import objects.Adventurer;

/**
 *
 * @author dszabo1
 */
public class GenerateCharacter {

    public static void main(String args[]) throws IOException, Exception {
//        AdventurerDao adventurerDao = new AdventurerDao();
//        Adventurer adventurer = generator.buildCharacter();
//        Adventurer adventurer = adventurerDao.getAdventurer("Mordred");

//        adventurerDao.saveAdventurer(adventurer);
//        adventurerDao.getAllAdventurers();
//        Adventurer adventurer = TxtDao.getAdventurer("Mordred");
        Adventurer adventurer;
//        adventurer = TxtDao.getAdventurer("Mordred");
        adventurer = AdventurerDao.getAdventurer("Mordred");
//        JsonDao.saveAdventurer(adventurer);
//
//        adventurer = TxtDao.getAdventurer("Darton");
//        JsonDao.saveAdventurer(adventurer);
//
//        adventurer = JsonDao.getAdventurer("Mordred");
//        AdventurerDao.saveAdventurer(adventurer);
//        JsonDao.saveCharAttributes();
//        System.out.println(CastesDao.getLevelForCaste(200000, "sorcerer"));
        Leveling.gainXp(adventurer, 200);
    }
}
