package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public String direction;

    enum State {
        SITTING,
        WALKING
    }

    enum Direction {
        LEFT,
        RIGHT
    }


    public int spriteCounter = 0;
    public int spriteNum = 1;


    //BufferedImage[][] names = { {1, 2}, {1, 2} };

    //state[State.SITTING][Direction.RIGHT] = 2;

}
