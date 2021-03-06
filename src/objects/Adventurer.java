/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dao.WeaponDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import rpg.RollDice;

/**
 *
 * @author dszabo1
 */
@JsonPropertyOrder({"name", "gender", "age", "race", "caste", "alignment",
    "Strength", "Agility", "Dexterity", "Fitness", "Health", "Intelligence", "Beauty", "Willpower", "Astral",
    "initValue", "attackValue", "defenseValue", "targetValue", "FpMax", "ÉpMax", "PsiMax", "MpMax", "Kp",
    "modPerLvl", "FpPerLvl", "psiPerLvl", "MpPerLvl", "KpPerLvl",
    "Tp",
    "weapons", "armor"})
public class Adventurer {

    private String name;

    private String gender;

    private int age;

    private String race;

    private String caste;

    private String alignment;

    private int Strength;

    private int Agility;

    private int Dexterity;

    private int Fitness;

    private int Health;

    private int Intelligence;

    private int Beauty;

    private int Willpower;

    private int Astral;

    private int initValue;

    private int attackValue;

    private int defenseValue;

    private int targetValue;

    private int Level;    

    private String modPerLvl;

    private int PsiPerLvl;

    private int MpPerLvl;

    private int KpPerLvl;

    private String FpPerLvl;

    @JsonIgnore
    private int Fp;

    @JsonIgnore
    private int Ép;

    @JsonIgnore
    private int Mp;

    @JsonIgnore
    private int Psi;

    private int FpMax;

    private int ÉpMax;

    private int MpMax;

    private int PsiMax;

    private int Kp;

    private int Xp;

    private int combatModifier[] = new int[5];  //initValue, attackValue, defenseValue, targetValue, Sebzés       

    @JsonIgnore
    private boolean alive;

    @JsonIgnore
    private boolean conscious;

    private List<Weapon> weapons = new ArrayList<>();

    @JsonIgnore
    private Weapon rightWeapon = null;

    @JsonIgnore
    private Weapon leftWeapon = null;

    private Armor armor;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Adventurer(int Strength, int Agility, int Dexterity, int Fitness, int Health, int Intelligence, int Beauty, int Willpower, int Astral, String name, String gender, int age, String race, String caste, String alignment) {
        this.Strength = Strength;
        this.Agility = Agility;
        this.Dexterity = Dexterity;
        this.Fitness = Fitness;
        this.Health = Health;
        this.Intelligence = Intelligence;
        this.Beauty = Beauty;
        this.Willpower = Willpower;
        this.Astral = Astral;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.race = race;
        this.caste = caste;
        this.alignment = alignment;
        conscious = true;
        alive = true;
    }

    public Adventurer() {
    }
    //</editor-fold>

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addWeapon(String weaponName) throws IOException {
        weapons.add(WeaponDao.getWeapon(weaponName));
    }

    public void removeWeapon(String name) {
        for (int i = 0; i < weapons.size(); i++) {
            if (name.equals(weapons.get(i).getName())) {
                weapons.remove(i);
                break;
            }
        }
    }

    public void useWeapon(String name) {
        for (int i = 0; i < weapons.size(); i++) {
            if (name.equals(weapons.get(i).getName())) {
                rightWeapon = weapons.get(i);
                break;
            }
        }
    }

    public void sheatheWeapon() {
        rightWeapon = null;
    }

    public String woundTaken(int épDamage, int fpDamage) {
        if (épDamage != 0) {
            épDamage -= armor.getSfé();
            Ép -= épDamage;
            Fp -= épDamage * 2;
        } else {
            fpDamage -= armor.getSfé();
            Fp -= fpDamage;
        }

        if (Fp < 0) {
            Ép -= Fp * -1;
            Fp = 0;
        }

        if (Ép < 0) {
            Ép = 0;
        }

        if (Fp == 0) {
            conscious = false;
        }

        if (Ép < 1) {
            alive = false;
        }

        if (!alive) {
            return "dead";
        } else if (!conscious) {
            return "unconscious";
        } else {
            return "ready";
        }
    }

    public void fullRestore() {
        Fp = FpMax;
        Ép = ÉpMax;
        Mp = MpMax;
        Psi = PsiMax;
    }

    public int calculateInitValue() {
        int actualInitValue = initValue;
        if (Agility > 10) {
            actualInitValue += Agility - 10;
        }
        if (Dexterity > 10) {
            actualInitValue += Dexterity - 10;
        }
        if (rightWeapon != null) {
            actualInitValue += rightWeapon.getInitValue();
        }

        actualInitValue += RollDice.roll("k10");
        return actualInitValue;
    }

    public int calculateAttackValue() {
        int actualAttackValue = attackValue;

        if (Strength > 10) {
            actualAttackValue += Strength - 10;
        }
        if (Agility > 10) {
            actualAttackValue += Agility - 10;
        }
        if (Dexterity > 10) {
            actualAttackValue += Dexterity - 10;
        }
        if (rightWeapon != null) {
            actualAttackValue += rightWeapon.getAttackValue();
        }
        actualAttackValue += RollDice.roll("k100");
        return actualAttackValue;
    }

    public int calculateDefenseValue() {
        int actualDefenseValue = defenseValue;
        if (Agility > 10) {
            actualDefenseValue += Agility - 10;
        }
        if (Dexterity > 10) {
            actualDefenseValue += Dexterity - 10;
        }
        if (rightWeapon != null) {
            actualDefenseValue += rightWeapon.getDefenseValue();
        }
        if (leftWeapon != null) {
            actualDefenseValue += leftWeapon.getDefenseValue();
        }
        return actualDefenseValue;
    }

    public int calculateTargetValue() {
        int actualTargetValue = targetValue;
        if (Dexterity > 10) {
            actualTargetValue += Dexterity - 10;
        }
        if (rightWeapon != null) {
            actualTargetValue += rightWeapon.getTargetValue();
        }
        return actualTargetValue;
    }

    public int calculateDamage() {
        int damage = 0;
        if (rightWeapon != null) {
            damage = RollDice.roll(rightWeapon.getDamage());
        }
        return damage;
    }

    @JsonIgnore
    public double getAttackNumbers() {
        return rightWeapon.getAttackNumber();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getStrength() {
        return Strength;
    }

    public void setStrength(int Strength) {
        this.Strength = Strength;
    }

    public int getAgility() {
        return Agility;
    }

    public void setAgility(int Agility) {
        this.Agility = Agility;
    }

    public int getDexterity() {
        return Dexterity;
    }

    public void setDexterity(int Dexterity) {
        this.Dexterity = Dexterity;
    }

    public int getFitness() {
        return Fitness;
    }

    public void setFitness(int Fitness) {
        this.Fitness = Fitness;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        this.Health = Health;
    }

    public int getIntelligence() {
        return Intelligence;
    }

    public void setIntelligence(int Intelligence) {
        this.Intelligence = Intelligence;
    }

    public int getBeauty() {
        return Beauty;
    }

    public void setBeauty(int Beauty) {
        this.Beauty = Beauty;
    }

    public int getWillpower() {
        return Willpower;
    }

    public void setWillpower(int Willpower) {
        this.Willpower = Willpower;
    }

    public int getAstral() {
        return Astral;
    }

    public void setAstral(int Astral) {
        this.Astral = Astral;
    }

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
    
    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public String getModPerLvl() {
        return modPerLvl;
    }

    public void setModPerLvl(String modPerLvl) {
        this.modPerLvl = modPerLvl;
    }

    public int getPsiPerLvl() {
        return PsiPerLvl;
    }

    public void setPsiPerLvl(int PsiPerLvl) {
        this.PsiPerLvl = PsiPerLvl;
    }

    public int getMpPerLvl() {
        return MpPerLvl;
    }

    public void setMpPerLvl(int MpPerLvl) {
        this.MpPerLvl = MpPerLvl;
    }

    public int getKpPerLvl() {
        return KpPerLvl;
    }

    public void setKpPerLvl(int KpPerLvl) {
        this.KpPerLvl = KpPerLvl;
    }

    public String getFpPerLvl() {
        return FpPerLvl;
    }

    public void setFpPerLvl(String FpPerLvl) {
        this.FpPerLvl = FpPerLvl;
    }

    public int getFp() {
        return Fp;
    }

    public void setFp(int Fp) {
        this.Fp = Fp;
    }

    public int getÉp() {
        return Ép;
    }

    public void setÉp(int Ép) {
        this.Ép = Ép;
    }

    public int getMp() {
        return Mp;
    }

    public void setMp(int Mp) {
        this.Mp = Mp;
    }

    public int getPsi() {
        return Psi;
    }

    public void setPsi(int Psi) {
        this.Psi = Psi;
    }

    public int getFpMax() {
        return FpMax;
    }

    public void setFpMax(int FpMax) {
        this.FpMax = FpMax;
    }

    public int getÉpMax() {
        return ÉpMax;
    }

    public void setÉpMax(int ÉpMax) {
        this.ÉpMax = ÉpMax;
    }

    public int getMpMax() {
        return MpMax;
    }

    public void setMpMax(int MpMax) {
        this.MpMax = MpMax;
    }

    public int getPsiMax() {
        return PsiMax;
    }

    public void setPsiMax(int PsiMax) {
        this.PsiMax = PsiMax;
    }

    public int getKp() {
        return Kp;
    }

    public void setKp(int Kp) {
        this.Kp = Kp;
    }

    public int getXp() {
        return Xp;
    }

    public void setXp(int Xp) {
        this.Xp = Xp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

//    public void setArmor(String armorName) {
//        if (armorDao == null) {
//            armorDao = new ArmorDao();
//        }
//        this.armor = armorDao.getArmor(armorName);
//    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isConscious() {
        return conscious;
    }

    public void setConscious(boolean conscious) {
        this.conscious = conscious;
    }//</editor-fold>
}
