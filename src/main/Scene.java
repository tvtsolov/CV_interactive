package main;

import animations.Animatable;
import animations.Animation;

public class Scene {

    // used to update all elements coordinates relative to the background
    // this is currently handled the animation manager object: updateAnimationsPositions()

    Animation background;
    Animatable[] animations;

    Scene(Animation bg, Animatable[] objects){
        this.background = bg;
        this.animations = objects;
    }


    public void moveScene(){

    }

}
