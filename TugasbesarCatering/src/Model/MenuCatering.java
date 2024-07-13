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

public class MenuCatering {
    private int menu_id;
    private String nama_menu;
    private String deskripsi_menu;
    private double harga_menu;
    private Date available_date;

    public MenuCatering() {
    }

    public MenuCatering(int menu_id, String nama_menu, String deskripsi_menu, double harga_menu, Date available_date) {
        this.menu_id = menu_id;
        this.nama_menu = nama_menu;
        this.deskripsi_menu = deskripsi_menu;
        this.harga_menu = harga_menu;
        this.available_date = available_date;
    }

    public MenuCatering(int menu_id, String nama_menu, String deskripsi_menu, double harga_menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getDeskripsi_menu() {
        return deskripsi_menu;
    }

    public void setDeskripsi_menu(String deskripsi_menu) {
        this.deskripsi_menu = deskripsi_menu;
    }

    public double getHarga_menu() {
        return harga_menu;
    }

    public void setHarga_menu(double harga_menu) {
        this.harga_menu = harga_menu;
    }

    public Date getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(Date available_date) {
        this.available_date = available_date;
    }
}