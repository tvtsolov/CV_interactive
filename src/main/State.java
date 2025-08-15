package main;

import animations.Animation;

import java.awt.image.BufferedImage;

public class State {
    public String name;
    public Animation animation;

    public State(String name, Animation animation){
        this.name = name;
        this.animation = animation;
    }

    public int getFrame(){
        return animation.currentFrame;
    }

    public void setFrame(int val){
        animation.currentFrame = val;
    }

    public BufferedImage getCurrentSprite(){
        return animation.sprites[getFrame()-1];
    }

    public Animation getAnimation(){
        return animation;
    }

}
