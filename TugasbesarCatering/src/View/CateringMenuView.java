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
import Controller.ControllerJadwalMenu;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CateringMenuView {

    private JFrame frame = new JFrame("Catering Menu"); // Frame utama untuk aplikasi
    private JPanel menu = new JPanel(); // Panel untuk menampung komponen-komponen utama
    private JLabel labelInput = new JLabel("Masukkan angka untuk menampilkan menu berdasarkan jadwal:"); // Label instruksi
    private JTextField textFieldInput = new JTextField(); // TextField untuk input angka
    private JButton buttonShowMenu = new JButton("Tampilkan Menu"); // Tombol untuk menampilkan menu
    private JTextArea textAreaMenu = new JTextArea(); // TextArea untuk menampilkan menu
    private JButton buttonBeli = new JButton("Beli"); // Tombol untuk memulai proses pembelian

    private ControllerJadwalMenu controller = new ControllerJadwalMenu(); // Controller untuk mengelola logika bisnis
    private User loggedInUser; // User yang sedang login

    public CateringMenuView(User user) {
        loggedInUser = user; // Ambil user yang sedang login

        // Set up the frame
        frame.setLayout(null); // Gunakan layout null untuk penempatan manual komponen
        frame.setSize(1400, 700); // Atur ukuran frame
        frame.setLocationRelativeTo(null); // Agar frame muncul di tengah layar

        // Set up the menu panel
        menu.setLayout(null); // Gunakan layout null untuk penempatan manual komponen
        menu.setBounds(0, 0, 1400, 700); // Atur ukuran dan posisi menu panel
        menu.setBackground(Color.WHITE); // Atur warna latar belakang menu panel

        // Set up components' bounds and add them to the menu panel
        labelInput.setBounds(50, 50, 400, 30); // Atur posisi dan ukuran label instruksi
        textFieldInput.setBounds(50, 100, 200, 30); // Atur posisi dan ukuran input text field
        buttonShowMenu.setBounds(50, 150, 150, 30); // Atur posisi dan ukuran tombol tampilkan menu
        textAreaMenu.setBounds(50, 200, 1300, 350); // Atur posisi dan ukuran text area untuk menampilkan menu
        textAreaMenu.setEditable(false); // Set agar text area tidak bisa diedit oleh pengguna
        buttonBeli.setBounds(50, 570, 150, 30); // Atur posisi dan ukuran tombol beli

        // Tambahkan komponen-komponen ke menu panel
        menu.add(labelInput);
        menu.add(textFieldInput);
        menu.add(buttonShowMenu);
        menu.add(textAreaMenu);
        menu.add(buttonBeli);

        // Tambahkan action listener ke tombol tampilkan menu
        buttonShowMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textFieldInput.getText();
                if (!input.isEmpty()) {
                    try {
                        int angka = Integer.parseInt(input);
                        showMenuByDate(angka); // Panggil metode untuk menampilkan menu berdasarkan input angka
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Input harus berupa angka");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input tidak boleh kosong");
                }
            }
        });

        // Tambahkan action listener ke tombol beli
        buttonBeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PembelianDialog dialog = new PembelianDialog(frame, loggedInUser); // Membuat dialog pembelian
                dialog.setVisible(true); // Tampilkan dialog pembelian
            }
        });

        frame.add(menu); // Tambahkan menu panel ke frame utama
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Atur operasi penutupan frame
        frame.setVisible(true); // Atur frame agar terlihat
    }

    // Metode untuk menampilkan menu berdasarkan tanggal yang dimasukkan
    private void showMenuByDate(int angka) {
        List<Integer> menuIds = controller.getJadwalMenuIdsByDate(String.valueOf(angka)); // Ambil ID menu dari database
        StringBuilder result = new StringBuilder("Menu yang tersedia:\n\n"); // String builder untuk menampung hasil

        // Ambil detail menu untuk setiap menu ID
        for (int menuId : menuIds) {
            String menuDetail = controller.getMenuDetailById(menuId); // Ambil detail menu dari controller
            result.append(menuDetail).append("\n\n"); // Tambahkan detail menu ke string builder
        }

        textAreaMenu.setText(result.toString()); // Tampilkan hasil di text area
    }

    public static void main(String[] args) {
        User user = new User();
        new CateringMenuView(user); // Buat instance baru dari CateringMenuView
    }
}