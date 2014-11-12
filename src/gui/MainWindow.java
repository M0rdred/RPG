/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.AdventurerDao;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objects.Adventurer;

/**
 *
 * @author dszabo1
 */
public class MainWindow extends JFrame {

    public MainWindow() throws IOException {

        Adventurer adventurer = AdventurerDao.getAdventurer("Mordred");

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new GridLayout(33, 2));

        JLabel nameLabel = new JLabel("Name");
        JLabel genderLabel = new JLabel("Gender");
        JLabel ageLabel = new JLabel("Age");
        JLabel raceLabel = new JLabel("Race");
        JLabel casteLabel = new JLabel("Caste");
        JLabel alignmentLabel = new JLabel("Alignment");

        JLabel strengthLabel = new JLabel("Strength");
        JLabel agilityLabel = new JLabel("Agility");
        JLabel dexterityLabel = new JLabel("Dexterity");
        JLabel fitnessLabel = new JLabel("Fitness");
        JLabel healthLabel = new JLabel("Health");
        JLabel intelligenceLabel = new JLabel("Intelligence");
        JLabel beautyLabel = new JLabel("Beauty");
        JLabel willpowerLabel = new JLabel("Willpower");
        JLabel astralLabel = new JLabel("Astral");

        JLabel initValueLabel = new JLabel("Initiation");
        JLabel attackValueLabel = new JLabel("Attacking");
        JLabel defenseValueLabel = new JLabel("Defending");
        JLabel targetValueLabel = new JLabel("Targeting");
        JLabel modPerLvlLabel = new JLabel("Combat modifier per level");

        JLabel fpLabel = new JLabel("Fp");
        JLabel fpPerLvlLabel = new JLabel("Fp per level");
        JLabel fpMaxLabel = new JLabel("Max Fp");
        JLabel épLabel = new JLabel("Ép");
        JLabel épMaxLabel = new JLabel("Max Ép");

        JLabel psiLabel = new JLabel("Psi points");
        JLabel psiPerLvlLabel = new JLabel("Psi points per level");
        JLabel psiMaxLabel = new JLabel("Maximum psi points");
        JLabel mpLabel = new JLabel("Mana points");
        JLabel mpPerLvlLabel = new JLabel("Mana points per level");
        JLabel mpMaxLabel = new JLabel("Maximum mana points");

        JLabel kpLabel = new JLabel("Skill points");
        JLabel kpPerLvlLabel = new JLabel("Skill points per level");

        JTextField nameField = new JTextField(adventurer.getName());
        JTextField genderField = new JTextField(adventurer.getGender());
        JTextField ageField = new JTextField(Integer.toString(adventurer.getAge()));
        JTextField raceField = new JTextField(adventurer.getRace());
        JTextField casteField = new JTextField(adventurer.getCaste());
        JTextField alignmentField = new JTextField(adventurer.getAlignment());

        JTextField strengthField = new JTextField(Integer.toString(adventurer.getStrength()));
        JTextField agilityField = new JTextField(Integer.toString(adventurer.getAgility()));
        JTextField dexterityField = new JTextField(Integer.toString(adventurer.getDexterity()));
        JTextField fitnessField = new JTextField(Integer.toString(adventurer.getFitness()));
        JTextField healthField = new JTextField(Integer.toString(adventurer.getHealth()));
        JTextField intelligenceField = new JTextField(Integer.toString(adventurer.getIntelligence()));
        JTextField beautyField = new JTextField(Integer.toString(adventurer.getBeauty()));
        JTextField willpowerField = new JTextField(Integer.toString(adventurer.getWillpower()));
        JTextField astralField = new JTextField(Integer.toString(adventurer.getAstral()));

        JTextField initValueField = new JTextField(Integer.toString(adventurer.getInitValue()));
        JTextField attackValueField = new JTextField(Integer.toString(adventurer.getAttackValue()));
        JTextField defenseValueField = new JTextField(Integer.toString(adventurer.getDefenseValue()));
        JTextField targetValueField = new JTextField(Integer.toString(adventurer.getTargetValue()));
        JTextField modPerLvlField = new JTextField(adventurer.getModPerLvl());

        JTextField fpField = new JTextField(Integer.toString(adventurer.getFp()));
        JTextField fpPerLvlField = new JTextField(adventurer.getFpPerLvl());
        JTextField fpMaxField = new JTextField(Integer.toString(adventurer.getFpMax()));
        JTextField épField = new JTextField(Integer.toString(adventurer.getÉp()));
        JTextField épMaxField = new JTextField(Integer.toString(adventurer.getÉpMax()));

        JTextField psiField = new JTextField(Integer.toString(adventurer.getPsi()));
        JTextField psiPerLvlField = new JTextField(Integer.toString(adventurer.getPsiPerLvl()));
        JTextField psiMaxField = new JTextField(Integer.toString(adventurer.getPsiMax()));
        JTextField mpField = new JTextField(Integer.toString(adventurer.getMp()));
        JTextField mpPerLvlField = new JTextField(Integer.toString(adventurer.getMpPerLvl()));
        JTextField mpMaxField = new JTextField(Integer.toString(adventurer.getMpMax()));

        JTextField kpField = new JTextField(Integer.toString(adventurer.getKp()));
        JTextField kpPerLvlField = new JTextField(Integer.toString(adventurer.getKpPerLvl()));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(raceLabel);
        panel.add(raceField);
        panel.add(casteLabel);
        panel.add(casteField);
        panel.add(alignmentLabel);
        panel.add(alignmentField);
        panel.add(agilityLabel);
        panel.add(agilityField);

        panel.add(strengthLabel);
        panel.add(strengthField);
        panel.add(agilityLabel);
        panel.add(agilityField);
        panel.add(dexterityLabel);
        panel.add(dexterityField);
        panel.add(fitnessLabel);
        panel.add(fitnessField);
        panel.add(healthLabel);
        panel.add(healthField);
        panel.add(intelligenceLabel);
        panel.add(intelligenceField);
        panel.add(beautyLabel);
        panel.add(beautyField);
        panel.add(willpowerLabel);
        panel.add(willpowerField);
        panel.add(astralLabel);
        panel.add(astralField);

        panel.add(initValueLabel);
        panel.add(initValueField);
        panel.add(attackValueLabel);
        panel.add(attackValueField);
        panel.add(defenseValueLabel);
        panel.add(defenseValueField);
        panel.add(targetValueLabel);
        panel.add(targetValueField);
        panel.add(modPerLvlLabel);
        panel.add(modPerLvlField);

        panel.add(fpLabel);
        panel.add(fpField);
        panel.add(fpPerLvlLabel);
        panel.add(fpPerLvlField);
        panel.add(fpMaxLabel);
        panel.add(fpMaxField);
        panel.add(épLabel);
        panel.add(épField);
        panel.add(épMaxLabel);
        panel.add(épMaxField);

        panel.add(psiLabel);
        panel.add(psiField);
        panel.add(psiPerLvlLabel);
        panel.add(psiPerLvlField);
        panel.add(psiMaxLabel);
        panel.add(psiMaxField);
        panel.add(mpLabel);
        panel.add(mpField);
        panel.add(mpPerLvlLabel);
        panel.add(mpPerLvlField);
        panel.add(mpMaxLabel);
        panel.add(mpMaxField);

        panel.add(kpLabel);
        panel.add(kpField);
        panel.add(kpPerLvlLabel);
        panel.add(kpPerLvlField);

        setTitle("M.A.G.U.S. - avagy a Kalandorok Krónikái");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
