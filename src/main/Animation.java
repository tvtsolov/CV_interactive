package main;

import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Animation{

    //public static int priority;  // to be used as depth

    public int pauseTime = 0;
    public Random rand = new Random();
    public int minPauseTime = 20;
    public int maxPauseTime = 500;
    public int timer = 0;

    public boolean animationPaused = false;
    public int speed;
    public BufferedImage[] sprites;
    public int size;
    public int currentFrame = 1; // this is just the index of the current sprite



    public Animation(BufferedImage[] sprites){
        this.sprites = sprites;
        this.size = sprites.length;
        this.speed = 1;
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
        if (currentFrame >= size){
            currentFrame = 1;
        } else {
            currentFrame++;
        }
    }

    public void draw(Graphics2D g2, int x, int y, Entity.Direction dir){
        BufferedImage image = sprites[currentFrame-1];
        int scale = Config.SCALE;
        if (image != null) {
            if (dir == Entity.Direction.LEFT)
            {
                g2.drawImage(image, x + image.getWidth()*scale , y, -image.getWidth() * scale, image.getHeight() * scale, null);
            } else {
                g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
            }
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

    // draw objects that don't rotate
    public void draw(Graphics2D g2, int x, int y){
        Entity.Direction dir = Entity.Direction.RIGHT;
        BufferedImage image = sprites[currentFrame-1];
        int scale = Config.SCALE;
        if (image != null) {
            g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

    // draw a given image only
    public void draw(Graphics2D g2, BufferedImage image, int x, int y){
        int scale = Config.SCALE;
        if (image != null){
            g2.drawImage(image, x + image.getWidth()*scale , y, -image.getWidth() * scale, image.getHeight() * scale, null);
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }


}
