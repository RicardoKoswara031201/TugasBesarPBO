/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asus
 */

import Controller.DBHandler;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuUpdater {
    private DBHandler dbHandler;

    public MenuUpdater(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void updateMenuFromSchedule() {
        String sql = "UPDATE Menu m " +
                     "JOIN Jadwal_Menu jm ON m.menu_id = jm.menu_id " +
                     "SET m.tanggal_tersedia = jm.tanggal_tersedia";

        try {
            dbHandler.connect();
            PreparedStatement statement = dbHandler.getConnection().prepareStatement(sql);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbHandler.disconnect();
        }
    }
}
