/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import dao.AdventurerDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objects.Adventurer;

/**
 *
 * @author dszabo1
 */
public class RPG {

    public static void main(String args[]) throws IOException, Exception {

        CharGen generator = new CharGen();
        Combat combat = new Combat();
        Scanner scanner = new Scanner(System.in);

        Adventurer adventurerA = AdventurerDao.getAdventurer("Mordred");
        Adventurer adventurerB = AdventurerDao.getAdventurer("Darton");
        Adventurer adventurerC = AdventurerDao.getAdventurer("Ranagol");
        Adventurer winner;

        List<Adventurer> adventurers = new ArrayList<>();

        adventurers.add(adventurerA);

        adventurers.add(adventurerB);

        adventurers.add(adventurerC);

        try {
//            adventurerA = generator.buildCharacter();
//            adventurerB = generator.buildCharacter();
            winner = combat.fight(adventurers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
