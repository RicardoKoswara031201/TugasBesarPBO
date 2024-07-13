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
public class Person {
    private String nama;
    private String password;

    public Person(){
        
    }
    
    public Person(String Nama, String Password) {
        this.nama = Nama;
        this.password = Password;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String Nama) {
        this.nama = Nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
    
}
