package rpg;

import dao.RacesDao;
import dao.CastesDao;
import java.io.IOException;
import java.util.Scanner;
import objects.Adventurer;

public class CharGen {

    Scanner scanner = new Scanner(System.in);
    private static String charName, charGender, charRace = "elf", charCaste = "fighter", charAlignment;
    private String[] attributeNames = new String[]{"Strength", "Agility", "Dexterity", "Fitness", "Health", "Intelligence", "Beauty", "Willpower", "Astral"};
    private static int[] attributes = new int[9];
    private int charAge;

    public Adventurer buildNewCharacter() throws IOException, Exception {

        setName();
        setGender();
        setAge();
        setRace();
        setCaste();
        setAlignment();
        setAttributes();

        Adventurer adventurer = new Adventurer(
                attributes[0], attributes[1], attributes[2],
                attributes[3], attributes[4], attributes[5],
                attributes[6], attributes[7], attributes[8],
                charName, charGender, charAge,
                charRace, charCaste, charAlignment);
        adventurer = setCombatValues(adventurer);

        return adventurer;
    }

    private void test() {
        charName = "Ranagol";
        charGender = "male";
        charAge = 35;
        charRace = "elf";
        charCaste = "headhunter";
        charAlignment = "Halál/Rend";
    }

    private void setName() {

        System.out.print("Please enter your characters name: ");
        charName = scanner.nextLine();
    }

    private void setGender() {

        System.out.print("Please enter your characters gender(male/female): ");
        charGender = scanner.nextLine();
    }

    private void setAge() {

        System.out.print("Please enter your characters age: ");
        charAge = scanner.nextInt();
    }

    private void setRace() {

        System.out.print("Please enter your characters race(human/elf/half elf/dwarf/orc): ");
        charRace = scanner.nextLine();
    }

    private void setCaste() {

        System.out.print("Please enter your characters caste: ");
        charCaste = scanner.nextLine();
    }

    private void setAlignment() {
        System.out.print("Please enter your characters alignment(Chaos/Law,Life/Death): ");
        charAlignment = scanner.nextLine();
    }

    public String getName() {
        return charName;
    }

    public String getGender() {
        return charGender;
    }

    public int getAge() {
        return charAge;
    }

    public String getRace() {
        return charRace;
    }

    public String getCaste() {
        return charCaste;
    }

    public static void setAttributes() throws IOException, Exception {
        int value, max, i = 0;
        for (CharAttributes attribute : CharAttributes.values()) {

            value = RollDice.roll(CastesDao.getAttributesMode(charCaste, attribute));

            value += RacesDao.getRaceAttributeModifier(charRace, attribute);

            max = RacesDao.getRaceMaxAttributeValue(charRace, attribute);
            if (value > max) {
                value = max;
            }

            attributes[i++] = value;
        }
    }

    private static Adventurer setCombatValues(Adventurer adventurer) throws IOException {
        Object[] bases = CastesDao.getCasteBaseValues(adventurer.getCaste());
        adventurer.setInitValue((int) bases[0]
                + adventurer.getAgility() - 10
                + adventurer.getDexterity() - 10);
        adventurer.setAttackValue((int) bases[1]
                + adventurer.getStrength() - 10
                + adventurer.getAgility() - 10
                + adventurer.getDexterity() - 10);
        adventurer.setDefenseValue((int) bases[2]
                + adventurer.getAgility() - 10
                + adventurer.getDexterity() - 10);
        adventurer.setTargetValue((int) bases[3]
                + adventurer.getDexterity() - 10);
        adventurer.setModPerLvl(bases[4].toString());
        adventurer.setKp((int) bases[5]);
        adventurer.setKpPerLvl((int) bases[6]);
        adventurer.setÉp((int) bases[7]
                + adventurer.getHealth());
        adventurer.setFp((int) bases[8]
                + adventurer.getFitness() - 10
                + adventurer.getWillpower() - 10);
        adventurer.setFpPerLvl(bases[9].toString());
        return adventurer;
    }
}
