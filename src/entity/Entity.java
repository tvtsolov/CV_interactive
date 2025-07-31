package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;
    public int step = 6;
    public State prevState;
    public State state;

    public String direction;

    public enum State {
        SITTING,
        WALKING
    }

    public enum Direction {
        LEFT,
        RIGHT
    }


    public int spriteCounter = 0;
    public int pauseCounter = 0;

    //BufferedImage[][] names = { {1, 2}, {1, 2} };

    //state[State.SITTING][Direction.RIGHT] = 2;

}
