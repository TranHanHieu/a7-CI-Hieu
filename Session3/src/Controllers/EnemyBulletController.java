package Controllers;

import Models.Model;
import Views.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyBulletController extends Controller {

    public EnemyBulletController(Model model, View view) {
        super(model, view);
    }

    public void run() {
        this.model.move(0, 5);
    }

    public static EnemyBulletController creatEnemyBulletController(int x, int y) {
        return new EnemyBulletController(
                new Model(x - 11, y - 10, 32, 32),
                new View(loadImage("resources/enemy_bullet.png")));
    }
}
