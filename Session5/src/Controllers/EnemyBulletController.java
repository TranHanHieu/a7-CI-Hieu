package Controllers;

import Controllers.managers.BodyManager;
import Models.Model;
import Views.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyBulletController extends Controller implements Body {

    private static final int SPEED = 5;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;


    public EnemyBulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(0, SPEED);
    }

    public static EnemyBulletController creatEnemyBulletController(int x, int y) {
        return new EnemyBulletController(
                new Model(x - 11, y - 10, WIDTH, HEIGHT),
                new View(loadImage("resources/enemy_bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            //System.out.println("aaaaaa");
            this.model.setAlive(false);
            System.out.println(model.isAlive());
        }
    }
}
