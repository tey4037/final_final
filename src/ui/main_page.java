package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_page {
    private JPanel mainpage;
    private JLabel title;
    private JLabel subtitle;
    private JSeparator lines;
    private JButton viewBtn;
    private JButton returnBtn;
    private JButton statusBtn;
    private JButton exitBtn;
    private JButton LogOutBtn;

    private JFrame frame;
    private MovieStore store;

    public main_page(MovieStore store) {
        this.store = store;
        frame = new JFrame("MAIN PAGE");
        frame.setContentPane(mainpage);
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Color bg = new Color(250, 250, 250);
        Color text = new Color(20, 20, 20);
        Color gray = new Color(130, 130, 130);
        Color line = new Color(200, 200, 200);
        Color hover = new Color(80, 80, 80);

        mainpage.setBackground(bg);

        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(text);

        subtitle.setFont(new Font("sansSerif", Font.PLAIN, 12));
        subtitle.setForeground(gray);

        lines.setForeground(line);

        Font btnFont = new Font("Serif", Font.PLAIN, 16);
        LogOutBtn.setFont(btnFont);
        JButton[] buttons = {viewBtn, returnBtn, statusBtn, exitBtn,LogOutBtn};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setForeground(text);
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

        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Rent_page(store);
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame, "Enter Movie ID to Return:");
                if (id != null && !id.isEmpty()) {
                    boolean success = store.processReturn(id);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Processed Return for ID: " + id.toUpperCase());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed! Invalid ID or Movie is not Rented.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        statusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame, "Enter Movie ID to Check Status:");
                if (id != null && !id.isEmpty()) {
                    String currentStatus = "Not Found";
                    for (int i = 0; i < store.getItemCount(); i++) {
                        if (store.getItems()[i].getId().equalsIgnoreCase(id)) {
                            currentStatus = store.getItems()[i].getStatus();
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Movie Status: " + currentStatus);
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        LogOutBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        LogOutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        LogOutBtn.addActionListener(e -> {
            frame.dispose();
            new Login_page(store);
        });

        frame.setVisible(true);
    }
}