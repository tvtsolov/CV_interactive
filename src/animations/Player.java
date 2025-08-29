package animations;

import Handlers.KeyHandler;
import Handlers.StateManager;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static main.UI.selected;

public class Player extends Entity{

    private GamePanel gp;
    private KeyHandler keyH;
    private StateManager stateManager;

    private BufferedImage[] walkingSprites;
    private BufferedImage[] sittingSprites;
    public Animation walking;
    public Animation sitting;
    public Animation sat;
    private State[] states;
    public State prevState;
    public State state;
    private int scale;
    public Direction direction;
    public boolean animateSittingDown = false;
    public boolean movedRight = false;
    public boolean movedLeft = false;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setPlayerImage();
        createStates();
        setDefaultValues(); // sets speed as well as X and Y
    }

    public void setDefaultValues(){

        x = Config.PLAYER_INIT_POS_X;
        y = Config.PLAYER_INIT_POS_Y;
        worldX = 0;
        worldY = 0;
        scale = Config.SCALE;
        speed = Config.PLAYER_SPEED;
        direction = Direction.RIGHT;
        setState("SAT");
        prevState = state;
        stateManager = new StateManager(this, keyH);
    }

    private State getPlayerState(){
        return state;
    }

    private void updateState(State newSt){
        prevState = state;
        state = newSt;

    }

    public void setState(String stateName){
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

    private void updatePosition(){
        if(!selected){
            return;
        }
        if(state.name.equals("WALKING")){
            movedLeft = false;
            movedRight = false;
                if (direction == Direction.RIGHT) {
                    movedRight = true;
                    if (x < Config.RIGHT_BOUNDARY+speed && direction == Direction.RIGHT) {
                        x += (int) speed;
                    }
                } else if (direction == Direction.LEFT) {
                    movedLeft = true;
                    if (x > Config.LEFT_BOUNDARY-speed && direction == Direction.LEFT) {
                        x -= (int) speed;
                    }
                }
            }
    }

    private void setPlayerImage() {

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
        stateManager.run();
        updatePosition();
        state.animation.updateFrame();
    }

    public void draw(Graphics2D g2){
        if(!selected){
            return;
        }
        if (animateSittingDown) {
            if (state.getFrame() <= sitting.size) {
                if (state.getFrame() == sitting.size) {
                    animateSittingDown = false;
                }
            } else {
                animateSittingDown = false;
            }
        }

        Animation currentAnimation = state.getAnimation();
        Direction currentDirection = direction;

        state.animation.draw(g2, x, y, direction, this.scale);

        BufferedImage image = null;

    }


}
