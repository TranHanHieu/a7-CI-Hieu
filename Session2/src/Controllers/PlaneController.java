package Controllers;

import Models.PlaneModel;
import Views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneController {
    private PlaneModel planeModel;
    private PlaneView planeView;
    private KeySetting keySetting;

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public PlaneController(PlaneModel planeModel, PlaneView planeView, KeySetting keySetting) {
        this.planeModel = planeModel;
        this.planeView = planeView;
        this.keySetting = keySetting;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keySetting != null) {
            if (keyCode == keySetting.getKeyUp()) {
                planeModel.move(0, -5);
            } else if (keyCode == keySetting.getKeyDown()) {
                planeModel.move(0, 5);
            } else if (keyCode == keySetting.getKeyLeft()) {
                planeModel.move(-5, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                planeModel.move(5, 0);
            }
        }
    }

    public void draw(Graphics g) {
        planeView.draw(g, planeModel);
    }

    public void run() {

    }
}
