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
import objects.Armor;

/**
 *
 * @author dszabo1
 */
public class ArmorDao extends ObjectDao {

    private static final String ARMOR_FILE_PATH = "src/files/Armors.json";

    public static void saveArmor(Armor armor) throws IOException {
        try {
            List<Armor> armors = new ArrayList<>();
            armors = getAllArmor();

            boolean found = false;
            for (int i = 0, end = armors.size(); i < end; i++) {
                if (armors.get(i).getName().equals(armor.getName())) {
                    armors.set(i, armor);
                    found = true;
                    break;
                }
            }
            if (!found) {
                armors.add(armor);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARMOR_FILE_PATH), armors);
        } catch (IOException ex) {
            Logger.getLogger(JsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Armor getArmor(String armorName) throws IOException {
        List<Armor> armors = new ArrayList<>();
        Armor armor = null;
        armors = getAllArmor();

        for (int i = 0, end = armors.size(); i < end; i++) {
            if (armors.get(i).getName().equals(armorName)) {
                armor = armors.get(i);
                break;
            }
        }
        try {
            return armor;
        } catch (Exception e) {
            System.out.println("Armor can not be found with the following name: " + armorName);
            e.printStackTrace();
            return null;
        }
    }

    private static List<Armor> getAllArmor() throws IOException {
        List<Armor> armors;
        armors = objectMapper.readValue(new File(ARMOR_FILE_PATH), new TypeReference<List<Armor>>() {
        });
        return armors;
    }

    public static void deleteArmor(String name) throws IOException {
        List<Armor> armors = new ArrayList<>();
        armors = getAllArmor();

        boolean found = false;
        for (int i = 0, end = armors.size(); i < end; i++) {
            if (armors.get(i).getName().equals(name)) {
                armors.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARMOR_FILE_PATH), armors);
        } else {
            System.out.println("There were no armor to delete. The name was " + name);
        }
    }
}
