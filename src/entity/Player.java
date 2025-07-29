package entity;

import main.GamePanel;
import main.KeyHandler;

import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.lang.model.type.NullType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage[] walking = new BufferedImage[8];
    public BufferedImage[] sitting = new BufferedImage[18];

    int scale;
    public Direction direction;
    public State state;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues(); // sets speed as well as X and Y
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.RIGHT;
        state = State.SITTING;
        scale = 4;
    }

    public void getPlayerImage() {

        try{
            for(int i = 0; i < walking.length; i++){
                String str = "/player/walk/wlk" + (i+1) + ".png";
                walking[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }
            for(int i = 0; i < sitting.length; i++){
                String str = "/player/sit/sitting" + (i+1) + ".png";
                sitting[i] = ImageIO.read(getClass().getResourceAsStream(str));
            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void update(){
        if(keyH.upPressed){
            y -= speed;
        } else if (keyH.downPressed){
            y += speed;
        } else if(keyH.leftPressed){
            x -= speed;
            direction = Direction.LEFT;
        } else if(keyH.rightPressed){
            x += speed;
            direction = Direction.RIGHT;
        }


    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (state == State.WALKING) {
            if (direction == Direction.LEFT)  {
                image = walking[0];
            }  else if(direction == Direction.RIGHT) {
                image = walking[0];
            }
        } else if (state == State.SITTING){
            image = sitting[0];
        }




        if (image != null) {
            g2.drawImage(image, x, y, image.getWidth()*scale, image.getHeight()*scale, null);
        } else {
            // Optionally, draw a placeholder or log a warning
            System.out.println("Warning: image is null, cannot draw.");
        }



    }

}
