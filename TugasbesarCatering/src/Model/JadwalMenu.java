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

import java.sql.Date;

public class JadwalMenu {
    private int jadwal_menu_id;
    private int menu_id;
    private Date tanggal_tersedia;

    public JadwalMenu(int jadwal_menu_id, int menu_id, Date tanggal_tersedia) {
        this.jadwal_menu_id = jadwal_menu_id;
        this.menu_id = menu_id;
        this.tanggal_tersedia = tanggal_tersedia;
    }

    public int getJadwal_menu_id() {
        return jadwal_menu_id;
    }

    public void setJadwal_menu_id(int jadwal_menu_id) {
        this.jadwal_menu_id = jadwal_menu_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public Date getTanggal_tersedia() {
        return tanggal_tersedia;
    }

    public void setTanggal_tersedia(Date tanggal_tersedia) {
        this.tanggal_tersedia = tanggal_tersedia;
    }
    
    
}
