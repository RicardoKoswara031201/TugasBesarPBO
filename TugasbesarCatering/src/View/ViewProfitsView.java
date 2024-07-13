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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ViewProfitsView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private DBHandler dbHandler;

    public ViewProfitsView() {
        setTitle("View Profits - Admin");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        dbHandler = new DBHandler();

        tableModel = new DefaultTableModel(new String[]{"Menu ID", "Nama Menu", "Harga", "Status", "Profit"}, 0);
        table = new JTable(tableModel);

        fetchProfits();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void fetchProfits() {
        dbHandler.connect();
        String query = "SELECT m.menu_id, m.nama_menu, m.harga_menu, p.status_pesanan, " +
                       "SUM(CASE WHEN p.status_pesanan IN ('diproses', 'selesai') THEN m.harga_menu ELSE 0 END) AS profit " +
                       "FROM Menu m " +
                       "JOIN Jadwal_Menu jm ON m.menu_id = jm.menu_id " +
                       "JOIN Pesanan p ON jm.menu_id = p.menu_id " +
                       "GROUP BY m.menu_id, p.status_pesanan";
        try {
            PreparedStatement stmt = dbHandler.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int menuId = rs.getInt("menu_id");
                String namaMenu = rs.getString("nama_menu");
                double hargaMenu = rs.getDouble("harga_menu");
                String statusPesanan = rs.getString("status_pesanan");
                double profit = rs.getDouble("profit");

                Vector<Object> row = new Vector<>();
                row.add(menuId);
                row.add(namaMenu);
                row.add(hargaMenu);
                row.add(statusPesanan);
                row.add(profit);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewProfitsView::new);
    }
}