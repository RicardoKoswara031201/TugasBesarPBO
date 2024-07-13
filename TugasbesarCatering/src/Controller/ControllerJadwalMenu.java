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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerJadwalMenu {
    private static DBHandler conn = new DBHandler();

    public List<Integer> getJadwalMenuIdsByDate(String tanggalTersedia) {
        List<Integer> menuIds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn.connect();
            connection = conn.con;
            String query = "SELECT menu_id FROM jadwal_menu WHERE tanggal_tersedia = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, tanggalTersedia);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int menuId = resultSet.getInt("menu_id");
                menuIds.add(menuId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return menuIds;
    }

    public String getMenuDetailById(int menuId) {
        String menuDetail = "";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn.connect();
            connection = conn.con;
            String query = "SELECT nama_menu, deskripsi_menu, harga_menu FROM menu WHERE menu_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, menuId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String namaMenu = resultSet.getString("nama_menu");
                String deskripsiMenu = resultSet.getString("deskripsi_menu");
                double hargaMenu = resultSet.getDouble("harga_menu");

                menuDetail = "Nama Menu: " + namaMenu + "\n"
                           + "Deskripsi: " + deskripsiMenu + "\n"
                           + "Harga: Rp " + hargaMenu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return menuDetail;
    }
}