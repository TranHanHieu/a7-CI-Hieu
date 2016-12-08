package Controllers;

import Models.Model;
import Views.View;

import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyPlaneController extends Controller {

    private EnemyBulletController enemyBulletController =
            EnemyBulletController.creatEnemyBulletController(this.getModel().getX() + 30, this.getModel().getY() + 50);
    private Vector<EnemyBulletController> enemyBulletControllerVector = new Vector<>();

    public EnemyBulletController getEnemyBulletController() {
        return enemyBulletController;
    }

    public Vector<EnemyBulletController> getEnemyBulletControllerVector() {
        return enemyBulletControllerVector;
    }

    public EnemyPlaneController(Model model, View view) {
        super(model, view);
    }

    public static EnemyPlaneController creatEnemyAircraft(int x, int y) {
        return new EnemyPlaneController(
                new Model(x, y, 70, 50),
                new View(loadImage("resources/plane1.png")));
    }

    public void run() {
        this.model.move(0, 1);

        if (enemyBulletController.getModel().getY() > 10000) {
            enemyBulletController = EnemyBulletController.creatEnemyBulletController(this.getModel().getX() + 30,
                    this.getModel().getY() + 50);
        }

        enemyBulletControllerVector.add(enemyBulletController);
    }
}
