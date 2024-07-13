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
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PembelianDialog extends JDialog {

    private JLabel labelTanggal = new JLabel("Tanggal :");
    private JLabel labelNamaMakanan = new JLabel("Nama Makanan:");
    private JLabel labelQuantity = new JLabel("Quantity:");

    private JTextField textFieldTanggal = new JTextField();
    private JTextField textFieldNamaMakanan = new JTextField();
    private JTextField textFieldQuantity = new JTextField();

    private JButton buttonCheckHarga = new JButton("Check Harga");
    private JButton buttonBeli = new JButton("Beli");

    private ControllerPesanan controller = new ControllerPesanan();
    private User loggedInUser;

    public PembelianDialog(Frame parent, User user) {
        super(parent, "Pembelian", true); // Set dialog modal dan judul
        loggedInUser = user; // Ambil user yang sedang login

        JPanel panel = new JPanel(new GridBagLayout()); // Panel utama dengan layout GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints(); // Constraint untuk GridBagLayout
        constraints.insets = new Insets(10, 10, 10, 10); // Padding antar komponen

        // Label dan text field untuk tanggal
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(labelTanggal, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        textFieldTanggal.setPreferredSize(new Dimension(200, 25));
        panel.add(textFieldTanggal, constraints);

        // Label dan text field untuk nama makanan
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(labelNamaMakanan, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        textFieldNamaMakanan.setPreferredSize(new Dimension(200, 25));
        panel.add(textFieldNamaMakanan, constraints);

        // Label dan text field untuk quantity
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(labelQuantity, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        textFieldQuantity.setPreferredSize(new Dimension(200, 25));
        panel.add(textFieldQuantity, constraints);

        // Button untuk check harga
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        buttonCheckHarga.setPreferredSize(new Dimension(150, 30));
        panel.add(buttonCheckHarga, constraints);

        // Button untuk beli
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        buttonBeli.setPreferredSize(new Dimension(150, 30));
        panel.add(buttonBeli, constraints);

        // Action listener untuk button check harga
        buttonCheckHarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkHarga(); // Panggil metode untuk mengecek harga pesanan
            }
        });

        // Action listener untuk button beli
        buttonBeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beliPesanan(); // Panggil metode untuk memproses pembelian pesanan
            }
        });

        getContentPane().add(panel); // Tambahkan panel ke dalam content pane
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Atur operasi penutupan dialog
        pack(); // Agar dialog menyesuaikan ukuran sesuai komponennya
        setLocationRelativeTo(parent); // Agar dialog muncul di tengah frame utama
    }

    // Metode untuk mengecek harga pesanan
    private void checkHarga() {
        try {
            // Ambil input dari text fields
            String tanggal = textFieldTanggal.getText();
            String namaMakanan = textFieldNamaMakanan.getText();
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            // Hitung total harga pesanan
            double totalHarga = controller.hitungTotalHarga(namaMakanan, quantity);

            // Tampilkan total harga kepada pengguna
            JOptionPane.showMessageDialog(this, "Total harga pesanan: " + totalHarga);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity harus berupa angka");
        }
    }

    // Metode untuk memproses pembelian pesanan
    private void beliPesanan() {
        try {
            // Ambil input dari text fields
            String tanggal = textFieldTanggal.getText();
            String namaMakanan = textFieldNamaMakanan.getText();
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            // Simpan pesanan dan detail pesanan ke database
            controller.simpanPesanan(loggedInUser, tanggal, namaMakanan, quantity);

            // Tutup dialog setelah berhasil menyimpan
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity harus berupa angka");
        }
    }

    public static void main(String[] args) {
        User user = new User(); // Ganti dengan cara yang sesuai untuk mendapatkan user yang login
        JFrame parentFrame = new JFrame(); // Frame utama untuk dialog
        parentFrame.setSize(400, 300); // Atur ukuran frame utama
        parentFrame.setLocationRelativeTo(null); // Agar frame muncul di tengah layar

        // Buat instance dari PembelianDialog dan tampilkan
        PembelianDialog dialog = new PembelianDialog(parentFrame, user);
        dialog.setVisible(true);
    }
}