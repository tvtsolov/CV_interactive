package main;

import Handlers.Assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Config {

    public static int SCALE = 2;

    public static final int screenWidth = 1000;
    public static final int screenHeight = 500;

    static public int BG_WIDTH = 1380;
    static public int BG_HEIGHT = 500;

    static public int INIT_BG_X = -375;
    public static int INIT_BG_Y = 0;

    public static BufferedImage plImage;

    static {
        try {
            plImage = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/player/walk/wlk1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int playerSpriteYSize = plImage.getHeight()*SCALE;
    public static int PLAYER_INIT_POS_X = 30 * SCALE;
    public static int PLAYER_INIT_POS_Y = (BG_HEIGHT * SCALE) - playerSpriteYSize * SCALE;
    public static float PLAYER_SPEED = 3F;


    //cat movement boundaries that trigger the camera movement
    static public int LEFT_BOUNDARY = 0;
    static public int RIGHT_BOUNDARY = 200;


    public Config() throws IOException {
    }
}
