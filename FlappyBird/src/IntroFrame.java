/*Saya Klara Ollivviera Augustine Gunawan dengan NIM 2306205
mengerjakan soal Tugas Praktikum 6 dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin */

import javax.swing.*;
import java.awt.*;

public class IntroFrame extends JFrame {
    public IntroFrame() {
        // set frame intro
        setTitle("Welcome to Flappy Bird");
        setSize(360, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Menambahkan background
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Label untuk tulisan "FLappy Bird"
        JLabel label = new JLabel("Flappy Bird", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // penempatan di tengah frame
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

        // Button untuk start game
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setMaximumSize(new Dimension(140, 36));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // penempatan di tengah frame
        startButton.addActionListener(e -> { // apabila di pencet buttonnya maka akan menutup frame lalu memuncul kan frame game
            dispose();
            App.main(null);
        });

        // Karena label dan button ditaruh di tengah frame jadi di beri jarak
        mainPanel.add(Box.createVerticalStrut(220)); // jarak dari atas ke label
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(20));  // jarak dari label ke tombol
        mainPanel.add(startButton);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new IntroFrame();
    }

    // Panel untuk gambar background
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // mengambil image untuk background
            backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}