package Handlers;

import animations.Animation;
import animations.Phase;
import animations.Player;
import main.Config;
import main.GamePanel;
import main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

public class AnimationManager {


    GamePanel gp;
    BufferedImage backgroundImage;
    Animation backgroundAnimation;
    BufferedImage[] tutorialSprites;
    Animation tutorial;
    Phase[] phases = Assets.phases;
    Deque<Phase> future;
    Deque<Phase> past;
    Deque<Phase> drawn = new ArrayDeque<>();
    Phase left;
    Phase right;
    Sound sound;

    private boolean tutorialFading = false;
    public Player player;

    public AnimationManager(GamePanel gp, Player player){
        this.gp = gp;
        this.player = player;
        Assets.load(player.speed);
        backgroundImage = Assets.backgroundImage;
        backgroundAnimation = Assets.backgroundAnimation;
        tutorial = Assets.tutorial;
        tutorialSprites = Assets.tutorialSprites;
        future = Assets.future;
        past = Assets.past;
        sound = Assets.sound;
    }


    // next two functions manage adding and removing the phases that are being drawn

    // when player moving right
    private void hideLeftAddRight(){
        if (!future.isEmpty()) {

            past.addLast(drawn.pollFirst());
            drawn.addLast(future.pollFirst());

            Phase phLeft = drawn.peekFirst();
            Phase phRight = drawn.peekLast();

            // add coordinates for the new phase (ph) on the right side of the current phase
            int offset = phLeft.picture.x + phLeft.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET + phLeft.textBox.sprites[0].getWidth() * Config.SCALE + Config.PIC_AREA_OFFSET;

            phRight.picture.x = offset;
            phRight.picture.y = Config.INIT_MIDPIC_Y;
            phRight.textBox.x = phRight.picture.x + phRight.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET * Config.SCALE;
            phRight.textBox.y = phRight.picture.y;
            //add x and y
        }
    }

    // when player moving left
    private void hideRightAddLeft(){
        if (!past.isEmpty()){

            int offset = drawn.peekFirst().picture.x + drawn.peekFirst().picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET + drawn.peekFirst().textBox.sprites[0].getWidth() * Config.SCALE + Config.PIC_AREA_OFFSET;

            future.addFirst(drawn.removeLast());
            drawn.addFirst(past.pollLast());


            Phase phLeft = drawn.peekFirst();
            Phase phRight = drawn.peekLast();

            // add coordinates for the new phase (ph) on the right side of the current phase

            phLeft.picture.x = phRight.picture.x - offset - Config.PIC_TEXTBOX_OFFSET;
            phLeft.picture.y = Config.INIT_MIDPIC_Y;
            phLeft.textBox.x = phLeft.picture.x + phLeft.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET * Config.SCALE;
            phLeft.textBox.y = phLeft.picture.y;

        }
    }

    public void playSF(Boolean loop){
        sound.setFile();
        if(!loop) sound.play();
        else sound.loop();
    }

    public void stopSF(){
        sound.stop();
    }

    public void updateAnimationsPositions(){

        if (player.state.name.equals("WALKING")) {

            if((sound.clip == null) || (!sound.clip.isActive())){
                playSF( true);
            }


            if (player.x < Config.LEFT_BOUNDARY) {
                if (player.movedLeft){
                    backgroundAnimation.x += (int) player.speed;

                    if (!drawn.isEmpty()) {
                        if (drawn.size() == 1) {
                            drawn.peekFirst().picture.x += (int) player.speed;
                            drawn.peekFirst().textBox.x += (int) player.speed;
                        } else if (drawn.size() == 2) {
                            drawn.peekFirst().picture.x += (int) player.speed;
                            drawn.peekFirst().textBox.x += (int) player.speed;
                            drawn.peekLast().picture.x += (int) player.speed;
                            drawn.peekLast().textBox.x += (int) player.speed;
                        }
                    }
                }
            } else if (player.x > Config.RIGHT_BOUNDARY) {
                if (player.movedRight){
                    backgroundAnimation.x -= (int) player.speed;

                    if (!drawn.isEmpty()) {
                        if (drawn.size() == 1) {
                            drawn.peekFirst().picture.x -= (int) player.speed;
                            drawn.peekFirst().textBox.x -= (int) player.speed;
                        } else if (drawn.size() == 2) {
                            drawn.peekFirst().picture.x -= (int) player.speed;
                            drawn.peekFirst().textBox.x -= (int) player.speed;
                            drawn.peekLast().picture.x -= (int) player.speed;
                            drawn.peekLast().textBox.x -= (int) player.speed;
                        }
                    }
                }
            }
        } else if(player.state.name.equals("SITTING")){
            if(sound.clip.isRunning()){
                stopSF();
            }
        }
    }


    // run all animatable objects if they are active:
    public void draw(Graphics2D g2){

        if (drawn.size() == 1){
            System.out.println("Ouchie");
        }

        // draw the BG
        if (backgroundImage != null){
            int width = Config.BG_WIDTH;
            int height = Config.BG_HEIGHT;

            //draw first and middle panel background
            backgroundAnimation.draw(g2, backgroundAnimation.x, backgroundAnimation.y, width, height);

            //draw left panel background or right panel based on the position of the main background sprite
            if(backgroundAnimation.x > Config.DRAW_BG_LEFT_BOUND) {
                backgroundAnimation.draw(g2, backgroundAnimation.x - width * Config.SCALE, backgroundAnimation.y, width, height);
            }  else if(backgroundAnimation.x < Config.DRAW_BG_RIGHT_BOUND) {  // draw right panel
                backgroundAnimation.draw(g2, backgroundAnimation.x + width * Config.SCALE, backgroundAnimation.y, width, height);
            }

            //reset middle bg sprite position
            if(backgroundAnimation.x > 1025 * Config.SCALE){
                backgroundAnimation.x = backgroundAnimation.x - width * Config.SCALE;
            }
            if(backgroundAnimation.x < - 1400 * Config.SCALE){
                backgroundAnimation.x = backgroundAnimation.x + width * Config.SCALE;
            }

            //System.out.println("BG middle X is " + backgroundAnimation.x );
            //System.out.println("Player X is " + player.x);

        } else {
            System.out.println("Warning: background is null, cannot draw.");
        }

        // draw the rest

        if (tutorial.active) {
            if ((player.x > Config.LEFT_BOUNDARY && player.x < Config.RIGHT_BOUNDARY) && !tutorialFading)
                tutorial.draw(g2, tutorial.x, tutorial.y, tutorial.sprites[0].getWidth(), tutorial.sprites[0].getHeight());
            else {
                //if we want it to fade in or out:
                tutorialFading = true;
                tutorial.draw(g2, tutorial.x, tutorial.y, tutorial.sprites[0].getWidth(),  tutorial.sprites[0].getHeight(), true);
            }
            tutorial.updateFrame();
        }

        Phase currentlyDrawn;
        if(drawn.isEmpty()){                // add the first phase
            currentlyDrawn = future.pollFirst();
            if(currentlyDrawn != null) {
                currentlyDrawn.picture.x = Config.INIT_MIDPIC_X;
                currentlyDrawn.picture.y = Config.INIT_MIDPIC_Y;
                currentlyDrawn.textBox.x = currentlyDrawn.picture.x + currentlyDrawn.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET * Config.SCALE;
                currentlyDrawn.textBox.y = currentlyDrawn.picture.y;
            }
            drawn.addFirst(currentlyDrawn);
        }
                                            //draw one phase
        if(!drawn.isEmpty()) {

            Phase phaseToDraw1 = drawn.peekFirst();
            int offset = phaseToDraw1.picture.x + phaseToDraw1.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET + phaseToDraw1.textBox.sprites[0].getWidth() * Config.SCALE + Config.PIC_AREA_OFFSET;



            phaseToDraw1.picture.draw(g2, phaseToDraw1.picture.x, phaseToDraw1.picture.y, phaseToDraw1.picture.sprites[0].getWidth(), phaseToDraw1.picture.sprites[0].getHeight());
            phaseToDraw1.picture.updateFrame(); // in case the animation is dynamic this will animate it
            phaseToDraw1.textBox.draw(g2, phaseToDraw1.textBox.x + Config.PIC_TEXTBOX_OFFSET, phaseToDraw1.textBox.y, phaseToDraw1.textBox.sprites[0].getWidth(), phaseToDraw1.textBox.sprites[0].getHeight());

            if ((phaseToDraw1.picture.x < 20) && (drawn.size() < 2) && (!future.isEmpty())) { // add second phase if there isn't any added and if future is not empty

                Phase phaseToAdd = future.pollFirst();

                phaseToAdd.picture.x = offset;
                phaseToAdd.picture.y = Config.INIT_MIDPIC_Y;
                phaseToAdd.textBox.x = phaseToAdd.picture.x + phaseToAdd.picture.sprites[0].getWidth() * Config.SCALE + Config.PIC_TEXTBOX_OFFSET * Config.SCALE;
                phaseToAdd.textBox.y = phaseToAdd.picture.y;
                drawn.addLast(phaseToAdd);
            }

            if (drawn.size() == 2){

                if((drawn.peekFirst().picture.x < Config.LEFT_BORDER_VISIBILITY)&& (player.movedRight)){
                    hideLeftAddRight();
                } else if((drawn.peekLast().picture.x > Config.RIGHT_BORDER_VISIBILITY) && (player.movedLeft)) {
                    hideRightAddLeft();
                }

                Phase phaseToDraw2 = drawn.peekLast();
                if (phaseToDraw2 != null) {
                    phaseToDraw1 = drawn.peekFirst();

                    phaseToDraw2.picture.draw(g2, phaseToDraw2.picture.x, phaseToDraw2.picture.y, phaseToDraw2.picture.sprites[0].getWidth(), phaseToDraw2.picture.sprites[0].getHeight());
                    phaseToDraw2.picture.updateFrame(); // in case the animation is dynamic this will animate it
                    phaseToDraw2.textBox.draw(g2, phaseToDraw2.textBox.x + Config.PIC_TEXTBOX_OFFSET, phaseToDraw2.textBox.y, phaseToDraw2.textBox.sprites[0].getWidth(), phaseToDraw2.textBox.sprites[0].getHeight());

                    //System.out.println("PIC 1 x is: " + phaseToDraw1.picture.x);
                }
            }

        }
    }

//if future.isEmpty();


}

