package animations;

import java.awt.image.BufferedImage;
import java.util.Random;

public  class Animation extends Animatable {

    //public static int priority;  // to be used as depth

    public int pauseTime = 0;
    public Random rand = new Random();
    public int minPauseTime = 20;
    public int maxPauseTime = 500;
    public int timer = 0;
    public float step = 6;


    public boolean animationPaused = false;

    public int size;
    // this is just the index of the current sprite

    public Animation(BufferedImage sprite){
        this.sprites = new BufferedImage[]{sprite};
        this.size = sprites.length;
        this.speed = 1;
    }

    public Animation(BufferedImage[] sprites){
        this.sprites = sprites;
        this.size = sprites.length;
        this.speed = 1;
    }

    // make an animation that is independent of the player and has its own coordinates
    public Animation(BufferedImage[] sprites, int x, int y, float speed){
        this.sprites = sprites;
        this.size = sprites.length;
        this.step = this.step / speed;
        this.x = x;
        this.y = y;
    }

    // for animations with only one sprite
    public Animation(BufferedImage sprite, int x, int y, float speed){
        this.sprites = new BufferedImage[] {sprite};
        this.size = sprites.length;
        this.step = this.step / speed;
        this.x = x;
        this.y = y;
    }

    //make sub animation directly from BufferedImage array with given start and end frames
    public Animation(BufferedImage[] sprites, int start, int end){
        this.sprites = new BufferedImage[end-start];
        System.arraycopy(sprites, start, this.sprites, 0, end-start);
        this.size = this.sprites.length;
        this.speed = 1;
    }

    public int getFrame(){
        return currentFrame;
    }

    public void updateFrame(){
        spriteCounter++;
        if(spriteCounter > step){

            if (currentFrame >= size){
                currentFrame = 1;
            } else {
                currentFrame++;
            }
            spriteCounter = 0;
        }
    }
}


