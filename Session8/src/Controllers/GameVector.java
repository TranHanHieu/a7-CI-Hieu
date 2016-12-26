package Controllers;

/**
 * Created by Hieu It on 12/24/2016.
 */
public class GameVector {
    public int dx;
    public int dy;

    public GameVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getLength(){
        return Math.sqrt(dx * dx + dy*dy);
    }
}
