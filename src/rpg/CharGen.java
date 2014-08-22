package rpg;

import dao.RacesDao;
import dao.CastesDao;
import java.io.IOException;
import java.util.Scanner;
import objects.Adventurer;

public class CharGen {

    Scanner scanner = new Scanner(System.in);
    private String charName, charGender, charRace, charCaste,charAlignment;
    private String[] attributeNames = new String[]{"Strength", "Agility", "Dexterity", "Fitness", "Health", "Intelligence", "Beauty", "Willpower", "Astral"};
    private int[] attributes = new int[9];
    private int charAge;

    public Adventurer buildCharacter() throws IOException, Exception {

        test();

//        setName();
//        setGender();
//        setAge();
//        setRace();
//        setCaste();
//        setAlignment();
        setAttributes();

        Adventurer adventurer = new Adventurer(
                attributes[0], attributes[1], attributes[2],
                attributes[3], attributes[4], attributes[5],
                attributes[6], attributes[7], attributes[8],
                charName, charGender, charAge,
                charRace, charCaste, charAlignment);

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

    public void setAttributes() throws IOException, Exception {
        CastesDao castes = new CastesDao();
        RacesDao races = new RacesDao();
        int value, max;

        for (int count = 0; count < 9; count++) {
            value = 0;
            System.out.println(attributeNames[count]);

            //képesség kidobása
            value = RollDice.roll(castes.getAttributesMode(charCaste, count));
            //faji módosító hozzáadása
            value += races.getAttributeModifierForRace(charRace, count);
            //faji maximum ellenőrzése
            max = races.getMaxAttributeValueForRace(charRace, count);
            if (value > max) {
                value = max;
            }
            //eredmény rögzítése
            attributes[count] = value;

            System.out.println(attributes[count]);
            System.out.println("");
            //eredmény kiírása
//            System.out.printf("%15s%10s\n", attributeNames[count], attributes[count]);
        }
    }
}
