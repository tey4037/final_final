package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Register_page {
    private JPanel panel;
    private JLabel title;
    private JLabel userLbl;
    private JTextField userField;
    private JLabel passLbl;
    private JPasswordField passField;
    private JButton regBtn;
    private JButton backBtn;
    private JFrame frame;

    public Register_page(MovieStore store) {
        frame = new JFrame("Create Account");
        frame.setContentPane(panel);
        frame.setSize(315, 330);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBackground(new Color(245, 245, 245));
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        regBtn.setBackground(new Color(231, 231, 231));
        regBtn.setForeground(new Color(20, 20, 20));



        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String u = userField.getText();
                String p = new String(passField.getPassword());

                if (u.equals("") || p.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Input Username or Password!");
                    return;
                }

                User newUser = new User(u, p);

                try {
                    FileWriter fw = new FileWriter("src/ui/users.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(newUser.toFileString());
                    bw.newLine();
                    bw.close();

                    frame.dispose();
                    new Login_page(store);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new Login_page(store);
        });

        frame.setVisible(true);
    }
}