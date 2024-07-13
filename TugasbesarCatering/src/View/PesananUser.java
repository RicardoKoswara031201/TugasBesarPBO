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
import java.sql.*;
import Controller.DBHandler;
import Model.User;
import javax.swing.table.DefaultTableModel;

public class PesananUser {

    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private User user;
    private DBHandler dbHandler;

    public PesananUser(User user) {
        this.user = user;
        dbHandler = new DBHandler();

        frame = new JFrame("Pesanan Saya");
        panel = new JPanel(new BorderLayout());

        // Setup table columns
        String[] columnNames = {"Pesanan ID", "Menu", "Jumlah", "Harga", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Initialize table and scroll pane
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        // Load user's orders
        loadPesanan();

        // Add components to panel and frame
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);

        // Set frame properties
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to load user's orders from database
    private void loadPesanan() {
        try {
            Connection con = dbHandler.getConnection();
            String query = "SELECT p.pesanan_id, m.nama_menu, pd.jumlah_pesanan, pd.harga_menu, p.status_pesanan " +
                    "FROM pesanan p " +
                    "JOIN pesanan_detail pd ON p.pesanan_id = pd.pesanan_id " +
                    "JOIN menu m ON pd.menu_id = m.menu_id " +
                    "WHERE p.user_id = ? AND (p.status_pesanan = 'diantrian' OR p.status_pesanan = 'diproses')";
            
            // Prepare SQL statement with user's ID
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUser_id()); // Set user_id parameter using getId() method from User object
            ResultSet rs = ps.executeQuery();

            // Get table model for adding rows
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                // Retrieve order details from result set
                int pesananId = rs.getInt("pesanan_id");
                String namaMenu = rs.getString("nama_menu");
                int jumlahPesanan = rs.getInt("jumlah_pesanan");
                double hargaMenu = rs.getDouble("harga_menu");
                String statusPesanan = rs.getString("status_pesanan");

                // Add retrieved data as a new row in the table
                model.addRow(new Object[]{pesananId, namaMenu, jumlahPesanan, hargaMenu, statusPesanan});
            }

            // Close resources
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading pesanan");
        }
    }
}
