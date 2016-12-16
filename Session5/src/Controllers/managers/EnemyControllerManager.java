package Controllers.managers;

import Controllers.EnemyPlaneController;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Hieu It on 12/10/2016.
 */
public class EnemyControllerManager extends ControllerManager {

    private static int timerCounter = 0;

    private void spawn() {
        EnemyPlaneController enemyPlane = EnemyPlaneController.creatEnemyPlane(new Random().nextInt() % 600 + 100, 0);
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
