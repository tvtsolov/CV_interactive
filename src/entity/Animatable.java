package entity;

import main.Animation;
import main.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Animatable {

    public Animation[] frames;

    public int x;
    public int y;
    public int speed = 1;
    boolean active = false;

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
