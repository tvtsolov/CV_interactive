package main;


import Handlers.Assets;
import Handlers.KeyHandler;
import animations.Animatable;
import animations.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Handlers.KeyHandler.*;

public class UI extends Animatable {

    public static Boolean selected = false;
    public Sound selectSound = Assets.sound;
    public Sound changeValueSound = Assets.sound;
    public int selection = 1;

    public UI(BufferedImage[] sprites){
        this.sprites = sprites;
    }


    public void display(Graphics2D g2){

        draw(g2, 0,0, Entity.Direction.RIGHT);
        if(rightPressed){
            if (currentFrame == 1){
                currentFrame = 2;
            }
        } else if (leftPressed){
            if (currentFrame == 2){
                currentFrame = 1;
            }
        }

        if (enterPressed){
            Config.SCALE = currentFrame;
            selected = true;
        }
    }
}
