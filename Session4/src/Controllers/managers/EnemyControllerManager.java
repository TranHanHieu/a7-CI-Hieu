package Controllers.managers;

import Controllers.EnemyPlaneController;
import Controllers.EnemyType;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Hieu It on 12/10/2016.
 */
public class EnemyControllerManager extends ControllerManager {

    private static int timerCounter = 0;
    private static int timerCounter1 = 0;

    private void spawn() {
        timerCounter1++;
        EnemyPlaneController enemyPlane;
        if (timerCounter1 % 5 == 0) {
            enemyPlane = EnemyPlaneController.creatEnemyPlane(750, new Random().nextInt() % 100 + 40, EnemyType.LEFT);
        } else {
            enemyPlane = EnemyPlaneController.creatEnemyPlane(new Random().nextInt() % 600 + 100, 0, EnemyType.DOWN);
        }
        if (timerCounter1 % 1 == 0 && timerCounter1 < 8) {
            enemyPlane = EnemyPlaneController.creatEnemyPlane(0, 100, EnemyType.ALWAYS);
        }
        this.controllers.add(enemyPlane);
    }

    @Override
    public void run() {
        super.run();
        timerCounter++;
        if (timerCounter > 50) {
            spawn();
            timerCounter = 0;
        }
    }
}
