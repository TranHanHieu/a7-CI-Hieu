package Models;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneModel {
    private int x;
    private int y;

    public PlaneModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
