package Handlers;

import animations.Animation;
import animations.Phase;
import main.Config;
import main.Sound;
import main.UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

public class Assets{

    public static BufferedImage backgroundImage;
    public static Animation backgroundAnimation;
    public static BufferedImage[] tutorialSprites;

    public static Animation tutorial;
    public static Phase[] phases = new Phase[9];
    public static Deque<Phase> future = new ArrayDeque<>();
    public static Deque<Phase> past = new ArrayDeque<>();

    public static Sound sound;
    public static Sound music;

    public static UI menu;
    public static BufferedImage[] menuSprites;

    public static void load(float speed) {

        tutorialSprites = new BufferedImage[2];
        menuSprites = new BufferedImage[2];

        try {

                BufferedImage UI1 = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/UI/UI1.png")));
                BufferedImage UI2 = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/UI/UI2.png")));
                menuSprites[0] = UI1;
                menuSprites[1] = UI2;
                menu = new UI(menuSprites);

                backgroundImage = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/background/test-bg.png")));

                backgroundAnimation = new Animation(backgroundImage, Config.INIT_BG_X, Config.INIT_BG_Y, speed);

                tutorialSprites[0] = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons1.png")));
                tutorialSprites[1] = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons2.png")));
                tutorial = new Animation(tutorialSprites, Config.tutorialX, Config.tutorialY, 0.13F); // these coordinates are always relative to the window



                for(int i = 0; i < phases.length; i++){
                    Animation pictureAnimation;
                    if(i != 4 && i != 6 && i !=7) {
                        String picPath = "/phases/pics/pic" + i + "/pic" + i + ".png";
                        BufferedImage picSprite = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream(picPath)));
                        pictureAnimation = new Animation(picSprite);
                    } else {
                        String picPath1 = "/phases/pics/pic" + i + "/pic" + i + "-1.png";
                        String picPath2 = "/phases/pics/pic" + i + "/pic" + i + "-2.png";
                        BufferedImage picSprite1 = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream(picPath1)));
                        BufferedImage picSprite2 = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream(picPath2)));
                        BufferedImage[] sprites = new BufferedImage[2];
                        sprites[0] = picSprite1;
                        sprites[1] = picSprite2;
                        pictureAnimation = new Animation(sprites, 0, 0, .2F);
                    }
                    String textBoxPath = "/phases/textboxes/box"+ i + "/textbox" + i + ".png";
                    BufferedImage textBoxSprite = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream(textBoxPath)));
                    Animation textBox = new Animation(textBoxSprite);
                    Phase ph = new Phase(textBox, pictureAnimation);
                    phases[i] = ph;
                    future.addLast(ph);
                }

                //Sounds
                sound = new Sound("/sounds/steps_cat.wav");
                music = new Sound("/sounds/game_music.wav");

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}
