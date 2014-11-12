/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import dao.AdventurerDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import objects.Adventurer;
import java.util.Random;

/**
 *
 * @author dszabo1
 */
public class Combat {

    private int roundCount = 0;

    /**
     * Organizes the fight between <tt>listOfAdventurers<tt>.
     * <p>
     * @param listOfAdventurers <p>
     * @return
     */
    public Adventurer fight(List<Adventurer> listOfAdventurers) throws IOException {
        Adventurer winner = null;

        for (Adventurer adv : listOfAdventurers) {
            adv.fullRestore();
            adv.useWeapon("fist");
        }

        System.out.println("Let the battle begin!!!!!!!!");

        while (winner == null) {
            winner = round(listOfAdventurers);

            for (int i = 0; i < listOfAdventurers.size(); i++) {
                if (!listOfAdventurers.get(i).isAlive() && !listOfAdventurers.get(i).isConscious()) {
                    listOfAdventurers.remove(i);
                }
            }
        }
        System.out.printf("And the winner is: %s\n", winner.getName());
        return winner;
    }

    /**
     * Plays one round of fight
     * <p>
     * @param adventurerList <p>
     * @return
     */
    private Adventurer round(List<Adventurer> adventurerList) throws IOException {

        int index, attack, defense, attacksInRound = 1, damage = 0;
        Adventurer winner;
        boolean hasMoreAttack;
        Random rnd = new Random();
        TreeMap<Integer, Adventurer> initMap = new TreeMap<>();
        int[] init = new int[adventurerList.size()];

        roundCount++;
        System.out.println("\n****************************************************\nRound number " + roundCount + "\n");

        init = initiation(adventurerList);
        Iterator<Adventurer> iterator = adventurerList.iterator();

        for (int count = 0, end = adventurerList.size(); count < end; count++) {
            initMap.put(init[count], adventurerList.get(count));
        }

        adventurerList = new ArrayList<>(initMap.values());
        System.out.printf("%s was faster.\n", adventurerList.get(0).getName());

        for (int i = 0; i < adventurerList.size(); i++) {

            hasMoreAttack = false;
            Adventurer adventurer = adventurerList.get(i);

            if (adventurer.getAttackNumbers() > 1 && adventurer.getAttackNumbers() < attacksInRound) {
                continue;
            }
            System.out.println("roundcount: " + roundCount);
            System.out.println(1.0 / adventurer.getAttackNumbers());
            System.out.println(roundCount % (1 % adventurer.getAttackNumbers()));
            if (adventurer.getAttackNumbers() < 1 && roundCount % adventurer.getAttackNumbers() != 0) {
                System.out.println(roundCount % adventurer.getAttackNumbers());
                continue;
            }

            Adventurer defender;

            do {
                index = rnd.nextInt(adventurerList.size());
                defender = adventurerList.get(index);
            } while (adventurer == defender);

            System.out.printf("%s is attacker and %s is defender.\n", adventurer.getName(), defender.getName());

            attack = adventurer.calculateAttackValue();
            defense = defender.calculateDefenseValue();

            if (attack >= defense) {
                damage = adventurer.calculateDamage();
                if (attack >= defense + 50) {
                    defender.woundTaken(damage, 0);
                    System.out.printf("%s hit %s with an OverHit and made %d Ép damage.\n", adventurer.getName(), defender.getName(), damage);
                } else {
                    defender.woundTaken(0, damage);
                    System.out.printf("%s hit %s and made %d Fp damage.\n", adventurer.getName(), defender.getName(), damage);
                }
            } else {
                System.out.printf("%s has not hit %s.\n", adventurer.getName(), defender.getName());
            }

            System.out.printf("%s has %d Ép and %d Fp.\n\n", defender.getName(), defender.getÉp(), defender.getFp());

            if (!defender.isConscious() || !defender.isAlive()) {
//                winner = adventurer;
//                return winner;
                adventurerList.remove(index);
                adventurer = Leveling.gainXp(adventurer, Leveling.calculateXpGain(adventurer, defender));
                AdventurerDao.saveAdventurer(adventurer);
                System.out.printf("%s is out of business!\n", defender.getName());
            }

            if (adventurerList.size() == 1) {
                winner = adventurerList.get(0);
                return winner;
            }

            if (i == adventurerList.size() - 1 || i == adventurerList.size()) {
                for (Adventurer adv : adventurerList) {
                    if (adv.getAttackNumbers() > attacksInRound) {
                        hasMoreAttack = true;
                        i = -1;
                        attacksInRound++;
                        break;
                    }
                }
            }

            if (hasMoreAttack) {
                i = -1;
            }
        }
        return null;
    }

    /**
     * Plays initiation for param List<Adventurer> . If value is equal to
     * former, throws again
     * <p>
     * @param adventurers <p>
     * @return init Array filled with values of adventurers
     */
    private int[] initiation(List<Adventurer> adventurers) {

        int[] init = new int[adventurers.size()];
        Adventurer adventurer;

        for (int i = 0, end = adventurers.size(); i < end; i++) {
            boolean valid = false;
            boolean match = false;
            adventurer = adventurers.get(i);
            do {
                init[i] = 1000 - adventurer.calculateInitValue();

                for (int count = 0; count < end; count++) {
                    if (init[count] == init[i] && count != i) {
                        match = true;
                        valid = false;
                        break;
                    } else if (init[count] == 0) {
                        match = false;
                        break;
                    } else if (i == 0) {
                        match = false;
                        break;
                    } else {
                        match = false;
                        valid = true;
                    }
                }
            } while (match);

            if (!valid && i != 0) {
                i--;
            }

        }
        return init;
    }
}
