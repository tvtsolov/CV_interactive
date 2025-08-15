package Handlers;

import animations.Animation;
import animations.FramedPicture;
import animations.TextBox;
import main.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Assets{

    public static BufferedImage backgroundImage;
    public static Animation backgroundAnimation;
    public static BufferedImage[] tutorialSprites;
    public static Animation tutorial;
    public static FramedPicture[] pictures;

    public static void load(float speed) {

        tutorialSprites = new BufferedImage[2];

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/background/test-bg.png")));

            backgroundAnimation = new Animation(backgroundImage, Config.INIT_BG_X, Config.INIT_BG_Y, speed);

            tutorialSprites[0] = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons1.png")));
            tutorialSprites[1] = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons2.png")));
            tutorial = new Animation(tutorialSprites, 100, 100, 0.13F); // these coordinates are always relative to the window

            {
                pictures = new FramedPicture[5];
                String VTUmessage = Config.TXTBOX_MESSAGES.get("VTU");
                BufferedImage pictureVTU = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons1.png")));             //todo I don't have a sprite for it yet
                TextBox textBoxVTU = new TextBox(VTUmessage);
                FramedPicture VTU = new FramedPicture("VTU", textBoxVTU, pictureVTU, 0, 0);


                String NBUmessage = Config.TXTBOX_MESSAGES.get("NBU");
                BufferedImage pictureNBU = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons1.png")));             //todo I don't have a sprite for it yet
                TextBox textBoxNBU = new TextBox(NBUmessage);
                FramedPicture NBU = new FramedPicture("NBU", textBoxNBU, pictureNBU, 0, 0);


                String psyWorkMessage = Config.TXTBOX_MESSAGES.get("psyWork");
                BufferedImage picturePsyWork = ImageIO.read(Objects.requireNonNull(Assets.class.getResourceAsStream("/buttons/dir_buttons1.png")));             //todo I don't have a sprite for it yet
                TextBox textBoxPsyWork = new TextBox(psyWorkMessage);
                FramedPicture psyWork = new FramedPicture("psyWork", textBoxPsyWork, picturePsyWork, 0, 0);


            }





        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}
