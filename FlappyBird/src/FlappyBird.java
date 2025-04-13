/*Saya Klara Ollivviera Augustine Gunawan dengan NIM 2306205
mengerjakan soal Tugas Praktikum 6 dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    //image attribute
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    //player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    //pipe attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean gameOver = false; // untuk flag game over
    double score = 0; // untuk menghitung score

    JLabel scoreLabel; // Jlabel untuk score
    boolean gameStart = false; // flag untuk mulai game

    //constructor
    public FlappyBird(JLabel scoreLabel){
        this.scoreLabel = scoreLabel; // set score label

        // menunjukkan score label di game panel
        scoreLabel.setBounds(0, 20, frameWidth, 40); // posisi & ukuran
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        setPreferredSize(new Dimension(360, 640));
        setBackground(Color.blue);
        addKeyListener(this);

        //load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipa");
                placePipes();
            }
        });

        gameLoop = new Timer(1000/60, this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // untuk text di game panel
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g.getFontMetrics();

        // jika game over, tampilkan text "Game Over"
        if(gameOver){
            String gameOverText = "Game Over";
            String restartText = "Press R to Restart";

            int gameOverX = (frameWidth - fm.stringWidth(gameOverText)) / 2;
            int restartX = (frameWidth - fm.stringWidth(restartText)) / 2;

            g.drawString(gameOverText, gameOverX, frameHeight / 2 - 40);
            g.drawString(restartText, restartX, frameHeight / 2 + 40);
        }

        // jika game belum di mulai, tampilkan text petunjuk untuk mulai
        if(!gameStart){
            String spaceText = "Press Space to Start";

            int restartX = (frameWidth - fm.stringWidth(spaceText)) / 2;

            g.drawString(spaceText, restartX, frameHeight / 2 + 40);
        }
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            //apabila player melewati pipa atas dan bawah
            if(!pipe.passed && player.getPosX() > pipe.getPosX() + pipeWidth){
                pipe.passed = true; // ubah flag passed menjadi true
                score += 0.5; // menghitung score (karena ada 2 pipa jadi membagi 2 scorenya, 1 pipa 0.5 score)
                scoreLabel.setText(String.valueOf((int) score)); // menampilkan score
            }

            // apabila player menabrak pipa
            if(collide(player, pipe)){
                gameOver = true; // ubah flag game over menjadi true
            }
        }

        // apabila player turun melewati frame
        if(player.getPosY() > frameHeight){
            gameOver = true; // ubah flag game over menjadi true
        }
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + pipeHeight + openingSpace), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // fungsi cek apabila player menabrak pipa atas atau bawah
    public boolean collide(Player player, Pipe pipe){
        return player.getPosX() < pipe.getPosX() + pipeWidth &&
                player.getPosX() + playerWidth > pipe.getPosX() &&
                player.getPosY() < pipe.getPosY() + pipeHeight &&
                player.getPosY() + playerHeight > pipe.getPosY();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // apabila game sudah dimulai dan belum game over, maka dimulai gamenya
        if(gameStart && !gameOver){
            move();
            repaint();
        }

        // apabila game over, pipa akan stop dimunculkan dan game loop dihentikan
        if(gameOver){
            pipesCooldown.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        // apabila player pressed space
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            // apabila gameStart masih false (game belum dimulai)
            if(!gameStart){
                gameStart = true; // ubah flag gameStart menjadi true
                pipesCooldown.start(); // memunculkan pipa
                gameLoop.start(); // game loop di start
            }else { // apabila sudah di mulai gamenya
                player.setVelocityY(-10);
            }
        }

        // apabila player pressed "R" dan game over
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            //restart semua
            player.setPosX(playerStartPosX);
            player.setPosY(playerStartPosY);
            player.setVelocityY(0);
            pipes.clear();
            score = 0;
            scoreLabel.setText("0");
            gameOver = false;
            gameStart = false; // Reset state
            repaint(); // supaya "Start" muncul lagi
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}