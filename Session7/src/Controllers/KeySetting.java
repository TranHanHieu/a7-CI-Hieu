package Controllers;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class KeySetting {

    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;
    private int keySpace;

    public int getKeySpace() {
        return keySpace;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public KeySetting(int keyUp, int keyDown, int keyLeft, int keyRight,int keySpace) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keySpace = keySpace;

    }
}
