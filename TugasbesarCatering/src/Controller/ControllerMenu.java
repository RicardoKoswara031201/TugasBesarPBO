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
import Model.MenuCatering;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerMenu {
    private static DBHandler conn = new DBHandler();

    // Method to retrieve menus from the database based on the date
    public static List<MenuCatering> getMenusByDate(Date date) {
        List<MenuCatering> menus = new ArrayList<>();
        conn.connect();
        String query = "SELECT m.menu_id, m.nama_menu, m.deskripsi_menu, m.harga_menu " +
                       "FROM Menu m " +
                       "JOIN Jadwal_Menu jm ON m.menu_id = jm.menu_id " +
                       "WHERE jm.tanggal_tersedia = ?";

        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setDate(1, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int menu_id = rs.getInt("menu_id");
                String nama_menu = rs.getString("nama_menu");
                String deskripsi_menu = rs.getString("deskripsi_menu");
                double harga_menu = rs.getDouble("harga_menu");

                MenuCatering menu = new MenuCatering(menu_id, nama_menu, deskripsi_menu, harga_menu);
                menus.add(menu);
            }

            // Close resources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return menus;
    }
}