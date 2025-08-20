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

    static public Map<String, String> TXTBOX_MESSAGES = new HashMap<>();
    static {
        TXTBOX_MESSAGES.put("VTU", "Bachelor of Psychology \n" + "University of Veliko Turnovo 2005 2009 \n");
        TXTBOX_MESSAGES.put("NBU", "Master's degree in Clinical psychology\n" + "NBU 2010 2012\n");
        TXTBOX_MESSAGES.put("Summary", "I am curious and communicative. I am very eager to learn new things \n" +
                "and have been programming since the beginning of 2023, \n" +
                "when I decided to start looking for new career opportunities.\n" +
                " After I graduated from Telerik and a 6 month course in C++ \n" +
                "in SoftUni I started looking for a job but not long after that \n" +
                "I had to pause my search for a year for financial reasons and \n" +
                "now I am back on the market. I still work full time in another\n" +
                " field (web hosting) but I can't wait to find a job as a junior\n" +
                " developer. I haven't stopped programming since 2023, \n" +
                "I kept working on my game and I will soon update the list of my projects \n" +
                "below to include a short presentation of where I got to. If you consider /n" +
                "me for an interview I would be super eager to talk to you./n " +
                "And if I don't get the job I will definitely ask you for feedback >:). /n" +
                "I want to know what the gaps in my knowledge are and what I can improve on. \n");
        TXTBOX_MESSAGES.put("psyWork", "2014 - 2023\n " +
                "Different schools and institutions\n" +
                "Sofia\n" +
                "Psychologist, Child Psychologist, Psychotherapist\n" +
                "This was the period when I was invested in my psychotherapy career \n" +
                "that I was working in parallel to the other jobs you see listed here.\n" +
                "During this period I have worked in different positions starting from/n" +
                " a school psychologist to an adult psychotherapist.\n" +
                " I learned a lot about people and myself during this period and /n" +
                "how to work with a really vast array of people.\n");
        TXTBOX_MESSAGES.put("VT", "Bachelor of Psychology \n" + "University of Veliko Turnovo 2005 2009 \n");
        TXTBOX_MESSAGES.put("VT", "Bachelor of Psychology \n" + "University of Veliko Turnovo 2005 2009 \n");
        TXTBOX_MESSAGES.put("VT", "Bachelor of Psychology \n" + "University of Veliko Turnovo 2005 2009 \n");
        TXTBOX_MESSAGES.put("VT", "Bachelor of Psychology \n" + "University of Veliko Turnovo 2005 2009 \n");
    }


    public Config() throws IOException {
    }
}
