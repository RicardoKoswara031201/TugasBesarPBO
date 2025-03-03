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
import java.sql.DriverManager;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class DBHandler {
    // Mendeklarasikan objek Connection untuk koneksi database
    public Connection con;
    // Mendeklarasikan driver JDBC untuk MySQL
    private String driver = "com.mysql.cj.jdbc.Driver";
    // Mendeklarasikan URL koneksi dengan TimeZone default
    private String url = "jdbc:mysql://localhost/catering?serverTimezone=" + TimeZone.getDefault().getID();
    // Mendeklarasikan username dan password untuk database
    private String username = "root";
    private String password = "";

    // Metode untuk melakukan koneksi ke database
    private Connection logOn() {
        try {
            // Memuat driver JDBC
            Class.forName(driver).newInstance();
            // Membuat objek Connection
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            // Menangani error yang terjadi
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
        return con;
    }

    // Metode untuk menutup koneksi ke database
    private void logOff() {
        try {
            // Menutup koneksi
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
    }

    // Metode untuk memulai koneksi ke database
    public void connect() {
        try {
            con = logOn();
        } catch (Exception ex) {
            System.out.println("Error occurred when connecting to database");
        }
    }

    // Metode untuk mengakhiri koneksi ke database
    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Error occurred when connecting to database");
        }
    }
}