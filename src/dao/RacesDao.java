package dao;

import java.io.InputStream;
import java.util.Scanner;

public class RacesDao extends ObjectDao{

    public int getAttributeModifierForRace(String race, int attribute) throws Exception {

        int modifier = 0;

        InputStream iStream = getClass().getResourceAsStream("/files/RaceModifiers.txt");
        String row;
        Scanner fileScanner = new Scanner(iStream);


        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (row.contains(race)) {
                modifier = Integer.parseInt(scanRow(row, attribute));
                break;
            }
        }
        return modifier;
    }

    public int[] getAllModifiersForRace(String race) {
        int[] modifiers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        InputStream stream = getClass().getResourceAsStream("/files/RaceModifiers.txt");
        String row;
        Scanner fileScanner = new Scanner(stream);


        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (row.contains(race)) {
                for (int i = 0; i < 9; i++) {
                    modifiers[i] = Integer.parseInt(scanRow(row, i));

                }
                break;
            }
        }
        return modifiers;
    }
    
    public int getMaxAttributeValueForRace(String race, int attribute){
        int maxValue = 20;

        InputStream stream = getClass().getResourceAsStream("/files/RaceMaxAttributeValues.txt");
        String row;
        Scanner fileScanner = new Scanner(stream);


        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (row.contains(race)) {
                maxValue = Integer.parseInt(scanRow(row, attribute));
                break;
            }
        }
        return maxValue;
    }

   
}
