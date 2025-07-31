package entity;

import main.Animation;
import main.GamePanel;
import main.KeyHandler;

import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;


    public BufferedImage[] walkingSprires;
    public BufferedImage[] sittingSprites;
    public Animation walking;
    public Animation sitting;
    public Animation sat;
    int scale;
    public Direction direction;
    public State state;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues(); // sets speed as well as X and Y
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.RIGHT;
        state = State.SITTING;
        scale = 4;

    }

    public void getPlayerImage() {

        walkingSprires = new BufferedImage[8];
        sittingSprites = new BufferedImage[18];

        try{
            for(int i = 0; i < walkingSprires.length; i++){
                String str = "/player/walk/wlk" + (i+1) + ".png";
                walkingSprires[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }
            for(int i = 0; i < sittingSprites.length; i++){
                String str = "/player/sit/sitting" + (i+1) + ".png";
                sittingSprites[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }

            walking = new Animation(walkingSprires);
            sitting = new Animation(sittingSprites, 0, 1);
            sat = new Animation(sittingSprites, 2, 18);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void update(){

        spriteCounter++;

        if(keyH.upPressed){
            y -= speed;
        } else if (keyH.downPressed){
            y += speed;
        } else if(keyH.leftPressed){
            x -= speed;
            state = State.WALKING;
            direction = Direction.LEFT;
            if(spriteCounter > step){
                walking.updateFrame();
                spriteCounter = 0;
            }
        } else if(keyH.rightPressed){
            x += speed;
            state = State.WALKING;
            direction = Direction.RIGHT;
            if(spriteCounter > step){
                walking.updateFrame();
                spriteCounter = 0;
            }
        } else {
            state = State.SITTING;
            if(spriteCounter > step){
                sat.updateFrame();
                spriteCounter = 0;
            }
        }



    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (state == State.WALKING) {
            if (direction == Direction.LEFT)  {
                image = walking.sprites[walking.currentFrame];
            }  else if(direction == Direction.RIGHT) {
                image = walking.sprites[walking.currentFrame];
            }
        } else if (state == State.SITTING){
            image = sat.sprites[sat.currentFrame];
        }



        if (image != null) {
            if (direction == Direction.LEFT)
            {
                g2.drawImage(image, x + image.getWidth()*2 , y, -image.getWidth() * scale, image.getHeight() * scale, null);
            } else {
                g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
            }
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }



    }

}
