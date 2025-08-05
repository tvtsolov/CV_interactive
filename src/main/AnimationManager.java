package main;

import entity.Entity;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AnimationManager {

    GamePanel gp;
    BufferedImage backgroundImage;

    public AnimationManager(GamePanel gp){
        this.gp = gp;
        getBackgroundImage();
    }


    public void getBackgroundImage() {

        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background/test-bg.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        if (backgroundImage != null){
            g2.drawImage(backgroundImage, 0,0, null);
        } else {
            System.out.println("Warning: background is null, cannot draw.");
        }
    }



}
