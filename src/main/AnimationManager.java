package main;

import entity.Entity;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

    public AnimationManager(GamePanel gp){
        this.gp = gp;
        getAssets();
    }


    public void getAssets() {

        tutorialSprites = new BufferedImage[2];


        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/test-bg.png")));
            background = new Animation(backgroundImage);

            tutorialSprites[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons1.png")));
            tutorialSprites[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/buttons/dir_buttons2.png")));
            tutorial = new Animation(tutorialSprites, 0, 0, 1);
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    // for each visible animatable in the array, we need to
    // activate/deactivate the animatable
    // make it update its frames - internal to the animation?


    // TODO function for checking collisions

    public void draw(Graphics2D g2){


        if (backgroundImage != null){
            g2.drawImage(backgroundImage, 0,0, null);
        } else {
            System.out.println("Warning: background is null, cannot draw.");
        }
        background.draw(g2, 0, -500);
        tutorial.draw(g2, tutorial.x, tutorial.y);

    }



}
