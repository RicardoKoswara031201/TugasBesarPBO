/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Asus
 */
import Model.Pesanan;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerPesanan {
    private final DBHandler dbHandler = new DBHandler();

    // Method to retrieve user's orders based on user_id
    public List<Pesanan> getUserOrders(int userId) {
        List<Pesanan> pesananList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbHandler.getConnection();
            String query = "SELECT * FROM pesanan WHERE user_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            // Iterate through the result set and create Pesanan objects
            while (rs.next()) {
                Pesanan pesanan = new Pesanan();
                pesanan.setPesanan_id(rs.getInt("pesanan_id"));
                pesanan.setUser_id(rs.getInt("user_id"));
                pesanan.setTanggal_pesanan(rs.getString("tanggal_pesanan"));
                pesanan.setStatus_pesanan(rs.getString("status_pesanan"));
                pesanan.setTotal_harga(rs.getDouble("total_harga"));
                pesananList.add(pesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources in finally block to ensure they are always closed
            dbHandler.closeResources(conn, stmt, rs);
        }
        return pesananList;
    }

    // Method to calculate total price of an order
    public double hitungTotalHarga(String namaMakanan, int quantity) {
        double totalHarga = 0.0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbHandler.getConnection();
            String query = "SELECT harga_menu FROM Menu WHERE nama_menu = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, namaMakanan);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double hargaPerItem = rs.getDouble("harga_menu");
                totalHarga = hargaPerItem * quantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.closeResources(conn, stmt, rs);
        }

        return totalHarga;
    }

    // Method to save an order and its details to the database
    public void simpanPesanan(User user, String tanggal, String namaMakanan, int quantity) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbHandler.getConnection();
            conn.setAutoCommit(false); // Set autocommit to false for transaction

            // Get harga_menu from Menu table based on namaMakanan
            double hargaMenu = getHargaMenuByName(namaMakanan, conn);

            // Insert into Pesanan table
            String sqlPesanan = "INSERT INTO pesanan (user_id, tanggal_pesanan, status_pesanan, total_harga) VALUES (?, ?, 'diantrian', ?)";
            stmt = conn.prepareStatement(sqlPesanan, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, user.getUser_id());
            stmt.setString(2, tanggal);
            stmt.setDouble(3, 0); // Initial total_harga is set to 0, to be updated later
            stmt.executeUpdate();

            // Get last inserted pesanan_id
            int pesananId;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pesananId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

            // Insert into Detail_Pesanan table
            int menuId = getMenuIdByName(namaMakanan, conn);
            String sqlDetail = "INSERT INTO pesanan_detail (pesanan_id, menu_id, jumlah_pesanan, harga_menu) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sqlDetail);
            stmt.setInt(1, pesananId);
            stmt.setInt(2, menuId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, hargaMenu);
            stmt.executeUpdate();

            // Calculate total price of the order
            double totalHarga = hargaMenu * quantity;

            // Update total_harga in pesanan table
            String sqlUpdateTotalHarga = "UPDATE pesanan SET total_harga = ? WHERE pesanan_id = ?";
            stmt = conn.prepareStatement(sqlUpdateTotalHarga);
            stmt.setDouble(1, totalHarga);
            stmt.setInt(2, pesananId);
            stmt.executeUpdate();

            conn.commit(); // Commit transaction

            // Success notification
            System.out.println("Pembelian berhasil.");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback transaction if there's an error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Close database resources in finally block to ensure they are always closed
            dbHandler.closeResources(conn, stmt);
        }
    }

    // Method to retrieve menu_id by menu name
    private int getMenuIdByName(String namaMakanan, Connection conn) throws SQLException {
        int menuId = 0;
        String query = "SELECT menu_id FROM Menu WHERE nama_menu = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaMakanan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                menuId = rs.getInt("menu_id");
            }
        }
        return menuId;
    }

    // Method to retrieve harga_menu by menu name
    private double getHargaMenuByName(String namaMakanan, Connection conn) throws SQLException {
        double hargaMenu = 0.0;
        String query = "SELECT harga_menu FROM Menu WHERE nama_menu = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaMakanan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hargaMenu = rs.getDouble("harga_menu");
            }
        }
        return hargaMenu;
    }
    
    // Method to retrieve pesanan based on status and user_id
    public List<Pesanan> getPesananByStatus(int userId, String status) {
        List<Pesanan> pesanans = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbHandler.getConnection();
            String query = "SELECT * FROM pesanan WHERE user_id = ? AND status_pesanan = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setString(2, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pesanan pesanan = new Pesanan();
                pesanan.setPesanan_id(rs.getInt("pesanan_id"));
                pesanan.setUser_id(rs.getInt("user_id"));
                pesanan.setTanggal_pesanan(rs.getString("tanggal_pesanan"));
                pesanan.setStatus_pesanan(rs.getString("status_pesanan"));
                pesanan.setTotal_harga(rs.getDouble("total_harga"));
                pesanans.add(pesanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.closeResources(conn, stmt, rs);
        }

        return pesanans;
    }
    
    // Method to cancel pesanan based on pesanan_id
    public boolean cancelPesanan(int pesananId) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbHandler.getConnection();
            String query = "UPDATE pesanan SET status_pesanan = 'dibatalkan' WHERE pesanan_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, pesananId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true; // Cancellation successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.closeResources(conn, stmt);
        }

        return success;
    }

    // Method to check progress of a menu in orders for the logged in user
    public String checkProgress(String menuName, int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder progress = new StringBuilder();

        try {
            conn = dbHandler.getConnection();

            // Query to retrieve progress based on user_id and menuName
            String query = "SELECT dp.status_pesanan FROM pesanan_detail dp " +
                           "JOIN pesanan p ON dp.pesanan_id = p.pesanan_id " +
                           "JOIN Menu m ON dp.menu_id = m.menu_id " +
                           "WHERE p.user_id = ? AND m.nama_menu = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setString(2, menuName);
            rs = stmt.executeQuery();

            // Append progress status to StringBuilder
            while (rs.next()) {
                String statusPesanan = rs.getString("status_pesanan");
                progress.append(statusPesanan).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHandler.closeResources(conn, stmt, rs);
        }

        return progress.toString();
    }
}