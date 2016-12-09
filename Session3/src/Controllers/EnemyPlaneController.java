package Controllers;

import Models.Model;
import Views.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static Controllers.EnemyBulletController.creatEnemyBulletController;
import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyPlaneController extends Controller {
    private Vector<EnemyBulletController> enemyBulletControllerVector = new Vector<>();

    private EnemyBulletController enemyBulletController = creatEnemyBulletController(getModel().getX() + 30,
            getModel().getY() + 50);

    public Vector<EnemyBulletController> getEnemyBulletControllerVector() {
        return enemyBulletControllerVector;
    }

    public EnemyPlaneController(Model model, View view) {
        super(model, view);
    }

    public static EnemyPlaneController creatEnemyPlane(int x, int y) {
        return new EnemyPlaneController(
                new Model(x, y, 70, 50),
                new View(loadImage("resources/plane1.png")));
    }

    public void run() {
        this.model.move(0, 1);

        if (enemyBulletController.getModel().getY() > 1000) {

            enemyBulletController = creatEnemyBulletController(getModel().getX() + 30,
                    getModel().getY() + 50);

        }
        enemyBulletControllerVector.add(enemyBulletController);

        for (int i = 0; i < enemyBulletControllerVector.size(); i++) {
            enemyBulletControllerVector.get(i).run();
            if (enemyBulletControllerVector.get(i).getModel().getY() > 600) {
                enemyBulletControllerVector.remove(i);
            }
        }
    }
}
