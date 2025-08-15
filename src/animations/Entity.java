package animations;

public class Entity {

    public int worldX, worldY;
    public int x, y;

    public float speed;
    //public int step = 6;

    public String direction;

    public enum Direction {
        LEFT ,
        RIGHT
    }



    public int spriteCounter = 0;   // timer for the speed of the animation
    public int pauseCounter = 0;    // timer for pausing the animation


}
