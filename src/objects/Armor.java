/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author dszabo1
 */
public class Armor {

    private String name, material;
    private int Mgt, Sfé, Stp;

    public Armor(String name, String material, int Mgt, int Sfé, int Stp) {
        this.name = name;
        this.material = material;
        this.Mgt = Mgt;
        this.Sfé = Sfé;
        this.Stp = Stp;
    }

    public Armor() {
    }

    public int getMgt() {
        return Mgt;
    }

    public void setMgt(int Mgt) {
        this.Mgt = Mgt;
    }

    public int getSfé() {
        return Sfé;
    }

    public void setSfé(int Sfé) {
        this.Sfé = Sfé;
    }

    public int getStp() {
        return Stp;
    }

    public void setStp(int Stp) {
        this.Stp = Stp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
