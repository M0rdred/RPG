/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author dszabo1
 */
public class Weapon {

    private String name;
    private String damage;
    private String type;
    private String range;
    private int initValue;
    private int attackValue;
    private int defenseValue;
    private int targetValue;
    private double attackNumber;

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Weapon() {
        attackValue = 0;
        defenseValue = 0;
        targetValue = 0;
    }

    public Weapon(int initValue, int targetValue) {
        this.initValue = initValue;
        this.targetValue = targetValue;
    }

    public Weapon(int initValue, int attackValue, int defenseValue) {
        this.initValue = initValue;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
    }

    public Weapon(int initValue, int attackValue, int defenseValue, int targetValue, double attackNumber, String name, String damage, String type) {
        this.initValue = initValue;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.targetValue = targetValue;
        this.attackNumber = attackNumber;
        this.name = name;
        this.damage = damage;
        this.type = type;
    }//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getInitValue() {
        return initValue;
    }

    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public double getAttackNumber() {
        return attackNumber;
    }

    public void setAttackNumber(double attackNumber) {
        this.attackNumber = attackNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }//</editor-fold>
}
