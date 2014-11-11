package dao;

import com.fasterxml.jackson.databind.JsonNode;
import static dao.ObjectDao.objectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import rpg.CharAttributes;

public class RacesDao extends ObjectDao {

    private static final String RACE_MAX_VALUE_FILE = "src/files/RaceMaxAttributeValues.json";
    private static final String RACE_MODIFIER_FILE = "src/files/RaceModifiers.json";
    private static final String RACE_CASTE_CORRELATION_FILE = "src/files/RaceCasteCorrelation.json";

    public static int getRaceMaxAttributeValue(String race, CharAttributes attribute) throws IOException {
        int maxValue = 0;

        JsonNode root = objectMapper.readValue(new File(RACE_MAX_VALUE_FILE), JsonNode.class);
        JsonNode raceNode = root.path(race);

        if (!raceNode.isMissingNode()) {
            maxValue = raceNode.path(attribute.toString()).intValue();
        }

        return maxValue;
    }

    public static int getRaceAttributeModifier(String race, CharAttributes attribute) throws IOException {
        int modifier = 0;

        JsonNode root = objectMapper.readValue(new File(RACE_MODIFIER_FILE), JsonNode.class);
        JsonNode raceNode = root.path(race);

        if (!raceNode.isMissingNode()) {
            modifier = raceNode.path(attribute.toString()).intValue();
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

    public int getMaxAttributeValueForRace(String race, int attribute) {
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

    public boolean getIfRaceAllowedCaste(String caste, String race) throws IOException {
        JsonNode root = objectMapper.readValue(new File(RACE_CASTE_CORRELATION_FILE), JsonNode.class);
        JsonNode casteNode = root.path(caste);

        if (casteNode.isMissingNode()) {
            return false;
        }
        return casteNode.path(race).booleanValue();

    }
}
