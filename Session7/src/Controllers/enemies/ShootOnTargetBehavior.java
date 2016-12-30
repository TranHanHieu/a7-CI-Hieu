package Controllers.enemies;

import Controllers.BulletController;
import Controllers.GameVector;
import Controllers.PlaneController;
import Controllers.managers.ControllerManager;

/**
 * Created by Hieu It on 12/21/2016.
 */
public class ShootOnTargetBehavior implements ShootBehavior {


    private final int SPEED = 5;

    @Override
    public void doShoot(EnemyPlaneController enemyController) {
        int x = enemyController.getModel().getMidX();
        int y = enemyController.getModel().getBottom();

        PlaneController planeController = PlaneController.instance;

        GameVector dVector = planeController.getModel().subtract(enemyController.getModel());
        double length = dVector.getLength();

        double steps = length / (double) SPEED;
        GameVector bulletMoveVector = new GameVector((int) (dVector.dx / steps), (int) (dVector.dy / steps));

        EnemyBulletController enemyBulletController =
                EnemyBulletController.creatEnemyBulletController(x, y, bulletMoveVector);

        ControllerManager.enemyBullet.add(enemyBulletController);
    }
}
