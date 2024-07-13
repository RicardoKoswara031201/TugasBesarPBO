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

public class MainMenuAdmin implements ActionListener {

    private JFrame frame;
    private JPanel menu;

    private JButton btnJadwalMenu;
    private JButton btnUpdateMenu;
    private JButton btnPesananUser;
    private JButton btnUserCatering;
    private JButton btnTotalKeuntungan;
    private JButton btnLogout;

    public MainMenuAdmin() {
        // Set up the frame
        frame = new JFrame("Main Menu - Admin");
        frame.setLayout(null);
        frame.setSize(1200, 620);
        frame.setLocationRelativeTo(null);

        // Set up the menu panel
        menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, 1200, 620);
        menu.setBackground(Color.ORANGE);

        // Initialize buttons
        btnJadwalMenu = new JButton("Jadwal Menu");
        btnUpdateMenu = new JButton("Update Menu");
        btnPesananUser = new JButton("Pesanan User");
        btnUserCatering = new JButton("User Catering");
        btnTotalKeuntungan = new JButton("Total Keuntungan");
        btnLogout = new JButton("Logout");

        // Set up buttons' bounds and add them to the menu panel
        btnJadwalMenu.setBounds(450, 100, 300, 50);
        btnUpdateMenu.setBounds(450, 180, 300, 50);
        btnPesananUser.setBounds(450, 260, 300, 50);
        btnUserCatering.setBounds(450, 340, 300, 50);
        btnTotalKeuntungan.setBounds(450, 420, 300, 50);
        btnLogout.setBounds(1050, 20, 100, 30);

        menu.add(btnJadwalMenu);
        menu.add(btnUpdateMenu);
        menu.add(btnPesananUser);
        menu.add(btnUserCatering);
        menu.add(btnTotalKeuntungan);
        menu.add(btnLogout);

        // Add action listeners to buttons
        btnJadwalMenu.addActionListener(this);
        btnUpdateMenu.addActionListener(this);
        btnPesananUser.addActionListener(this);
        btnUserCatering.addActionListener(this);
        btnTotalKeuntungan.addActionListener(this);
        btnLogout.addActionListener(this);

        // Add menu panel to the frame
        frame.add(menu);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnJadwalMenu) {
            new CateringMenuViewAdmin();
        } else if (ae.getSource() == btnUpdateMenu) {
            new UpdateMenuView();
        } else if (ae.getSource() == btnPesananUser) {
            new ViewOrdersView();
        } else if (ae.getSource() == btnUserCatering) {
            new ViewUserView();
        } else if (ae.getSource() == btnTotalKeuntungan) {
            new ViewProfitsView();   
        } else if (ae.getSource() == btnLogout) {
            // Handle logout button click
            frame.setVisible(false);
            new LoginScreen(); // Redirect to login screen
        }
    }

    public static void main(String[] args) {
        new MainMenuAdmin();
    }
}