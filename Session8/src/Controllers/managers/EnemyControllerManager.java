package Controllers.managers;

import Controllers.BombController;
import Controllers.Controller;
import Controllers.enemies.EnemyPlaneController;
import Controllers.enemies.EnemyType;
import Utils.Utils;

import java.util.Random;

/**
 * Created by Hieu It on 12/10/2016.
 */
public class EnemyControllerManager extends ControllerManager {

    private static int timerCounter = 0;
    private static int enemyCount = 0;

    private void spawn() {
        enemyCount++;
        EnemyPlaneController enemyPlane = null;
        if (enemyCount%4==0){
             enemyPlane = EnemyPlaneController.creatEnemyPlane(new Random().nextInt() % 600 + 100, 0, EnemyType.WHITE);
        }else {
             enemyPlane = EnemyPlaneController.creatEnemyPlane(new Random().nextInt() % 600 + 100, 0, EnemyType.BROWN);
        }

        this.controllers.add(enemyPlane);
    }

    @Override
    public void run() {
        super.run();
        timerCounter++;
        if (timerCounter > 100) {
            spawn();
            timerCounter = 0;
        }
        if (!BombController.isAliveBomb){
            for (Controller controller : controllers) {
                controller.getModel().setAlive(false);
                if(controller instanceof EnemyPlaneController){
                    ((EnemyPlaneController) controller).destroy();
                }
                BombController.isAliveBomb = true;
            }
        }
    }
}
