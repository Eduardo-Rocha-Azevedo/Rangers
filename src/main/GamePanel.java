package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // Screens Settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 pixels
    final int maxScrollCol = 16;
    final int maxScrollRow = 12;
    final int screenWidth = tileSize * maxScrollCol; // 768 pixels
    final int screenHeight = tileSize * maxScrollRow; // 576 pixels

    // FPS
    int FPS = 60;
    // Game Settings
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // Set player's defult position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            update();
            repaint();
          
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)(remainingTime));
                nextDrawTime += drawInterval;
            } catch (Exception e) {
                
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }

    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } 
        else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
         else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }
}
