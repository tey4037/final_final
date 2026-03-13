package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login_page {
    private JPanel Panels;
    private JLabel userlable;
    private JLabel passlabel;
    private JTextField user;
    private JPasswordField password;
    private JButton Login;
    private JButton registerBtn;
    private JFrame frame;
    private MovieStore store;

    public Login_page(MovieStore store) {
        this.store = store;
        frame = new JFrame("Login");
        frame.setContentPane(Panels);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Color text = new Color(20, 20, 20);
        Color hover = new Color(80, 80, 80);

        user.setFont(new Font("sansSerif", Font.PLAIN, 12));
        password.setFont(new Font("sansSerif", Font.PLAIN, 12));

        Login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton[] buttons = {registerBtn};
        for (JButton btn : buttons) {
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setForeground(hover);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setForeground(text);
                }
            });
        }

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getText().isEmpty() || password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Input Username or Password");
                } else {
                    boolean isLoginSuccess = false;
                    try {
                        FileReader fr = new FileReader("src/ui/users.txt");
                        BufferedReader br = new BufferedReader(fr);
                        String line;

                        while ((line = br.readLine()) != null) {
                            String[] data = line.split(",");
                            if (data.length == 2) {
                                if (user.getText().equals(data[0]) && password.getText().equals(data[1])) {
                                    isLoginSuccess = true;
                                    break;
                                }
                            }
                        }
                        br.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (isLoginSuccess) {
                        frame.dispose();
                        new main_page(store);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Register_page(store);
            }
        });
    }
}