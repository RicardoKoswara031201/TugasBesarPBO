/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Beli {
    public static void processPurchase(Date selectedDate, List<MenuCatering> menus, String namaMenu, int quantity) {
        // Find the selected menu
        MenuCatering selectedMenu = null;
        for (MenuCatering menu : menus) {
            if (menu.getNama_menu().equals(namaMenu)) {
                selectedMenu = menu;
                break;
            }
        }

        if (selectedMenu == null) {
            JOptionPane.showMessageDialog(null, "Menu tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate total price
        double totalPrice = selectedMenu.getHarga_menu() * quantity;

        // Show confirmation dialog
        int option = JOptionPane.showConfirmDialog(null,
                "Anda akan membeli " + quantity + " " + selectedMenu.getNama_menu() + " dengan total harga Rp " + totalPrice + ". Lanjutkan?",
                "Konfirmasi Pembelian", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Process pembelian (simulasi atau integrasi dengan sistem pembayaran)
            JOptionPane.showMessageDialog(null, "Pembelian berhasil!");
            // TODO: Implement actual purchase logic here
        }
    }
}
