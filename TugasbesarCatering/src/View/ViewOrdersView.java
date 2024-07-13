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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ViewOrdersView extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnUpdateStatus;
    private JComboBox<String> statusComboBox;
    private DBHandler dbHandler;

    public ViewOrdersView() {
        // Set up the frame
        setTitle("View Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize DBHandler
        dbHandler = new DBHandler();

        // Set up table model
        tableModel = new DefaultTableModel(new String[]{"Order ID", "User ID", "Tanggal", "Status", "Total Harga"}, 0);
        table = new JTable(tableModel);

        // Fetch orders from database
        fetchOrders();

        // Set up JScrollPane for table
        JScrollPane scrollPane = new JScrollPane(table);

        // Set up status combo box and update button
        statusComboBox = new JComboBox<>(new String[]{"diantrian", "diproses", "selesai"});
        btnUpdateStatus = new JButton("Update Status");
        btnUpdateStatus.addActionListener(this);

        // Set up panel for status update
        JPanel panel = new JPanel();
        panel.add(new JLabel("Change status to:"));
        panel.add(statusComboBox);
        panel.add(btnUpdateStatus);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void fetchOrders() {
        dbHandler.connect();
        String query = "SELECT pesanan_id, user_id, tanggal_pesanan, status_pesanan, total_harga FROM Pesanan WHERE status_pesanan = 'diantrian' OR status_pesanan = 'diproses'";
        try {
            Statement stmt = dbHandler.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("pesanan_id"));
                row.add(rs.getInt("user_id"));
                row.add(rs.getString("tanggal_pesanan"));
                row.add(rs.getString("status_pesanan"));
                row.add(rs.getDouble("total_harga"));
                tableModel.addRow(row);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.disconnect();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUpdateStatus) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int orderId = (int) tableModel.getValueAt(selectedRow, 0);
                String newStatus = (String) statusComboBox.getSelectedItem();
                updateOrderStatus(orderId, newStatus);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order to update.");
            }
        }
    }

    private void updateOrderStatus(int orderId, String newStatus) {
        dbHandler.connect();
        String query = "UPDATE Pesanan SET status_pesanan = ? WHERE pesanan_id = ?";
        try {
            PreparedStatement stmt = dbHandler.con.prepareStatement(query);
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Order status updated successfully.");
                tableModel.setRowCount(0); // Clear existing rows
                fetchOrders(); // Refresh table data
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update order status.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.disconnect();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewOrdersView::new);
    }
}
