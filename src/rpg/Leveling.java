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

        int formerLevel = CastesDao.getLevelForCaste(adventurer.getTp(), adventurer.getCaste());
        int newLevel = CastesDao.getLevelForCaste(adventurer.getTp() + xpGained, adventurer.getCaste());
        int levelGained = newLevel - formerLevel;

        if (levelGained > 0) {
            for (int i = 0; i < levelGained; i++) {
                adventurer = levelUp(adventurer);
            }
        }

        adventurer.setTp(adventurer.getTp() + xpGained);
        return adventurer;
    }

    private static Adventurer levelUp(Adventurer adventurer) {

        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        adventurer.setFpMax(adventurer.getFpMax() + RollDice.roll(adventurer.getFpPerLvl()));
        adventurer.setPsziMax(adventurer.getPsziMax() + adventurer.getPsziPerLvl());
        adventurer.setMpMax(adventurer.getMpMax() + adventurer.getMpPerLvl());
        adventurer.setKp(adventurer.getKp() + adventurer.getKpPerLvl());

        String modPerLvl = adventurer.getModPerLvl();
        int modBase = Integer.parseInt(modPerLvl.substring(0, modPerLvl.indexOf("(")));
        int modStrict = Integer.parseInt(modPerLvl.substring(modPerLvl.indexOf("(") + 1, modPerLvl.indexOf(")")));
        int modDivide = modBase - modStrict - modStrict;

        System.out.println("Your combat value modifier is: " + modPerLvl);
        System.out.println("You have to spend " + modStrict + " points both to attack and defense.");
        System.out.println("You can freely divide the remainder " + modDivide + " points.");
        
        
        //TODO
        System.out.print("How much do you want to spend on init: ");        
        adventurer.setInitValue(adventurer.getInitValue() + scanner.nextInt());
        
        System.out.print("How much do you want to spend on attack: ");        
        adventurer.setAttackValue(adventurer.getAttackValue()+ scanner.nextInt());
        
        System.out.print("How much do you want to spend on defense: ");        
        adventurer.setDefenseValue(adventurer.getDefenseValue()+ scanner.nextInt());
        
        System.out.print("How much do you want to spend on target: ");        
        adventurer.setTargetValue(adventurer.getTargetValue()+ scanner.nextInt());

        return adventurer;
    }
}
