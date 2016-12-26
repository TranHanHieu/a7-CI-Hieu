package Controllers;

import Controllers.enemies.EnemyPlaneController;
import Controllers.managers.BodyManager;
import Models.Model;
import Views.SingleView;
import Views.View;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class BulletController extends Controller implements Body {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 30;


    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(0, -5);
    }

    public static BulletController creatBullet(int x, int y) {
        return new BulletController(
                new Model(x, y, WIDTH, HEIGHT),
                new SingleView(loadImage("resources/bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyPlaneController) {
            System.out.println("Hihihi");
            this.model.setAlive(false);
            ((EnemyPlaneController) other).destroy();

        }
    }
}
