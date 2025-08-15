package animations;

import java.awt.image.BufferedImage;

public class FramedPicture {
    TextBox textBox;
    BufferedImage picture;
    int x;
    int y;
    String name;

    public FramedPicture(String name, TextBox tb, BufferedImage pic, int x, int y){
        this.x = x;
        this.y = y;
        this.name = name;
        this.textBox = tb;
        this.picture = pic;
    }

    public void drawTextBox(){

        if(textBox != null)
        {

        }
    }
}
