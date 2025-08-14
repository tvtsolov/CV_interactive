package main;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class AnimationManager {


    // needs to load all animatable objects

    // buttons
    // backgroundImage
    // interactable buttons/triggers bubble boxes
    // bubble text boxes


    GamePanel gp;
    BufferedImage backgroundImage;
    Animation background;
    BufferedImage[] tutorialSprites;
    Animation tutorial;

    public Player player;

    public AnimationManager(GamePanel gp, Player player){
        this.gp = gp;
        this.player = player;
        getAssets();
    }

    public void updateAnimationsPositions(){

        if (player.state.name.equals("WALKING")) {
            if (player.x < Config.LEFT_BOUNDARY) {
                if (player.movedLeft){
                    background.x += (int) player.speed;
                }
            } else if (player.x > Config.RIGHT_BOUNDARY) {
                if (player.movedRight){
                    background.x -= (int) player.speed;
                }
            }
        }


    }

    public void getAssets() {

        tutorialSprites = new BufferedImage[2];


        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/test-bg.png")));

            background = new Animation(backgroundImage, Config.INIT_BG_X, Config.INIT_BG_Y, player.speed);

            tutorialSprites[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons1.png")));
            tutorialSprites[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons2.png")));
            tutorial = new Animation(tutorialSprites, 100, 100, 0.13F); // these coordinates are always relative to the window
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // for each visible animatable in the array, we need to
    // activate/deactivate the animatable
    // make it update its frames - internal to the animation?




    // run all animatable objects if they are active:
    public void draw(Graphics2D g2){

        // draw the BG
        if (backgroundImage != null){
            int width = Config.BG_WIDTH;
            int height = Config.BG_HEIGHT;

            background.draw(g2, background.x, background.y, width,height);

        } else {
            System.out.println("Warning: background is null, cannot draw.");
        }

        // draw the rest
        if (tutorial.active) {
            if (player.x > Config.LEFT_BOUNDARY && player.x < Config.RIGHT_BOUNDARY)
                tutorial.draw(g2, tutorial.x, tutorial.y, 0, 0);
            else {
                //if we want it to fade in or out:
                tutorial.draw(g2, tutorial.x, tutorial.y, 0, 0, true);
            }
            tutorial.updateFrame();
        }

    }



}


// I'm just gonna ... leave the here...
//        Timer t = new Timer();
//        t.scheduleAtFixedRate(new TimerTask() {
//            int count = 0;
//
//            @Override
//            public void run() {
//                count ++;
//                if (count >=5){
//                    t.cancel();
//                }
//            }
//        },0, 3000);