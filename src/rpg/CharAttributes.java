package rpg;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dszabo1
 */
public enum CharAttributes {

    STRENGTH(0), AGILITY(1), DEXTERITY(2), FITNESS(3), HEALTH(4), INTELLIGENCE(5), BEAUTY(6), WILLPOWER(7), ASTRAL(8);

    private CharAttributes(int value) {
        code = value;
    }

    int code;
}
