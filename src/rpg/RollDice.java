package rpg;

import java.util.Random;
import java.util.Scanner;

public class RollDice {

    //gurítás argumentum nélkül
    public static int roll() {
        int numberOfDice, numberOfSides, numberOfTries = 1, diceModifier = 0, result = 0;
        boolean kf = false;
        StringBuilder dice = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //kockák száma
        System.out.print("Add meg, hogy hány kockával akarsz dobni: ");
        dice.append(scanner.nextInt());

        //oldalak száma
        System.out.print("Add meg a kocka oldalainak számát(6, 10): ");
        dice.append("k");
        dice.append(scanner.nextInt());

        //módosítás
        System.out.print("Add meg, hogy mennyit akarsz az eredményhez hozzáadni: ");
        diceModifier = scanner.nextInt();
        if (diceModifier < 0) {
            dice.append("-");
            dice.append(diceModifier * -1);
        } else {
            dice.append("+");
            dice.append(diceModifier);
        }

        //próbálkozások száma
        System.out.println("Add meg, hogy hányszor szeretnél próbálkozni: ");
        dice.append("(");
        dice.append(scanner.nextInt());
        dice.append("x)");

        //kf
        System.out.println("Add meg, hogy szeretnél-e különleges felkészítést(Y/N):  ");
        switch (scanner.next().toLowerCase()) {
            case "y":
            case "yes":
                dice.append("+kf");
        }

        //kockadobások végrehajtása
        System.out.println(dice);
        roll(dice.toString());

        return result;
    }

    //gurítás argumentummal
    public static int roll(String dice) {

        int numberOfDice, numberOfSides, numberOfTries = 1, diceModifier = 0, result = 0;
        boolean kf = false;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String diceName = null, modifier = null;

        //"kf" jelenléte
        if (dice.contains("+kf")) {
            kf = true;
            dice = dice.replace("+kf", "");
        }

        //több próbálkozás meghatározása
        if (dice.contains("(")) {
            numberOfTries = Integer.parseInt(dice.substring(dice.indexOf("(") + 1, dice.indexOf("x")));
            dice = dice.substring(0, dice.indexOf("("));
        }

        //meghatározni hogy "d" vagy "k" a kocka neve
        if (dice.contains("d")) {
            diceName = "d";
        } else if (dice.contains("k")) {
            diceName = "k";
        } else {
            System.out.println("Kérlek egy valós kockaazonosítót adj meg(d,k)");
        }

        //"+" vagy "-" meghatározása
        if (dice.contains("+")) {
            modifier = "+";
        } else if (dice.contains("-")) {
            modifier = "-";
        }

        //kockák számának beolvasása
        try {
            numberOfDice = Integer.parseInt(dice.substring(0, dice.indexOf(diceName)));
        } catch (NumberFormatException e) {
            numberOfDice = 1;
        }

        //oldalak számának beolvasása ha van +-
        if (modifier != null) {
            numberOfSides = Integer.parseInt(dice.substring(dice.indexOf(diceName) + 1, dice.indexOf(modifier)));
        } //oldalak számának beolvasása ha nincs +-
        else {
            numberOfSides = Integer.parseInt(dice.substring(dice.indexOf(diceName) + 1, dice.length()));
        }

        //módosító szám beolvasása ha van
        if (modifier != null) {
            diceModifier = Integer.parseInt(dice.substring(dice.indexOf(modifier) + 1, dice.length()));
        } else {
            diceModifier = 0;
        }

        int best;
        //kockadobások végrehajtása
        for (int x = 0; x < numberOfTries; x++) {

            best = 0;

            for (int i = 1; i <= numberOfDice; i++) {
                best += 1 + random.nextInt(numberOfSides);
            }

            //módosító hozzáadása
            if (modifier == "-") {
                best -= diceModifier;
            } else {
                best += diceModifier;
            }

            if (best > result) {
                result = best;
            }

            //különleges felkészítés
            if ((kf) && (result == 18)) {
                System.out.print("Ehhez a képességhez elérhető különleges felkészítés. Igénybe szeretnéd venni?(Y/N): ");

                String answer = scanner.nextLine();

                if (answer.equals("Y") || answer.equals("y") || answer.equals("yes")) {
                    result = rollKF();
                    kf = false;
                }
            }

        }

        return result;
    }

    public static int rollKF() {
        int result = 0;
        int kf = roll("k100");

        if ((kf > 1) && (kf < 21)) {
            result = 18 - roll("k6");
        } else if ((kf > 20) && (kf < 51)) {
            result = 17;
        } else if ((kf > 50) && (kf < 76)) {
            result = 18;
        } else if ((kf > 75) && (kf < 96)) {
            result = 19;
        } else if ((kf > 95) && (kf < 101)) {
            result = 20;
        }
        return result;
    }
}
