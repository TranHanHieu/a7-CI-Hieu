package Controllers.scenes;

import Controllers.BaseController;
import Controllers.GameSetting;
import Controllers.PlaneController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Controllers.managers.EnemyControllerManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/28/2016.
 */
public class PlayGameScenes extends GameScenes {
    Image background;
    Vector<BaseController> controllers;

    public PlayGameScenes() {
        if (!PlaneController.instance.getModel().isAlive()) {
            reset();
        }
        controllers = new Vector<>();
        controllers.add(new EnemyControllerManager());
        controllers.add(PlaneController.instance);
        controllers.add(BodyManager.instance);
        controllers.add(ControllerManager.enemyBullet);
        controllers.add(ControllerManager.explosion);
        controllers.add(ControllerManager.instance);
        background = loadImage("resources/background.png");
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, GameSetting.instance.getWitdh(), GameSetting.instance.getHeight(), null);

        for (BaseController baseController : controllers) {
            baseController.draw(g);
        }
    }

    @Override
    public void rụn() {
        for (BaseController baseController : controllers) {
            baseController.run();
        }
        if (!PlaneController.instance.getModel().isAlive()) {
            this.sceneListener.replaceScene(new GameOverListener(), false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlaneController.instance.keyPressed(e);
    }

    public void reset() {
        BodyManager.instance.reset();
        ControllerManager.instance.reset();
        PlaneController.instance.reset();
        EnemyControllerManager.instance.reset();
        BodyManager.instance.register(PlaneController.instance);
    }
}
