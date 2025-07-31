package main;

import entity.Entity;
import entity.Player;

public class AnimationManager {

    static public Animation prevAnimation;
    static public Animation currAnimation;
    private Player player;

    public AnimationManager(Player player) {
        this.player = player;
    }

    public void updateStates(){
        prevAnimation = currAnimation;
        //currAnimation = player.getPlayerState();
    }



}
