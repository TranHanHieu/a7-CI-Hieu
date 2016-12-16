package Controllers;

import Controllers.managers.BodyManager;
import Models.Model;
import Views.View;

import java.awt.*;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class BulletController extends Controller implements Body {

    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(0, -5);
    }

    public static BulletController creatBullet(int x, int y) {
        return new BulletController(
                new Model(x, y, 12, 30),
                new View(loadImage("resources/bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyPlaneController) {
            this.model.setAlive(false);

        }
    }
}
