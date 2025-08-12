package main;

public final class Config {

    public static int SCALE = 3;

    public static final int screenWidth = 500;
    public static final int screenHeight = 250;

    public static int PLAYER_INIT_POS_X = 100;
    public static int PLAYER_INIT_POS_Y = screenHeight * SCALE - 215;
    public static float PLAYER_SPEED = 3F;

    // scale 2 measurements for BG
    static public int BG_WIDTH = 2250;
    static int BG_HEIGHT = 750;
    static int INIT_BG_X = -375;
    static int INIT_BG_Y = 0;

    //cat movement boundaries that trigger the camera movement
    static public int LEFT_BOUNDARY = 0;
    static public int RIGHT_BOUNDARY = 200;

}
