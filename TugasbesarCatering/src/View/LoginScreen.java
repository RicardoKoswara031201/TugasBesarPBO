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
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import Controller.*;

public class LoginScreen implements ActionListener, ItemListener {

    // Deklarasi komponen GUI
    JFrame frame = new JFrame("LOGIN");
    JPanel menu = new JPanel();
    JLabel labUsername = new JLabel("Username");
    JLabel labPassword = new JLabel("Password");
    JTextField textUsername = new JTextField();
    JPasswordField textPassword = new JPasswordField();
    JButton login = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton registerButton = new JButton("REGISTER");
    JCheckBox showPassword = new JCheckBox("Show Password");
    
    // Deklarasi controller untuk admin dan user
    ControllerAdmin controlAdmin = new ControllerAdmin();
    ControllerUser controlUser = new ControllerUser();

    // Konstruktor untuk mengatur tampilan login
    public LoginScreen() {
        // Pengaturan frame
        frame.setLayout(null);
        frame.setSize(1200, 620);
        frame.setLocationRelativeTo(null);

        // Pengaturan panel menu
        menu.setLayout(null);
        menu.setBounds(0, 0, 1200, 620);
        menu.setBackground(Color.ORANGE);

        // Pengaturan posisi dan ukuran komponen
        labUsername.setBounds(450, 200, 100, 30);
        labPassword.setBounds(450, 240, 150, 30);
        textUsername.setBounds(550, 200, 150, 30);
        textPassword.setBounds(550, 240, 150, 30);
        login.setBounds(450, 300, 100, 30);
        resetButton.setBounds(600, 300, 100, 30);
        registerButton.setBounds(525, 340, 100, 30);
        showPassword.setBounds(550, 265, 150, 30);
        showPassword.setBackground(Color.ORANGE);

        // Menambahkan komponen ke panel menu
        menu.add(login);
        menu.add(labUsername);
        menu.add(labPassword);
        menu.add(textUsername);
        menu.add(textPassword);
        menu.add(resetButton);
        menu.add(registerButton);
        menu.add(showPassword);

        // Menambahkan action listener ke tombol dan checkbox
        login.addActionListener(this);
        resetButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addItemListener(this);

        // Menambahkan panel menu ke frame
        frame.add(menu);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Metode untuk menangani aksi tombol
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("LOGIN")) {
            String username = textUsername.getText();
            String password = new String(textPassword.getPassword());

            // Memeriksa apakah field username dan password kosong
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username atau Password Harus Diisi");
                return;
            }

            // Mencoba login sebagai user
            User user = controlUser.getUser(username);
            if (user != null && password.equals(user.getPassword())) {
                Singleton.getInstance().setUser(user);
                frame.setVisible(false);
                new MainMenuUser(); // Menampilkan menu user
                return;
            }

            // Mencoba login sebagai admin
            Admin admin = controlAdmin.getAdmin(username);
            if (admin != null && password.equals(admin.getPassword())) {
                Singleton.getInstance().setAdmin(admin);
                frame.setVisible(false);
                new MainMenuAdmin(); // Menampilkan menu admin
                return;
            }

            // Menampilkan pesan error jika username atau password salah
            JOptionPane.showMessageDialog(null, "Username atau Password Salah");
        }

        if (command.equals("RESET")) {
            // Mengosongkan field username dan password
            textUsername.setText("");
            textPassword.setText("");
        }

        if (command.equals("REGISTER")) {
            // Mengarahkan ke layar registrasi
            frame.setVisible(false);
            new RegistrationScreen();
        }
    }

    // Metode untuk menangani checkbox show password
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == showPassword) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textPassword.setEchoChar((char) 0); // Menampilkan password
            } else {
                textPassword.setEchoChar('*'); // Menyembunyikan password
            }
        }
    }
}