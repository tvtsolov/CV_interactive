package main;

import entity.Entity;
import entity.Player;

public class StateManager {

    Player player;
    KeyHandler kh;

    public StateManager(Player player, KeyHandler kh){
        this.player = player;
        this.kh = kh;
    }

    public void run(){
        if(kh.leftPressed){
            player.pauseCounter = 0;
            player.x -= player.speed;
            player.setState("WALKING");
            System.out.println("state is now WALKING");
            player.direction = Entity.Direction.LEFT;
            if(player.spriteCounter > player.step){
                player.state.animation.updateFrame();
                player.spriteCounter = 0;
            }
        } else if(kh.rightPressed){
            player.pauseCounter = 0;
            player.x += player.speed;
            player.setState("WALKING");
            System.out.println("state is now WALKING");
            player.direction = Entity.Direction.RIGHT;
            if(player.spriteCounter > player.step){
                player.state.animation.updateFrame();
                player.spriteCounter = 0;
            }
        } else { // if not moving at all, no arrows are being pressed
            if (player.prevState.name.equals("WALKING") && !player.animateSittingDown) {
                //start sitting down animation
                player.setState("SITTING");
                System.out.println("state is now SITTING");
                player.animateSittingDown = true;
                player.state.setFrame(1);
            } else if (player.animateSittingDown){
                player.setState("SITTING");
                player.state.animation.updateFrame();
                player.spriteCounter = 0;
            } else {
                if(player.prevState.name.equals("SITTING")){
                    player.state.setFrame(1);
                }
                player.setState("SAT");
                System.out.println("state is now SAT");
                player.pauseCounter++;

                if (!player.sat.animationPaused) { // always start the sat animation with a pause
                    player.sat.timer = player.sat.rand.nextInt(player.sat.minPauseTime, player.sat.maxPauseTime); // sets when we will unpause/start animating the sat animation
                    player.sat.animationPaused = true;
                } else { // State.SAT
                    if (player.pauseCounter > player.sat.timer) { // time to animate the idle animation
                        player.setState("SAT");
                        System.out.println("State is now SAT");
                        if (player.spriteCounter > player.step) { // time for the next frame
                            player.state.animation.updateFrame();
                            player.spriteCounter = 0;
                        }
                        if (player.sat.currentFrame >= player.sat.sprites.length){ // if sat animation is over
                            player.pauseCounter = 0;
                            player.sat.animationPaused = false;
                        }
                    }
                }
            }
        }
    }

}
