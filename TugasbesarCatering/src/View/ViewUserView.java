/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Asus
 */
import Controller.DBHandler;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ViewUserView implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JTable userTable;
    private DefaultTableModel model;
    private JButton btnBack;
    private DBHandler dbHandler;

    public ViewUserView() {
        dbHandler = new DBHandler();

        frame = new JFrame("View Users - Admin");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        btnBack = new JButton("Back");
        btnBack.setBounds(20, 20, 100, 30);
        btnBack.addActionListener(this);
        panel.add(btnBack);

        model = new DefaultTableModel();
        userTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 70, 740, 450);
        panel.add(scrollPane);

        model.addColumn("User ID");
        model.addColumn("Nama");
        model.addColumn("Daerah");
        model.addColumn("Alamat");
        model.addColumn("No. Telepon");

        fetchUsers();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void fetchUsers() {
        try {
            Connection con = dbHandler.getConnection();
            String query = "SELECT user_id, nama, daerah, alamat, no_telepon FROM users";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String nama = rs.getString("nama");
                String daerah = rs.getString("daerah");
                String alamat = rs.getString("alamat");
                String noTelepon = rs.getString("no_telepon");

                model.addRow(new Object[]{userId, nama, daerah, alamat, noTelepon});
            }

            rs.close();
            pst.close();
            dbHandler.disconnect();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            frame.setVisible(false);
            new MainMenuAdmin();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewUserView();
        });
    }
}