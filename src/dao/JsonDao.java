/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class JsonDao {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SAVE_FILE_PATH = "src/files/savedCharacters.json";
    private static final String CASTE_FILE_PATH = "src/files/CasteLeveling.json";

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

    public static void saveCharAttributes() throws IOException {
        CastesDao castesDao = new CastesDao();

        /*
         -----------------------------------------
         Saving caste XP levels-------------------
         -----------------------------------------
        
         String[] races = {"elf", "halfElf", "dwarf", "orc"};
         String[] castes = {"fighter", "gladiator", "headhunter", "knight", "thief", "bard", "priest", "paladin", "martialartist", "samurai", "witch", "warlock", "firemage", "sorcerer"};
         String[] attributess = {"Strength", "Agility", "Dexterity", "Fitness", "Health", "Intelligence", "Beauty", "Willpower", "Astral"};
         int[] levels = {1,2,3,4,5,6,7,8,9,10,11,12,13};
         String value = "";
         StringBuilder toWrite = new StringBuilder("{");
         String jsonQuote = "\"";

         for (int x = 0, length = castes.length; x < length; x++) {
         toWrite.append(jsonQuote).append(castes[x]).append(jsonQuote).append(":{");
         for (int i = 0, end = levels.length; i < end; i++) {
         value = castesDao.getAttributesMode(castes[x], i);
         toWrite.append(jsonQuote).append(levels[i]).append(jsonQuote).append(":");
         toWrite.append(jsonQuote).append(value).append(jsonQuote);
         if (i == end - 1) {
         toWrite.append("},");
         } else {
         toWrite.append(",");
         }
         }
         }
         toWrite.append("}");
         toWrite.deleteCharAt(toWrite.lastIndexOf(","));
         Object json = objectMapper.readValue(toWrite.toString(), Object.class);

         objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(CASTE_FILE_PATH), json);
         */
    }

    public void saveToJson(String path, Object saving) throws IOException {

        File file = new File(path);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, saving);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
