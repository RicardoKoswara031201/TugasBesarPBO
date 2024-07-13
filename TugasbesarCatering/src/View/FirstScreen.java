/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Asus
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstScreen implements ActionListener {

    JFrame frame = new JFrame("WELCOME TO CATERING");
    JPanel menu = new JPanel();
    JButton loginButton = new JButton("LOGIN");
    JButton registerButton = new JButton("REGISTER");

    public FirstScreen() {
        frame.setLayout(null);
        frame.setSize(1200, 620);
        frame.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.setBounds(0, 0, 1200, 620);
        menu.setBackground(Color.ORANGE);

        loginButton.setBounds(500, 250, 200, 40);
        registerButton.setBounds(500, 300, 200, 40);

        menu.add(loginButton);
        menu.add(registerButton);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        frame.add(menu);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("LOGIN")) {
            frame.setVisible(false);
            new LoginScreen();
        }
        if (command.equals("REGISTER")) {
            frame.setVisible(false);
            new RegistrationScreen();
        }
    }

    public static void main(String[] args) {
        new FirstScreen();
    }
}