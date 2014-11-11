package dao;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import rpg.CharAttributes;

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
    private static final String CASTE_THROW_FILE = "src/files/CasteThrowModes.json";
    private static final String CASTE_BASE_FILE = "src/files/CasteBaseValues.json";

    public static String getAttributesMode(String caste, CharAttributes attribute) throws IOException {
        String mode = null;
        JsonNode root = objectMapper.readValue(new File(CASTE_THROW_FILE), JsonNode.class);
        JsonNode casteNode = root.path(caste);

        if (!casteNode.isMissingNode()) {
            mode = casteNode.path(attribute.toString()).textValue();
        } else {
            System.out.println("No such caste: " + caste);
        }
        return mode;
    }

    public static Object[] getCasteBaseValues(String caste) throws IOException {

        Object[] baseValues = new Object[10];
        Object base;

        JsonNode root = objectMapper.readValue(new File(CASTE_BASE_FILE), JsonNode.class);
        JsonNode casteNode = root.path(caste);

        if (!casteNode.isMissingNode()) {
            int i = 0;
            for (Iterator<JsonNode> it = casteNode.iterator(); it.hasNext();) {
                JsonNode node = it.next();
                if (node.isNumber()) {
                    base = node.intValue();
                } else {
                    base = node.textValue();
                }

                baseValues[i++] = base;
            }
        }
        return baseValues;
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
