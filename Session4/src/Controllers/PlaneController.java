package Controllers;

import Models.Model;
import Views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneController extends Controller {

    private static final int SPEED = 5;
    private KeySetting keySetting;

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public PlaneController(Model model, View view) {
        super(model, view);
    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keySetting != null) {
            if (keyCode == keySetting.getKeyUp()) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.getKeyDown()) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.getKeyLeft()) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                model.move(SPEED, 0);
            }
        }
    }

    //Design pattern factory.
    public static PlaneController creatPlane(int x, int y) {
        return new PlaneController(
                new Model(x, y, 70, 50),
                new View(loadImage("resources/plane3.png")));

    }
}
