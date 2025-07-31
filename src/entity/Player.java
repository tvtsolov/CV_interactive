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


    public BufferedImage[] walkingSprites;
    public BufferedImage[] sittingSprites;
    public Animation walking;
    public Animation sitting;
    public Animation sat;
    private int scale;
    public Direction direction;
    public boolean animateSittingDown = false;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues(); // sets speed as well as X and Y
        getPlayerImage();
    }

    public State getPlayerState(){
        return state;
    }

    public void updateState(State newSt){
        prevState = state;
        state = newSt;
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.RIGHT;
        state = State.SITTING;
        prevState = state;
        scale = 4;
    }

    public void getPlayerImage() {

        walkingSprites = new BufferedImage[8];
        sittingSprites = new BufferedImage[18];

        try{
            for(int i = 0; i < walkingSprites.length; i++){
                String str = "/player/walk/wlk" + (i+1) + ".png";
                walkingSprites[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }
            for(int i = 0; i < sittingSprites.length; i++){
                String str = "/player/sit/sitting" + (i+1) + ".png";
                sittingSprites[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }

            walking = new Animation(walkingSprites);
            sitting = new Animation(sittingSprites, 0, 2);
            sat = new Animation(sittingSprites, 2, 18);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void update(){

        spriteCounter++;

        if(keyH.upPressed){
            //y -= speed;
        } else if (keyH.downPressed){
            //y += speed;
        } else if(keyH.leftPressed){
            pauseCounter = 0;
            x -= speed;
            updateState(State.WALKING);
            direction = Direction.LEFT;
            if(spriteCounter > step){
                walking.updateFrame();
                spriteCounter = 0;
            }
        } else if(keyH.rightPressed){
            pauseCounter = 0;
            x += speed;
            updateState(State.WALKING);
            direction = Direction.RIGHT;
            if(spriteCounter > step){
                walking.updateFrame();
                spriteCounter = 0;
            }
        } else { // if not moving at all, no arrows are being pressed
            if (prevState == State.WALKING && !animateSittingDown) {
                //start sitting down animation
                updateState(State.SITTING);
                animateSittingDown = true;
            } else if (animateSittingDown){
                sitting.updateFrame();
            } else {
                updateState(State.SITTING);
                pauseCounter++;

                if (!sat.animationPaused) { // always start the sat animation with a pause
                    sat.timer = sat.rand.nextInt(sat.minPauseTime, sat.maxPauseTime); // sets when we will unpause/start animating the sat animation
                    sat.animationPaused = true;
                } else {
                    if (pauseCounter > sat.timer) { // time to animate
                        if (spriteCounter > step) { // time for next frame
                            sat.updateFrame();
                            spriteCounter = 0;
                        }
                        if (sat.currentFrame >= sat.sprites.length){ // if sat animation is over
                            pauseCounter = 0;
                            sat.animationPaused = false;
                        }
                    }
                }
            }
        }



    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (state == State.WALKING) {
            if (direction == Direction.LEFT)  {
                image = walking.sprites[walking.currentFrame-1];
            }  else if(direction == Direction.RIGHT) {
                image = walking.sprites[walking.currentFrame-1];
            }
        } else if (state == State.SITTING){
            if (animateSittingDown) {
                if (sitting.currentFrame <= sitting.size){
                    image = sitting.sprites[sitting.currentFrame - 1];
                    if (sitting.currentFrame == sitting.size){
                        animateSittingDown = false;
                    }
                } else {
                    animateSittingDown = false;
                    image = sat.sprites[sat.currentFrame - 1];
                }
            } else {
                image = sat.sprites[sat.currentFrame - 1];
            }
        }



        if (image != null) {
            if (direction == Direction.LEFT)
            {
                g2.drawImage(image, x + image.getWidth()*4 , y, -image.getWidth() * scale, image.getHeight() * scale, null);
            } else {
                g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
            }
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

}
