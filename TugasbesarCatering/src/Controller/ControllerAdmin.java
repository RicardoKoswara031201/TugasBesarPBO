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
import java.sql.*;
import java.util.ArrayList;
import Model.*;

public class ControllerAdmin {

    static DBHandler conn = new DBHandler();

    // SELECT ALL from table admins
    public static ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM admin";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdmin_id(rs.getString("admin_id"));
                admin.setNama(rs.getString("nama"));
                admin.setPassword(rs.getString("kata_sandi"));
                admins.add(admin);
            }
            // Close resources
            rs.close();
            stmt.close();
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
    
    // SELECT an admin by username from table admin
    public static Admin getAdmin(String nama) {
        conn.connect();
        Admin admin = null;
        String query = "SELECT * FROM admin WHERE Nama='" + nama + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                admin = new Admin();
                admin.setAdmin_id(rs.getString("admin_id"));
                admin.setNama(rs.getString("nama"));
                admin.setPassword(rs.getString("kata_sandi"));
            }
            // Close resources
            rs.close();
            stmt.close();
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

}

