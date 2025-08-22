package animations;

import java.awt.*;

public class Phase {

    public Animation picture;
    int pictureX;
    int pictureY;
    public Animation textBox;
    int textBoxX;
    int textBoxY;

    public Phase(Animation txt,int textBoxX, int textBoxY, Animation pic,  int picX, int picY){
        this.picture = pic;
        this.pictureX = picX;
        this.pictureY = picY;
        this.textBox = txt;
        this.textBoxX = textBoxX;
        this.textBoxY = textBoxY;
    }
}
