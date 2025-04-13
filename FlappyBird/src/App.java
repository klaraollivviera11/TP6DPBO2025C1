/*Saya Klara Ollivviera Augustine Gunawan dengan NIM 2306205
mengerjakan soal Tugas Praktikum 6 dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin */

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args){
        //buat sebuah frame
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //buat label score
        JLabel scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setOpaque(false); // transparan

        //buat objek JPanel
        FlappyBird flappyBird = new FlappyBird(scoreLabel);
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}
