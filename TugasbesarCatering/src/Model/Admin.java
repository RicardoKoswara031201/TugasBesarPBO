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
public class Admin extends Person {
    private String admin_id;
    private String gaji;
    
    public Admin() {
        // Constructor kosong
    }
    
    public Admin(String gaji, int idCabang, String nama, String password) {
        super(nama, password);
        this.gaji = gaji;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
    
}
