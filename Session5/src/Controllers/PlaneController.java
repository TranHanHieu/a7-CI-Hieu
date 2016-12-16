package Controllers;

import Controllers.managers.BodyManager;
import Models.Model;
import Views.Animation;
import Views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;
    private KeySetting keySetting;
    //public static Animation animation;

//    public Animation getAnimation() {
//        return animation;
//    }
//
//    public void setAnimation(Animation animation) {
//        this.animation = animation;
//    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public PlaneController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
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

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (animation != null) {
            animation.draw(g);
        }
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyBulletController || other instanceof EnemyPlaneController) {
            //System.out.println("BBBBBBBBB");
            this.model.setAlive(false);
            animation = new Animation(model, view, "resources/explosion1.png,resources/explosion2.png,resources/explosion3.png,resources/explosion4.png,resources/explosion5.png,resources/explosion6.png");

        }
    }
}
