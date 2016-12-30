package Controllers.enemies;

import Controllers.BulletController;
import Controllers.managers.ControllerManager;

/**
 * Created by Hieu It on 12/21/2016.
 */
public class ShootStraightDownBehavior implements ShootBehavior {

    @Override
    public void doShoot(EnemyPlaneController enemyPlaneController) {
        int x = enemyPlaneController.getModel().getMidX() - 5;
        int y = enemyPlaneController.getModel().getY() + 30;
        EnemyBulletController enemyBulletController = EnemyBulletController.creatEnemyBulletController(x, y);
        ControllerManager.enemyBullet.add(enemyBulletController);
    }
}
