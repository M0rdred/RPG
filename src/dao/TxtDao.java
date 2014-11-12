/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import objects.Adventurer;
import objects.Armor;
import objects.Weapon;

/**
 *
 * @author dszabo1
 */
public class TxtDao {

    private static Scanner scanner;
    private static final String SAVE_FILE_PATH = "src/files/SavedCharacters.txt";

    /**
     *
     * @param name
     * @return
     */
    public static Adventurer getAdventurer(String name) throws FileNotFoundException {

        Adventurer adventurer = new Adventurer();
        File savedCharacters = new File(SAVE_FILE_PATH);
        scanner = new Scanner(savedCharacters);

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (row.contains(name)) {

                adventurer.setName(scanRow(row, -1));
                adventurer.setGender(scanRow(row, 0));
                adventurer.setAge(Integer.parseInt(scanRow(row, 1)));
                adventurer.setRace(scanRow(row, 2));
                adventurer.setCaste(scanRow(row, 3));
                adventurer.setAlignment(scanRow(row, 4));
                adventurer.setStrength(Integer.parseInt(scanRow(row, 5)));
                adventurer.setAgility(Integer.parseInt(scanRow(row, 6)));
                adventurer.setDexterity(Integer.parseInt(scanRow(row, 7)));
                adventurer.setFitness(Integer.parseInt(scanRow(row, 8)));
                adventurer.setHealth(Integer.parseInt(scanRow(row, 9)));
                adventurer.setIntelligence(Integer.parseInt(scanRow(row, 10)));
                adventurer.setBeauty(Integer.parseInt(scanRow(row, 11)));
                adventurer.setWillpower(Integer.parseInt(scanRow(row, 12)));
                adventurer.setAstral(Integer.parseInt(scanRow(row, 13)));
                adventurer.setInitValue(Integer.parseInt(scanRow(row, 14)));
                adventurer.setAttackValue(Integer.parseInt(scanRow(row, 15)));
                adventurer.setDefenseValue(Integer.parseInt(scanRow(row, 16)));
                adventurer.setTargetValue(Integer.parseInt(scanRow(row, 17)));
                adventurer.setFpMax(Integer.parseInt(scanRow(row, 18)));
                adventurer.setÉpMax(Integer.parseInt(scanRow(row, 19)));
                adventurer.setMpMax(Integer.parseInt(scanRow(row, 20)));
                adventurer.setPsiMax(Integer.parseInt(scanRow(row, 21)));
                adventurer.setKp(Integer.parseInt(scanRow(row, 22)));
                adventurer.setXp(Integer.parseInt(scanRow(row, 23)));
                adventurer.setFp(adventurer.getFpMax());
                adventurer.setÉp(adventurer.getÉpMax());
                adventurer.setMp(adventurer.getMpMax());
                adventurer.setPsi(adventurer.getPsiMax());
                adventurer.setAlive(true);
                adventurer.setConscious(true);
                adventurer.setWeapons(Arrays.asList(new Weapon(10, 4, 1, 0, 2, "fist", "k6", "melee")));
                adventurer.setArmor(new Armor("none", "none", 0, 0, 0));

                break;
            }
        }
        return adventurer;
    }

    public static void saveAdventurer(Adventurer adventurer) throws IOException {
        String toWrite;

        toWrite = adventurer.getName() + ";"
                + adventurer.getGender() + ";"
                + adventurer.getAge() + ";"
                + adventurer.getRace() + ";"
                + adventurer.getCaste() + ";"
                + adventurer.getAlignment() + ";"
                + adventurer.getStrength() + ";"
                + adventurer.getAgility() + ";"
                + adventurer.getDexterity() + ";"
                + adventurer.getFitness() + ";"
                + adventurer.getHealth() + ";"
                + adventurer.getIntelligence() + ";"
                + adventurer.getBeauty() + ";"
                + adventurer.getWillpower() + ";"
                + adventurer.getAstral() + ";"
                + adventurer.getInitValue() + ";"
                + adventurer.getAttackValue() + ";"
                + adventurer.getDefenseValue() + ";"
                + adventurer.getTargetValue() + ";"
                + adventurer.getFpMax() + ";"
                + adventurer.getÉpMax() + ";"
                + adventurer.getMpMax() + ";"
                + adventurer.getPsiMax() + ";"
                + adventurer.getKp() + ";"
                + adventurer.getXp() + "\n";
        File file = new File(SAVE_FILE_PATH);
        System.out.println("Looking for file " + file.getAbsolutePath());
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(toWrite);
        bufferedWriter.close();
    }

    private static String scanRow(String row, int numberOfAttribute) {
        String mode = null;
        Scanner rowScanner = new Scanner(row);
        rowScanner.useDelimiter(";");
        int counter = 0;
        numberOfAttribute++;

        while (rowScanner.hasNext()) {
            mode = rowScanner.next();

            if (numberOfAttribute == counter++) {
                break;
            }
        }
        return mode;
    }
}
