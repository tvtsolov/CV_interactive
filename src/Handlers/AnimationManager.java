package Handlers;

import animations.Animation;
import animations.FramedPicture;
import animations.Player;
import main.Config;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {


    // needs to load all animatable objects

    // buttons
    // backgroundImage
    // interactable buttons/triggers bubble boxes
    // bubble text boxes


    GamePanel gp;
    BufferedImage backgroundImage;
    Animation backgroundAnimation;
    BufferedImage[] tutorialSprites;
    Animation tutorial;
    FramedPicture[] pictures;

    private boolean tutorialFading = false;
    public Player player;

    public AnimationManager(GamePanel gp, Player player){
        this.gp = gp;
        this.player = player;
        Assets.load(player.speed);
        backgroundImage = Assets.backgroundImage;
        backgroundAnimation = Assets.backgroundAnimation;
        tutorial = Assets.tutorial;
        pictures = Assets.pictures;
        tutorialSprites = Assets.tutorialSprites;
    }

    public void updateAnimationsPositions(){

        if (player.state.name.equals("WALKING")) {
            if (player.x < Config.LEFT_BOUNDARY) {
                if (player.movedLeft){
                    backgroundAnimation.x += (int) player.speed;
                }
            } else if (player.x > Config.RIGHT_BOUNDARY) {
                if (player.movedRight){
                    backgroundAnimation.x -= (int) player.speed;
                }
            }
        }


    }

//    public void loadAssets() {
//
//        tutorialSprites = new BufferedImage[2];
//
//        try {
//            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/test-bg.png")));
//
//            background = new Animation(backgroundImage, Config.INIT_BG_X, Config.INIT_BG_Y, player.speed);
//
//            tutorialSprites[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons1.png")));
//            tutorialSprites[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons2.png")));
//            tutorial = new Animation(tutorialSprites, 100, 100, 0.13F); // these coordinates are always relative to the window
//
//            pictures = new FramedPicture[5];
//            String VTUmessage = Config.TXTBOX_MESSAGES.get("VTU");
//            //TODO
//            BufferedImage pictureVTU = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons1.png")));
//            TextBox textBoxVTU = new TextBox(VTUmessage);
//            FramedPicture VTU = new FramedPicture("VTU", textBoxVTU, pictureVTU, 0,0);
//
//
//
//
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//    }

    // for each visible animatable in the array, we need to
    // activate/deactivate the animatable
    // make it update its frames - internal to the animation?




    // run all animatable objects if they are active:
    public void draw(Graphics2D g2){

        // draw the BG
        if (backgroundImage != null){
            int width = Config.BG_WIDTH;
            int height = Config.BG_HEIGHT;

            backgroundAnimation.draw(g2, backgroundAnimation.x, backgroundAnimation.y, width, height);


        } else {
            System.out.println("Warning: background is null, cannot draw.");
        }

        // draw the rest
        if (tutorial.active) {
            if ((player.x > Config.LEFT_BOUNDARY && player.x < Config.RIGHT_BOUNDARY) && !tutorialFading)
                tutorial.draw(g2, tutorial.x, tutorial.y, 0, 0);
            else {
                //if we want it to fade in or out:
                tutorialFading = true;
                tutorial.draw(g2, tutorial.x, tutorial.y, 0, 0, true);
            }
            tutorial.updateFrame();
        }

    }


}

