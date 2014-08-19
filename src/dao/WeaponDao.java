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
import objects.Weapon;

/**
 *
 * @author dszabo1
 */
public class WeaponDao extends ObjectDao {

    private static final String WEAPON_SAVE_FILE = "src/files/Weapons.json";

    public static void saveWeapon(Weapon weapon) throws IOException {
        try {
            List<Weapon> weapons = new ArrayList<>();
            weapons = getAllWeapon();

            boolean found = false;
            for (int i = 0, end = weapons.size(); i < end; i++) {
                if (weapons.get(i).getName().equals(weapon.getName())) {
                    weapons.set(i, weapon);
                    found = true;
                    break;
                }
            }
            if (!found) {
                weapons.add(weapon);
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(WEAPON_SAVE_FILE), weapons);
        } catch (IOException ex) {
            Logger.getLogger(JsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Weapon getWeapon(String weaponName) throws IOException {
        List<Weapon> weapons = new ArrayList<>();
        Weapon weapon = null;
        weapons = getAllWeapon();

        for (int i = 0, end = weapons.size(); i < end; i++) {
            if (weapons.get(i).getName().equals(weaponName)) {
                weapon = weapons.get(i);
                break;
            }
        }
        try {
            return weapon;
        } catch (Exception e) {
            System.out.println("Weapon can not be found with the following name: " + weaponName);
            e.printStackTrace();
            return null;
        }
    }

    private static List<Weapon> getAllWeapon() throws IOException {
        List<Weapon> weapons;
        weapons = objectMapper.readValue(new File(WEAPON_SAVE_FILE), new TypeReference<List<Weapon>>() {
        });
        return weapons;
    }

    public static void deleteWeapon(String name) throws IOException {
        List<Weapon> weapons = new ArrayList<>();
        weapons = getAllWeapon();

        boolean found = false;
        for (int i = 0, end = weapons.size(); i < end; i++) {
            if (weapons.get(i).getName().equals(name)) {
                weapons.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(WEAPON_SAVE_FILE), weapons);
        } else {
            System.out.println("There were no weapon to delete. The name was " + name);
        }
    }
}
