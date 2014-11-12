/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dszabo1
 */
public class MainWindow extends JFrame {

    public MainWindow() {

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new GridLayout(15, 2, 5, 5));
        
        JLabel name = new JLabel("Name");
        JLabel gender = new JLabel("Gender");
        JLabel age = new JLabel("Age");
        JLabel race = new JLabel("Race");
        JLabel caste = new JLabel("Caste");
        JLabel alignment = new JLabel("Alignment");
        JLabel initValue = new JLabel("Initiation");
        JLabel attackValue = new JLabel("Attacking");
        JLabel defenseValue = new JLabel("Defending");
        JLabel targetValue = new JLabel("Targeting");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");
        JLabel name = new JLabel("Name");

        setTitle("M.A.G.U.S. - avagy a Kalandorok Krónikái");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
