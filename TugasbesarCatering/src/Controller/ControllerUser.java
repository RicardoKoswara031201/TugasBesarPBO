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

public class ControllerUser {

    static DBHandler conn = new DBHandler();

    // SELECT ALL from table users
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("kata_sandi"));
                user.setRegion(rs.getString("daerah"));
                user.setDaerah(rs.getString("alamat"));
                user.setNoTelepon(rs.getString("no_telepon"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return users;
    }
    
    // Method to insert new user into database
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users (nama, kata_sandi, alamat, daerah, no_telepon) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRegion());
            stmt.setString(4, user.getDaerah());
            stmt.setString(5, user.getNoTelepon());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // SELECT a user by username from table users
    public static User getUser(String nama) {
        conn.connect();
        User user = null;
        String query = "SELECT * FROM users WHERE nama=?";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, nama);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("kata_sandi"));
                user.setRegion(rs.getString("daerah"));
                user.setDaerah(rs.getString("alamat"));
                user.setNoTelepon(rs.getString("no_telepon"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return user;
    }

    // Method to retrieve a user by username
    public static User getUserByUsername(String username) {
        conn.connect();
        User user = null;
        String query = "SELECT * FROM users WHERE nama=?";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setNama(rs.getString("nama"));
                user.setPassword(rs.getString("kata_sandi"));
                user.setRegion(rs.getString("daerah"));
                user.setDaerah(rs.getString("alamat"));
                user.setNoTelepon(rs.getString("no_telepon"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return user;
    }

}