package main;


import Handlers.Assets;
import animations.Animatable;
import animations.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Handlers.KeyHandler.*;

public class UI extends Animatable {

    public static Boolean selected = false;
    public Sound soundSelect = Assets.soundSelect;
    public Sound changeValueSound = Assets.soundSelect;


    public UI(BufferedImage[] sprites){
        this.sprites = sprites;
    }

    public void playSound(Boolean loop, Sound snd){
        if(snd != null) {
            snd.setFile();
            if (!loop) {
                snd.play();
            } else {
                snd.loop();
            }
        } else {
            System.out.println("Sound is NULL");
        }
    }


    public void display(Graphics2D g2){

        draw(g2, 0,0, Entity.Direction.RIGHT);
        if(rightPressed){
            if (currentFrame == 1){
                currentFrame = 2;
                playSound(false, changeValueSound);
            }
        } else if (leftPressed){
            if (currentFrame == 2){
                currentFrame = 1;
                playSound(false, changeValueSound);
            }
        }

        if (enterPressed){
            Config.SCALE = currentFrame;
            selected = true;
        }
    }
}
