package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private GamePanel gp;
    private KeyHandler keyH;

    //private int initailX = 100;
    //private int initailY = 50;
    public final int screenX, screenY;

    private BufferedImage[] walkingSprites;
    private BufferedImage[] sittingSprites;
    private Animation walking;
    private Animation sitting;
    private Animation sat;
    private State[] states;
    public State prevState;
    public State state;
    private int scale;
    private Direction direction;
    private boolean animateSittingDown = false;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;
        getPlayerImage();
        createStates();
        setDefaultValues(); // sets speed as well as X and Y

    }

    private void setDefaultValues(){

        worldX = Config.PLAYER_INIT_POS_X;
        worldY = Config.PLAYER_INIT_POS_Y;
        scale = Config.SCALE;
        speed = Config.PLAYER_SPEED;

        direction = Direction.RIGHT;
        setState("SAT");
        prevState = state;
    }

    private State getPlayerState(){
        return state;
    }

    private void updateState(State newSt){
        prevState = state;
        state = newSt;

    }

    private void setState(String stateName){
        for(State st : states){
            if(st.name.equals(stateName)){
                prevState = state;
                state = st;
            }

        }
    }



    private void createStates(){
        states = new State[3];
        states[0] = new State("WALKING", walking);
        states[1] = new State("SITTING", sitting);
        states[2] = new State("SAT", sat);
    }

    private void manageState(KeyHandler kh){ // this can go into its own stateManager class, maybe?

        if(kh.leftPressed){
            pauseCounter = 0;
            worldX -= speed;
            setState("WALKING");
            System.out.println("state is now WALKING");
            direction = Direction.LEFT;
            if(spriteCounter > step){
                state.animation.updateFrame();
                spriteCounter = 0;
            }
        } else if(kh.rightPressed){
            pauseCounter = 0;
            worldX += speed;
            setState("WALKING");
            System.out.println("state is now WALKING");
            direction = Direction.RIGHT;
            if(spriteCounter > step){
                state.animation.updateFrame();
                spriteCounter = 0;
            }
        } else { // if not moving at all, no arrows are being pressed
            if (prevState.name.equals("WALKING") && !animateSittingDown) {
                //start sitting down animation
                setState("SITTING");
                System.out.println("state is now SITTING");
                animateSittingDown = true;
            } else if (animateSittingDown){
                setState("SITTING");
                state.animation.updateFrame();
                spriteCounter = 0;
            } else {
                if(prevState.name.equals("SITTING")){
                    state.setFrame(1);
                }
                setState("SAT");
                System.out.println("state is now SAT");
                pauseCounter++;

                if (!sat.animationPaused) { // always start the sat animation with a pause
                    sat.timer = sat.rand.nextInt(sat.minPauseTime, sat.maxPauseTime); // sets when we will unpause/start animating the sat animation
                    sat.animationPaused = true;
                } else { // State.SAT
                    if (pauseCounter > sat.timer) { // time to animate the idle animation
                        setState("SAT");
                        System.out.println("State is now SAT");
                        if (spriteCounter > step) { // time for the next frame
                            state.animation.updateFrame();
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



    private void getPlayerImage() {

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
        manageState(keyH);

    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (state.name.equals("WALKING")) {
            if (direction == Direction.LEFT)  {
                image = state.getCurrentSprite();
            }  else if(direction == Direction.RIGHT) {
                image = state.getCurrentSprite();
            }
        } else if (state.name.equals("SITTING")){
            // short animation run once
            // when over transition to next state = SAT

            if (animateSittingDown) {
                if (sitting.currentFrame <= sitting.size){
                    image = state.getCurrentSprite();
                    if (sitting.currentFrame == sitting.size){
                        animateSittingDown = false;
                    }
                } else {
                    animateSittingDown = false;
                }
            } else {
                image = state.getCurrentSprite();
            }
        } else if (state.name.equals("SAT")){
            image = state.getCurrentSprite();

        }

        if (image != null) {
            if (direction == Direction.LEFT)
            {
                g2.drawImage(image, worldX + image.getWidth()*scale , worldY, -image.getWidth() * scale, image.getHeight() * scale, null);
            } else {
                g2.drawImage(image, worldX, worldY, image.getWidth() * scale, image.getHeight() * scale, null);
            }
        } else {
            System.out.println("Warning: image is null, cannot draw.");
        }
    }

}
