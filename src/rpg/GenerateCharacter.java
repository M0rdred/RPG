/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
//        Adventurer adventurer;
//        adventurer = TxtDao.getAdventurer("Mordred");
//        adventurer = AdventurerDao.getAdventurer("Mordred");
//        JsonDao.saveAdventurer(adventurer);
//
//        adventurer = TxtDao.getAdventurer("Darton");
//        JsonDao.saveAdventurer(adventurer);
//
//        adventurer = JsonDao.getAdventurer("Mordred");
//        AdventurerDao.saveAdventurer(adventurer);
//        JsonDao.saveCharAttributes();
//        System.out.println(CastesDao.getLevelForCaste(200000, "sorcerer"));
//        Leveling.gainXp(adventurer, 200);
        String[] modes = new String[10];
        String[] attributes = {"initValueBase", "attackValueBase", "defenseValueBase", "targetValueBase", "modPerLvl", "kpBase", "kpPerLvl", "?pBase", "fpBase", "fpPerLvl"};
        String caste;
        Scanner scanner = new Scanner(System.in);
        String var;
        JsonFactory jf = new JsonFactory();
        JsonGenerator generator = jf.createGenerator(new File("src/files/CasteBaseValues.json"), JsonEncoding.UTF8);
        generator.useDefaultPrettyPrinter();
        generator.writeStartObject();
        while (true) {
            System.out.print("class name: ");
            caste = scanner.nextLine();
            if (caste.equals("end")) {
                break;
            }
            generator.writeFieldName(caste);
            generator.writeStartObject();

            for (int i = 0; i < 10; i++) {
                generator.writeFieldName(attributes[i]);
                System.out.print(attributes[i] + ": ");
                var = scanner.nextLine();
                if (isNumber(var)) {
                    generator.writeNumber(var);
                } else {
                    generator.writeString(var);
                }
            }

        }
        generator.close();
    }

    private static boolean isNumber(String var) {
        try {
            int number = Integer.parseInt(var);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
