package main;

import entity.Animatable;

public class Scene {

    // used to update all elements coordinates relative to the background

    Animation background;
    Animatable[] animations;

    Scene(Animation bg, Animatable[] objects){
        this.background = bg;
        this.animations = objects;
    }


    public void moveScene(){

    }

}
