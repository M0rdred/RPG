/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Adventurer;

/**
 *
 * @author dszabo1
 */
public class AdventurerDao extends ObjectDao {

    private static final String SAVE_FILE_PATH = "src/files/savedCharacters.json";

    public static void saveAdventurer(Adventurer adventurer) throws IOException {
        try {
            List<Adventurer> adventurers = new ArrayList<>();
            adventurers = getAllAdventurer();

            boolean found = false;
            for (int i = 0, end = adventurers.size(); i < end; i++) {
                if (adventurers.get(i).getName().equals(adventurer.getName())) {
                    adventurers.set(i, adventurer);
                    found = true;
                    break;
                }
            }
            if (!found) {
                adventurers.add(adventurer);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(SAVE_FILE_PATH), adventurers);
        } catch (IOException ex) {
            Logger.getLogger(JsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Adventurer getAdventurer(String name) throws IOException {

        List<Adventurer> adventurers = new ArrayList<>();
        Adventurer adventurer = null;
        adventurers = getAllAdventurer();

        for (int i = 0, end = adventurers.size(); i < end; i++) {
            if (adventurers.get(i).getName().equals(name)) {
                adventurer = adventurers.get(i);
                break;
            }
        }
        try {
            return adventurer;
        } catch (Exception e) {
            System.out.println("Character can not be found with the following name: " + name);
            e.printStackTrace();
            return null;
        }

    }

    public static List<Adventurer> getAllAdventurer() throws IOException {
        List<Adventurer> adventurers;
        adventurers = objectMapper.readValue(new File(SAVE_FILE_PATH), new TypeReference<List<Adventurer>>() {
        });
        return adventurers;
    }

    public static void deleteAdventurer(String name) throws IOException {
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers = getAllAdventurer();

        boolean found = false;
        for (int i = 0, end = adventurers.size(); i < end; i++) {
            if (adventurers.get(i).getName().equals(name)) {
                adventurers.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(SAVE_FILE_PATH), adventurers);
        } else {
            System.out.println("There were no character to delete. The name was " + name);
        }
    }
}
