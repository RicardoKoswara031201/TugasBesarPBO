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
import java.util.List;

public class CancelOrder {

    JFrame frame = new JFrame("Cancel Order");
    JPanel panel = new JPanel();
    JLabel labelTitle = new JLabel("Pesanan dalam Antrian yang dapat Dibatalkan");
    JTextArea textAreaPesanan = new JTextArea();
    JButton buttonCancel = new JButton("Batalkan Pesanan");

    User loggedInUser;
    ControllerPesanan controllerPesanan;

    public CancelOrder(User user) {
        loggedInUser = user;
        controllerPesanan = new ControllerPesanan();

        // Set up the frame
        frame.setLayout(null);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // Set up the panel
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 500);
        panel.setBackground(Color.LIGHT_GRAY);

        // Set up components' bounds and add them to the panel
        labelTitle.setBounds(50, 50, 400, 30);
        textAreaPesanan.setBounds(50, 100, 700, 300);
        textAreaPesanan.setEditable(false);
        buttonCancel.setBounds(300, 420, 200, 30);

        // Add action listener to button
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelPesanan();
            }
        });

        // Add components to panel
        panel.add(labelTitle);
        panel.add(textAreaPesanan);
        panel.add(buttonCancel);

        // Add panel to frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // Load pesanan yang sedang diantrian saat inisialisasi
        loadPesananDiantrian();
    }

    // Method untuk memuat pesanan dengan status "diantrian" dari pengguna yang sedang login
    private void loadPesananDiantrian() {
        List<Pesanan> pesananList = controllerPesanan.getPesananByStatus(loggedInUser.getUser_id(), "diantrian");

        if (pesananList.isEmpty()) {
            textAreaPesanan.setText("Tidak ada pesanan dengan status 'diantrian' yang dapat dibatalkan.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("ID Pesanan\t| Tanggal Pesanan\t| Total Harga\n");
            sb.append("---------------------------------------------\n");
            for (Pesanan pesanan : pesananList) {
                sb.append(pesanan.getPesanan_id()).append("\t| ");
                sb.append(pesanan.getTanggal_pesanan()).append("\t| ");
                sb.append(pesanan.getTotal_harga()).append("\n");
            }
            textAreaPesanan.setText(sb.toString());
        }
    }

    // Method untuk membatalkan pesanan yang dipilih
    private void cancelPesanan() {
        String input = JOptionPane.showInputDialog(null, "Masukkan ID Pesanan yang ingin dibatalkan:");
        if (input != null && !input.isEmpty()) {
            try {
                int pesananId = Integer.parseInt(input);
                boolean success = controllerPesanan.cancelPesanan(pesananId);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Pesanan berhasil dibatalkan.");
                    loadPesananDiantrian(); // Refresh list pesanan setelah pembatalan
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal membatalkan pesanan. Pastikan ID Pesanan valid.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID Pesanan harus berupa angka.");
            }
        }
    }

    public static void main(String[] args) {
        // Assuming user is logged in, create CancelOrder for logged in user
        User user = new User(); // Replace with actual logged in user instance
        new CancelOrder(user);
    }
}
