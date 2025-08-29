package main;


import Handlers.AnimationManager;
import Handlers.Assets;
import Handlers.KeyHandler;
import animations.Player;

import javax.swing.JPanel;
import java.awt.*;

import static Handlers.Assets.*;

public class GamePanel extends JPanel implements Runnable {

    final int scale = Config.SCALE;

    public final int screenWidth = Config.screenWidth * scale;
    public final int screenHeight = Config.screenHeight * scale;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    AnimationManager anManager = new AnimationManager(this, player, this);


    public GamePanel() {
        //this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        setPreferredSize(new Dimension(Assets.menu.sprites[0].getWidth(), Assets.menu.sprites[0].getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
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
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint(); // asks Swing to redraw the screen, eventually this calls paintComponent
                delta--;
            }
        }
    }

    public void update() {
        player.update();
        if((player.movedLeft && past.isEmpty()) && !anManager.drawn.isEmpty() && anManager.drawn.peekFirst().picture.x > Config.LEFT_END){

            System.out.println("reached left end");
        }
        else if(player.movedRight && future.isEmpty() && anManager.drawn.peekLast().picture.x < Config.RIGHT_END){

            System.out.println("reached right end");
        } else {
            anManager.updateAnimationsPositions();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        anManager.draw(g2);
        if(anManager.menu.selected){
            player.draw(g2);
        }
        g2.dispose();

    }


}

