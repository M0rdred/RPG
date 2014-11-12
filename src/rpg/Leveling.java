/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import dao.CastesDao;
import java.io.IOException;
import java.util.Scanner;
import objects.Adventurer;

/**
 *
 * @author dszabo1
 */
public class Leveling {

    private static Scanner scanner;

    public static Adventurer gainXp(Adventurer adventurer, int xpGained) throws IOException {

        int formerLevel = CastesDao.getLevelForCaste(adventurer.getXp(), adventurer.getCaste());
        int newLevel = CastesDao.getLevelForCaste(adventurer.getXp() + xpGained, adventurer.getCaste());
        int levelGained = newLevel - formerLevel;

        if (levelGained > 0) {
            for (int i = 0; i < levelGained; i++) {
                adventurer = levelUp(adventurer);
            }
        }

        adventurer.setXp(adventurer.getXp() + xpGained);
        return adventurer;
    }

    public static Adventurer levelUp(Adventurer adventurer) {

        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        adventurer.setFpMax(adventurer.getFpMax() + RollDice.roll(adventurer.getFpPerLvl()));
        adventurer.setPsziMax(adventurer.getPsziMax() + adventurer.getPsziPerLvl());
        adventurer.setMpMax(adventurer.getMpMax() + adventurer.getMpPerLvl());
        adventurer.setKp(adventurer.getKp() + adventurer.getKpPerLvl());
        adventurer.setLevel(adventurer.getLevel() + 1);

        String modPerLvl = adventurer.getModPerLvl();
        int modBase = Integer.parseInt(modPerLvl.substring(0, modPerLvl.indexOf("(")));
        int modStrict = Integer.parseInt(modPerLvl.substring(modPerLvl.indexOf("(") + 1, modPerLvl.indexOf(")")));
        int modDivide = modBase - modStrict - modStrict;

        System.out.println("Your combat value modifier is: " + modPerLvl);
        System.out.println("You have to spend " + modStrict + " points both to attack and defense.");
        System.out.println("You can freely divide the remainder " + modDivide + " points.");

        boolean looping = true;
        String[] combatValues = {"init", "attack", "defense", "target"};
        int i = 0;
        int divided;

        while (looping) {
            if (modDivide == 0) {
                looping = false;
                continue;
            }

            System.out.printf("How much do you want to spend on %s: ", combatValues[i]);
            divided = scanner.nextInt();

            if (divided > modDivide) {
                System.out.println("You can not spend more than " + modDivide + ", that's the most.");
                System.out.println("So I ask you again...");
                System.out.println("");
                continue;
            }
            if (divided > 0) {
                switch (i) {
                    case 0:
                        adventurer.setInitValue(adventurer.getInitValue() + divided);
                        modDivide -= divided;
                        i++;
                        break;
                    case 1:
                        adventurer.setAttackValue(adventurer.getAttackValue() + divided);
                        modDivide -= divided;
                        i++;
                        break;
                    case 2:
                        adventurer.setDefenseValue(adventurer.getDefenseValue() + divided);
                        modDivide -= divided;
                        i++;
                        break;
                    case 3:
                        adventurer.setTargetValue(adventurer.getTargetValue() + divided);
                        modDivide -= divided;
                        if (modDivide > 0) {
                            System.out.println("You can still spend some points. " + modDivide + " precisely.");
                            System.out.print("Do you want to spend them?(Y/N) ");
                            if (scanner.next().toLowerCase().equals("y")) {
                                i = 0;
                                break;
                            }
                        }
                        looping = false;
                }

            }
        }

        return adventurer;
    }

    public static int calculateXpGain(Adventurer winner, Adventurer loser) {

        int xp;
        int baseXp = (int) Math.pow(winner.getLevel() * 10, 2);

        int xpForFpLoss = (int) (baseXp * 0.1 * (1.0 - ((double) winner.getFp() / (double) winner.getFpMax())));
        int xpForÉpLoss = (int) (baseXp * 0.25 * (1.0 - ((double) winner.getÉp() / (double) winner.getÉpMax())));

        double levelDiffMultiplier;

        if (winner.getLevel() > loser.getLevel()) {
            levelDiffMultiplier = 1.0 / Math.pow(winner.getLevel() - loser.getLevel() + 1, 2);
        } else if (winner.getLevel() < loser.getLevel()) {
            levelDiffMultiplier = Math.pow(loser.getLevel() - winner.getLevel() + 1, 1.1);
        } else {
            levelDiffMultiplier = 1;
        }

        xp = (int) ((baseXp + xpForFpLoss + xpForÉpLoss) * levelDiffMultiplier);

        return xp;
    }
}
