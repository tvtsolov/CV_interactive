package main;

import java.awt.image.BufferedImage;

public class Animation {
    public int speed;
    public BufferedImage[] sprites;
    public int size;
    public int currentFrame = 1;

    public Animation(BufferedImage[] sprites){
        this.sprites = sprites;
        this.size = sprites.length;
        this.speed = 1;
    }

    //make a separate animation out of a bigger one, extract smaller step out of big animation
    public Animation(Animation animation, int start, int end){
        this.sprites = new BufferedImage[end-start];
        System.arraycopy(animation.sprites, start, this.sprites, 0, end-start);
        this.size = this.sprites.length;
        this.speed = 1;
    }

    //same but directly from BufferedImage array
    public Animation(BufferedImage[] sprites, int start, int end){
        this.sprites = new BufferedImage[end-start];
        System.arraycopy(sprites, start, this.sprites, 0, end-start);
        this.size = this.sprites.length;
        this.speed = 1;
    }

    public void updateFrame(){
        if (currentFrame >= size-1){
            currentFrame = 1;
        } else {
            currentFrame++;
        }
    }

}
