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
import Controller.ControllerUser;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationScreen implements ActionListener {

    JFrame frame = new JFrame("REGISTER");
    JPanel menu = new JPanel();

    JLabel labUsername = new JLabel("Username");
    JLabel labPassword = new JLabel("Password");
    JLabel labPhone = new JLabel("No Telepon");
    JLabel labRegion = new JLabel("Region");
    JLabel labArea = new JLabel("Area");

    JTextField textUsername = new JTextField();
    JPasswordField textPassword = new JPasswordField();
    JTextField textPhone = new JTextField();

    String[] regions = {"Region 1", "Region 2", "Region 3"};
    JComboBox<String> comboRegion = new JComboBox<>(regions);
    JComboBox<String> comboArea = new JComboBox<>();

    JButton register = new JButton("REGISTER");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    ControllerUser controlUser = new ControllerUser(); // User controller

    public RegistrationScreen() {
        // Set up the frame
        frame.setLayout(null);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // Set up the menu panel
        menu.setLayout(null);
        menu.setBounds(0, 0, 800, 500);
        menu.setBackground(Color.ORANGE);

        // Set up components' bounds and add them to the menu panel
        labUsername.setBounds(50, 50, 100, 30);
        labPassword.setBounds(50, 100, 150, 30);
        labPhone.setBounds(50, 150, 150, 30);
        labRegion.setBounds(50, 200, 150, 30);
        labArea.setBounds(50, 250, 150, 30);

        textUsername.setBounds(200, 50, 150, 30);
        textPassword.setBounds(200, 100, 150, 30);
        textPhone.setBounds(200, 150, 150, 30);

        comboRegion.setBounds(200, 200, 150, 30);
        comboArea.setBounds(200, 250, 150, 30);

        register.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        showPassword.setBounds(400, 100, 150, 30); // Adjusted bounds
        showPassword.setBackground(Color.ORANGE);

        // Add components to the menu panel
        menu.add(labUsername);
        menu.add(labPassword);
        menu.add(labPhone);
        menu.add(labRegion);
        menu.add(labArea);
        menu.add(textUsername);
        menu.add(textPassword);
        menu.add(textPhone);
        menu.add(comboRegion);
        menu.add(comboArea);
        menu.add(register);
        menu.add(resetButton);
        menu.add(showPassword);

        // Add action listeners to buttons, checkbox, and combo box
        register.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        comboRegion.addActionListener(this);

        // Add menu panel to the frame
        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Initialize the area combo box
        updateAreas();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("REGISTER")) {
            String username = textUsername.getText();
            String password = new String(textPassword.getPassword());
            String phone = textPhone.getText();
            String region = (String) comboRegion.getSelectedItem();
            String area = (String) comboArea.getSelectedItem();

            // Check if any field is empty
            if (username.isEmpty() || password.isEmpty() || phone.isEmpty() || region.isEmpty() || area.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi");
                return;
            }

            // Add user registration logic here
            // For example, save the user data to the database
            User newUser = new User();
            newUser.setNama(username);
            newUser.setPassword(password);
            newUser.setNoTelepon(phone);
            newUser.setDaerah(area);
            newUser.setRegion(region);

            boolean success = controlUser.insertNewUser(newUser);

            if (success) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                frame.setVisible(false);
                new LoginScreen(); // Redirect to login screen after successful registration
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        }

        if (command.equals("RESET")) {
            // Clear all fields
            textUsername.setText("");
            textPassword.setText("");
            textPhone.setText("");
            comboRegion.setSelectedIndex(0);
            comboArea.setSelectedIndex(0);
        }

        if (command.equals("Show Password")) {
            // Show or hide the password
            if (showPassword.isSelected()) {
                textPassword.setEchoChar((char) 0);
            } else {
                textPassword.setEchoChar('*');
            }
        }

        if (ae.getSource() == comboRegion) {
            // Update areas when region is changed
            updateAreas();
        }
    }

    private void updateAreas() {
        comboArea.removeAllItems();
        String selectedRegion = (String) comboRegion.getSelectedItem();

        if (selectedRegion.equals("Region 1")) {
            comboArea.addItem("Cimahi");
            comboArea.addItem("Jendral Sudirman");
            comboArea.addItem("Buah Batu");
        } else if (selectedRegion.equals("Region 2")) {
            comboArea.addItem("Mangga");
            comboArea.addItem("Lemon");
            comboArea.addItem("Anggur");
        } else if (selectedRegion.equals("Region 3")) {
            comboArea.addItem("Cibadak");
            comboArea.addItem("Pagarsih");
            comboArea.addItem("Jamika");
        }
    }

    public static void main(String[] args) {
        new RegistrationScreen();
    }
}