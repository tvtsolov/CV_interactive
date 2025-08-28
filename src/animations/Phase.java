package animations;

import java.awt.*;

public class Phase {

    public Animation picture;
    public Animation textBox;

    public Phase(Animation txt, Animation pic){
        this.picture = pic;
        this.textBox = txt;
    }
//todo this needs to have it's own draw function
//    public void draw(){
//
//
//
//    }

}
