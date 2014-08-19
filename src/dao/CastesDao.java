package dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import static dao.ObjectDao.objectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CastesDao extends ObjectDao {

    final int STRENGTH = 0,
            AGILITY = 1,
            DEXTERITY = 2,
            FITNESS = 3,
            HEALTH = 4,
            WISDOM = 5,
            BEAUTY = 6,
            WILLPOWER = 7,
            ASTRAL = 8;

    private static final String LEVELS_FILE = "src/files/CasteLeveling.json";

    public String getAttributesMode(String caste, int attribute) throws IOException {
        InputStream stream = getClass().getResourceAsStream("/files/CasteLeveling.txt");
        String row, mode = null;
        Scanner fileScanner = new Scanner(stream);

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();
            if (row.contains(caste)) {
                mode = scanRow(row, attribute);
                break;
            }
        }

        return mode;
    }

    public static Integer getLevelForCaste(int xp, String caste) throws IOException {

        int lvl = 1;
        JsonNode root = objectMapper.readValue(new File(LEVELS_FILE), JsonNode.class);
        JsonNode casteNode = root.path(caste);

        if (!casteNode.isMissingNode()) {

            for (int i = 1;; i++) {

                if (i < 13) {
                    if (xp <= casteNode.path(String.valueOf(i)).intValue()) {
                        lvl = i;
                        break;
                    }
                } else {
                    lvl = 13;
                    int tempLevel = casteNode.path(String.valueOf("12")).intValue();
                    int perLevel = casteNode.path(String.valueOf("13")).intValue();

                    while (tempLevel + perLevel < xp) {
                        tempLevel += perLevel;
                        lvl++;
                    }
                    break;
                }
            }
            return lvl;
        } else {
            System.out.println("No such class found: " + caste + ". You may check spelling!");
            return null;
        }
    }
}
