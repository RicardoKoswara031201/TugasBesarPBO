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
public class Pesanan {
    private int pesanan_id;
    private int user_id;
    private String tanggal_pesanan;
    private String status_pesanan;
    private double total_harga;
    private int detali_pesanan_id;

    public Pesanan(int pesanan_id, int user_id, String tanggal_pesanan, String status_pesanan, double total_harga,int detali_pesanan_id) {
        this.pesanan_id = pesanan_id;
        this.user_id = user_id;
        this.tanggal_pesanan = tanggal_pesanan;
        this.status_pesanan = status_pesanan;
        this.total_harga = total_harga;
        this.detali_pesanan_id = detali_pesanan_id;
    }

    public Pesanan() {
        
    }

    public int getDetali_pesanan_id() {
        return detali_pesanan_id;
    }

    public void setDetali_pesanan_id(int detali_pesanan_id) {
        this.detali_pesanan_id = detali_pesanan_id;
    }

    public int getPesanan_id() {
        return pesanan_id;
    }

    public void setPesanan_id(int pesanan_id) {
        this.pesanan_id = pesanan_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTanggal_pesanan() {
        return tanggal_pesanan;
    }

    public void setTanggal_pesanan(String tanggal_pesanan) {
        this.tanggal_pesanan = tanggal_pesanan;
    }

    public String getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(String status_pesanan) {
        this.status_pesanan = status_pesanan;
    }

    public double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga = total_harga;
    }

}

