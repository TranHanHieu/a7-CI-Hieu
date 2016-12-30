package Controllers.enemies;

import Controllers.BulletController;
import Controllers.managers.ControllerManager;

/**
 * Created by Hieu It on 12/21/2016.
 */
public interface ShootBehavior {
    void doShoot(EnemyPlaneController enemyPlaneController);
}
