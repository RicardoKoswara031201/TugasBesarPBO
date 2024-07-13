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
import Controller.ControllerPesanan;
import Model.Singleton;
import Model.User;

public class MainMenuUser implements ActionListener {

    JFrame frame = new JFrame("MAIN MENU USER");
    JPanel menu = new JPanel();

    JButton btnMenuCatering = new JButton("Menu Catering");
    JButton btnPesananUser = new JButton("Pesanan Saya");
    JButton btnProgressPesanan = new JButton("Progress Pesanan");
    JButton btnCancelOrder = new JButton("Batalkan Pesanan");
    JButton btnLogout = new JButton("Logout");
    
    User loggedInUser;

    public MainMenuUser() {
        loggedInUser = Singleton.getInstance().getUser(); // Ambil user yang sedang login

        // Set up the frame
        frame.setLayout(null);
        frame.setSize(1200, 620); // Set ukuran frame
        frame.setLocationRelativeTo(null);

        // Set up the menu panel
        menu.setLayout(null);
        menu.setBounds(0, 0, 1200, 620); // Set ukuran dan posisi menu panel
        menu.setBackground(Color.ORANGE);

        // Set up components' bounds and add them to the menu panel
        btnMenuCatering.setBounds(450, 100, 300, 50); // Sesuaikan posisi dan ukuran button
        btnPesananUser.setBounds(450, 180, 300, 50);
        btnProgressPesanan.setBounds(450, 260, 300, 50);
        btnCancelOrder.setBounds(450, 340, 300, 50);
        btnLogout.setBounds(1050, 20, 100, 30);

        // Add action listeners to buttons
        btnMenuCatering.addActionListener(this);
        btnPesananUser.addActionListener(this);
        btnProgressPesanan.addActionListener(this);
        btnCancelOrder.addActionListener(this);
        btnLogout.addActionListener(this);

        // Add buttons to the menu panel
        menu.add(btnMenuCatering);
        menu.add(btnPesananUser);
        menu.add(btnProgressPesanan);
        menu.add(btnCancelOrder);
        menu.add(btnLogout);

        // Add menu panel to the frame
        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnMenuCatering) {
            new CateringMenuView(loggedInUser); // Buka menu catering dengan user yang login
        } else if (ae.getSource() == btnPesananUser) {
            new PesananUser(loggedInUser); // Buka pesanan user dengan user yang login
        } else if (ae.getSource() == btnProgressPesanan) {
            new ProgressView(loggedInUser); // Buka progress pesanan
        } else if (ae.getSource() == btnCancelOrder) {
            new CancelOrder(loggedInUser); // Buka pembatalan pesanan
        } else if (ae.getSource() == btnLogout) {
            // Handle logout button click
            frame.setVisible(false);
            new LoginScreen(); // Redirect to login screen
        }
    }

    public static void main(String[] args) {
        new MainMenuUser();
    }
}