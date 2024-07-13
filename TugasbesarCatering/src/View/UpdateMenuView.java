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
import Controller.DBHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class UpdateMenuView implements ActionListener {

    private JFrame frame;
    private JPanel menu;
    private JButton btnTambahMenu;
    private JButton btnUpdateMenu;
    private DBHandler dbHandler;

    public UpdateMenuView() {
        // Set up the frame
        frame = new JFrame("Update Menu");
        frame.setLayout(null);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // Set up the menu panel
        menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, 800, 500);
        menu.setBackground(Color.ORANGE);

        // Initialize buttons
        btnTambahMenu = new JButton("Tambah Menu");
        btnUpdateMenu = new JButton("Update Menu");

        // Set up buttons' bounds and add them to the menu panel
        btnTambahMenu.setBounds(100, 100, 200, 50);
        btnUpdateMenu.setBounds(100, 200, 200, 50);

        menu.add(btnTambahMenu);
        menu.add(btnUpdateMenu);

        // Add action listeners to buttons
        btnTambahMenu.addActionListener(this);
        btnUpdateMenu.addActionListener(this);

        // Add menu panel to the frame
        frame.add(menu);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // Initialize DBHandler
        dbHandler = new DBHandler();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnTambahMenu) {
            tambahMenu();
        } else if (ae.getSource() == btnUpdateMenu) {
            updateMenu();
        }
    }

    private void tambahMenu() {
        String tanggalStr = JOptionPane.showInputDialog(frame, "Masukkan Tanggal (YYYY-MM-DD):");
        String namaMenu = JOptionPane.showInputDialog(frame, "Masukkan Nama Menu:");
        String deskripsi = JOptionPane.showInputDialog(frame, "Masukkan Deskripsi Menu:");
        String hargaStr = JOptionPane.showInputDialog(frame, "Masukkan Harga Menu:");

        if (tanggalStr != null && namaMenu != null && deskripsi != null && hargaStr != null) {
            try {
                double hargaDouble = Double.parseDouble(hargaStr);
                BigDecimal harga = BigDecimal.valueOf(hargaDouble);

                dbHandler.connect();
                String menuQuery = "INSERT INTO Menu (nama_menu, deskripsi_menu, harga_menu) VALUES (?, ?, ?)";
                PreparedStatement menuStmt = dbHandler.con.prepareStatement(menuQuery, Statement.RETURN_GENERATED_KEYS);
                menuStmt.setString(1, namaMenu);
                menuStmt.setString(2, deskripsi);
                menuStmt.setBigDecimal(3, harga);

                int menuRowsAffected = menuStmt.executeUpdate();

                if (menuRowsAffected > 0) {
                    ResultSet generatedKeys = menuStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int menuId = generatedKeys.getInt(1);
                        String jadwalQuery = "INSERT INTO Jadwal_Menu (menu_id, tanggal_tersedia) VALUES (?, ?)";
                        PreparedStatement jadwalStmt = dbHandler.con.prepareStatement(jadwalQuery);
                        jadwalStmt.setInt(1, menuId);
                        jadwalStmt.setString(2, tanggalStr);

                        int jadwalRowsAffected = jadwalStmt.executeUpdate();

                        if (jadwalRowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Menu berhasil ditambahkan!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Gagal menambahkan jadwal menu.");
                        }
                        jadwalStmt.close();
                    }
                    generatedKeys.close();
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal menambahkan menu.");
                }

                menuStmt.close();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            } finally {
                dbHandler.disconnect();
            }
        }
    }

    private void updateMenu() {
        String tanggalStr = JOptionPane.showInputDialog(frame, "Masukkan Tanggal (YYYY-MM-DD):");
        String namaMenuLama = JOptionPane.showInputDialog(frame, "Masukkan Nama Menu yang Ingin Diubah:");
        String namaMenuBaru = JOptionPane.showInputDialog(frame, "Masukkan Nama Menu Baru:");
        String deskripsiBaru = JOptionPane.showInputDialog(frame, "Masukkan Deskripsi Menu Baru:");
        String hargaStr = JOptionPane.showInputDialog(frame, "Masukkan Harga Menu Baru:");

        if (tanggalStr != null && namaMenuLama != null && namaMenuBaru != null && deskripsiBaru != null && hargaStr != null) {
            try {
                double hargaDouble = Double.parseDouble(hargaStr);
                BigDecimal hargaBaru = BigDecimal.valueOf(hargaDouble);

                dbHandler.connect();
                String query = "UPDATE Menu m " +
                               "JOIN Jadwal_Menu jm ON m.menu_id = jm.menu_id " +
                               "SET m.nama_menu = ?, m.deskripsi_menu = ?, m.harga_menu = ? " +
                               "WHERE m.nama_menu = ? AND jm.tanggal_tersedia = ?";
                PreparedStatement stmt = dbHandler.con.prepareStatement(query);
                stmt.setString(1, namaMenuBaru);
                stmt.setString(2, deskripsiBaru);
                stmt.setBigDecimal(3, hargaBaru);
                stmt.setString(4, namaMenuLama);
                stmt.setString(5, tanggalStr);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Menu berhasil diperbarui!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal memperbarui menu.");
                }

                stmt.close();
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
            } finally {
                dbHandler.disconnect();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateMenuView::new);
    }
}