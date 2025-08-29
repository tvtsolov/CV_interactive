package main;

import Handlers.Assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Config {

    public static int SCALE = 1;

    public static final int screenWidth = 1000;
    public static final int screenHeight = 500;

    public static int tutorialX = 100 * SCALE;
    public static int tutorialY = 100 * SCALE;


    static public int BG_WIDTH = 1380;
    static public int BG_HEIGHT = 500;

    static public int PIC_TEXTBOX_OFFSET    = 0;
    static public int PIC_AREA_OFFSET       = 150 * SCALE;

    static public int INIT_MIDPIC_X         = 500 * SCALE;
    static public int INIT_MIDPIC_Y         = 0 * SCALE;

    static public int LEFT_BORDER_VISIBILITY    = -955 * SCALE;
    static public int RIGHT_BORDER_VISIBILITY   = 1010 * SCALE;

    static public int INIT_BG_X                 = -187 * SCALE;
    public static int INIT_BG_Y                 = 0 * SCALE;

    public static BufferedImage plImage;

    static {
        try {
            plImage = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/player/walk/wlk1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int playerSpriteHeight = plImage.getHeight();
    public static int PLAYER_INIT_POS_X = 30 * SCALE;
    public static int PLAYER_INIT_POS_Y = (BG_HEIGHT * SCALE) - playerSpriteHeight * SCALE ;
    public static float PLAYER_SPEED = 3F * SCALE;

    public static int DRAW_BG_LEFT_BOUND = 3 * SCALE;
    public static int DRAW_BG_RIGHT_BOUND = -350 + SCALE;

    //cat movement boundaries that trigger the camera movement
    static public int LEFT_BOUNDARY = 25 * SCALE;
    static public int RIGHT_BOUNDARY = 100 * SCALE;

    public static void setValues(){
        playerSpriteHeight = plImage.getHeight();
        PLAYER_INIT_POS_X = 30 * SCALE;
        PLAYER_INIT_POS_Y = (BG_HEIGHT * SCALE) - playerSpriteHeight * SCALE ;
        PLAYER_SPEED = 3F * SCALE;
        DRAW_BG_LEFT_BOUND = 3 * SCALE;
        DRAW_BG_RIGHT_BOUND = -350 + SCALE;
        LEFT_BOUNDARY = 25 * SCALE;
        RIGHT_BOUNDARY = 100 * SCALE;
        PIC_TEXTBOX_OFFSET    = 0;
        PIC_AREA_OFFSET       = 150 * SCALE;
        INIT_MIDPIC_X         = 500 * SCALE;
        INIT_MIDPIC_Y         = 0 * SCALE;
        LEFT_BORDER_VISIBILITY    = -955 * SCALE;
        RIGHT_BORDER_VISIBILITY   = 1010 * SCALE;
        INIT_BG_X                 = -187 * SCALE;
        INIT_BG_Y                 = 0 * SCALE;
        tutorialX = 100 * SCALE;
        tutorialY = 100 * SCALE;
    }





}
