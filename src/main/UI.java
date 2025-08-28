package main;


import Handlers.Assets;
import animations.Animatable;

import java.awt.image.BufferedImage;

public class UI extends Animatable {

    Boolean selected = false;
    public Sound selectSound = Assets.sound;
    public Sound changeValueSound = Assets.sound;

    public UI(BufferedImage[] sprites){
        this.sprites = sprites;
    }

    public void display(){



    }

}
