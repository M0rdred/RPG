/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.xml.bind.annotation.*;

/**
 *
 * @author dszabo1
 */
@XmlType(propOrder = {"name", "attackNumber", "ké", "té", "vé", "cé", "damage", "type"})
public class Weapon {

    private String name;
    private String damage;
    private String type;
    private String range;
    private int Ké;
    private int Té;
    private int Vé;
    private int Cé;
    private double attackNumber;

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Weapon() {
        Té = 0;
        Vé = 0;
        Cé = 0;
    }

    public Weapon(int Ké, int Cé) {
        this.Ké = Ké;
        this.Cé = Cé;
    }

    public Weapon(int Ké, int Té, int Vé) {
        this.Ké = Ké;
        this.Té = Té;
        this.Vé = Vé;
    }

    public Weapon(int Ké, int Té, int Vé, int Cé, double attackNumber, String name, String damage, String type) {
        this.Ké = Ké;
        this.Té = Té;
        this.Vé = Vé;
        this.Cé = Cé;
        this.attackNumber = attackNumber;
        this.name = name;
        this.damage = damage;
        this.type = type;
    }//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getKé() {
        return Ké;
    }

    public void setKé(int Ké) {
        this.Ké = Ké;
    }

    public int getTé() {
        return Té;
    }

    public void setTé(int Té) {
        this.Té = Té;
    }

    public int getVé() {
        return Vé;
    }

    public void setVé(int Vé) {
        this.Vé = Vé;
    }

    public int getCé() {
        return Cé;
    }

    public void setCé(int Cé) {
        this.Cé = Cé;
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
