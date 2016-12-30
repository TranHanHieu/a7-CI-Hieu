package Controllers;

import Controllers.enemies.EnemyBulletController;
import Controllers.enemies.EnemyPlaneController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Controllers.notifications.EventSubcriber;
import Controllers.notifications.EventType;
import Controllers.scenes.SceneListener;
import Models.Model;
import Utils.Utils;
import Views.SingleView;
import Views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneController extends Controller implements Body,EventSubcriber {

    private static final int SPEED = 5;
    public  KeySetting keySetting;
    private ControllerManager bulletManager;

    public static final PlaneController instance = creatPlane(350,500);

    private PlaneController(Model model, View view) {
        super(model, view);
        bulletManager = new ControllerManager();
        BodyManager.instance.register(this);
    }

    public void reset(){
        PlaneController.instance.getModel().setX(350);
        PlaneController.instance.getModel().setY(500);
        PlaneController.instance.getModel().setAlive(true);

    }


    public void keyPressed(KeyEvent e) {


        if (keySetting != null) {
            int keyCode = e.getKeyCode();
            if (keyCode == keySetting.getKeyUp()) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.getKeyDown()) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.getKeyLeft()) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                model.move(SPEED, 0);
            } else if (keyCode ==keySetting.getKeySpace()){
                    shoot();
            }
        }
    }

    private void shoot() {
        Utils.playSound("resources/shoot.wav", false);
        BulletController bulletController = BulletController.creatBullet(this.model.getMidX() - BulletController.WIDTH/ 2,
                this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    //Design pattern factory.
    private static PlaneController creatPlane(int x, int y) {
        return new PlaneController(
                new Model(x, y, 70, 50),
                new SingleView(loadImage("resources/plane3.png")));

    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyBulletController ||other instanceof EnemyPlaneController){
            this.model.setAlive(false);
        }
    }

    @Override
    public void run() {
        super.run();
        bulletManager.run();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        bulletManager.draw(g);
    }

    @Override
    public boolean onEvent(EventType eventType, Object params) {
        switch (eventType){
            case PLANE_DIE:
                this.destroy();
                return false;

        }
        return true;
    }
}
