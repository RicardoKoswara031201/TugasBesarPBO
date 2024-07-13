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

public class User extends Person {
    private int user_id;
    private String noTelepon;
    private String region;
    private String daerah;

    public User() {
        // Constructor kosong
    }

    public User(int user_id, String noTelepon, String region, String daerah) {
        this.user_id = user_id;
        this.noTelepon = noTelepon;
        this.region = region;
        this.daerah = daerah;
    }
    
    public User(int user_id, String noTelepon, String region, String daerah, String nama, String password) {
        super(nama, password);
        this.user_id = user_id;
        this.noTelepon = noTelepon;
        this.region = region;
        this.daerah = daerah;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int User_id) {
        this.user_id = User_id;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }
}