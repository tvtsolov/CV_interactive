package main;


import entity.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.security.Key;
import java.sql.SQLOutput;

public class GamePanel extends JPanel implements Runnable {



    final int originalTileSize = 64;  // 32 x 32 tile
    final int scale = 1;
    public final int tileSize = originalTileSize * scale; // 64 x 64

    //public final int maxScreenCol = 32;
    //public final int maxScreenRow = 20;
    public final int screenWidth = 1000;
    public final int screenHeight = 500;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    AnimationManager anManager = new AnimationManager(this);

        
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // calls the run method automatically
    }

    @Override

    //------------------ANOTHER ALTERNATIVE FOR GAME LOOP--------------------//
//    public void run() {
//
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null) {
//
//            long currentTime = System.nanoTime();
//            System.out.println("Current Time: " + currentTime);
//
//            update();
//            repaint();
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep( (long)remainingTime );
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    //------------------SECOND ALTERNATIVE, USING DELTA TIME--------------------//
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint(); // asks Swing to redraw the screen, eventually this calls paintComponent
                delta--;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        anManager.draw(g2);
        player.draw(g2);

//        Point panelPos = this.getLocationOnScreen(); // this == GamePanel
//        int screenX = panelPos.x + player.x;
//        int screenY = panelPos.y + player.y;
//        System.out.println("Player screen position: " + screenX + ", " + screenY);


        //System.out.println("position Location on screen is:" + loc.getLocationOnScreen());



        g2.dispose();

    }
}
