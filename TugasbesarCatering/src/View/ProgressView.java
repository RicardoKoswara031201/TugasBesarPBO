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
import Controller.ControllerPesanan;
import Model.Pesanan;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressView extends JFrame {
    private JPanel panelProgress;
    private JLabel labelInput;
    private JTextField textFieldMenuName;
    private JButton buttonCheckProgress;
    private JTextArea textAreaProgress;

    private ControllerPesanan controllerPesanan;
    private User loggedInUser;

    public ProgressView(User user) {
        this.loggedInUser = user;
        this.controllerPesanan = new ControllerPesanan();

        setTitle("Progress Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panelProgress = new JPanel();
        panelProgress.setLayout(null);

        labelInput = new JLabel("Masukkan nama menu untuk melihat progress:");
        labelInput.setBounds(50, 50, 300, 30);
        panelProgress.add(labelInput);

        textFieldMenuName = new JTextField();
        textFieldMenuName.setBounds(50, 100, 200, 30);
        panelProgress.add(textFieldMenuName);

        buttonCheckProgress = new JButton("Check Progress");
        buttonCheckProgress.setBounds(50, 150, 150, 30);
        buttonCheckProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String menuName = textFieldMenuName.getText().trim();
                if (!menuName.isEmpty()) {
                    showProgress(menuName);
                } else {
                    JOptionPane.showMessageDialog(null, "Nama menu tidak boleh kosong.");
                }
            }
        });
        panelProgress.add(buttonCheckProgress);

        textAreaProgress = new JTextArea();
        textAreaProgress.setBounds(50, 200, 500, 150);
        textAreaProgress.setEditable(false);
        panelProgress.add(textAreaProgress);

        add(panelProgress);
        setVisible(true);
    }

    private void showProgress(String menuName) {
        String progress = controllerPesanan.checkProgress(loggedInUser.getUser_id(), menuName);
        textAreaProgress.setText(progress);
    }

    public static void main(String[] args) {
        User user = new User(); // Sesuaikan dengan cara Anda mendapatkan user yang sedang login
        ProgressView progressView = new ProgressView(user);
    }
}