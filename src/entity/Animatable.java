package entity;

import main.Animation;
import main.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Animatable {

    public Animation[] frames;

    public int x;
    public int y;
    public float speed;
    public int spriteCounter = 0;
    public float alpha = 1f;

    public boolean active = true;

    public BufferedImage[] sprites;
    public int currentFrame = 1;

    //draw basic thing with x and y and a given direction
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
    public void draw(Graphics2D g2, int x, int y, int width, int height){
        BufferedImage image = sprites[currentFrame-1];
        int scale = Config.SCALE;

        if(width==0){
            width = image.getWidth() * scale;
        }
        if(height==0){
            height = image.getHeight() * scale;
        }

        Entity.Direction dir = Entity.Direction.RIGHT;

        if (image != null) {
            g2.drawImage(image, x, y, width, height, null);
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }


    // draw a given image only, with a given scale
    public void draw(Graphics2D g2, BufferedImage image, int x, int y, int scaleVal){
        int scale = scaleVal;
        if (image != null){
            g2.drawImage(image, x + image.getWidth()*scale , y, -image.getWidth() * scale, image.getHeight() * scale, null);
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

    //draw with fade effect
    public void draw(Graphics2D g2, int x, int y, int width, int height, boolean fadeOut){
        BufferedImage image = sprites[currentFrame-1];
        int scale = Config.SCALE;

        fade(fadeOut);

        if(width==0){
            width = image.getWidth() * scale;
        }
        if(height==0){
            height = image.getHeight() * scale;
        }

        Entity.Direction dir = Entity.Direction.RIGHT;

        if (image != null) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(image, x, y, width, height, null);
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

    public void fade(boolean fadeOut){
        if (fadeOut) {
            if (alpha > 0) {
                alpha -= 0.01f;
                if (alpha < 0f) alpha = 0f;
            } else {
                active = false;
            }
        } else {
            if (alpha < 1f) {
                alpha += 0.01f;
                if (alpha > 1f) alpha = 1f;
            }
        }
    }
}
