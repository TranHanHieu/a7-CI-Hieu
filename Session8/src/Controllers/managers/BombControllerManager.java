package Controllers.managers;

import Controllers.BombController;

import java.util.Random;

import static Controllers.BombController.creatBomb;

/**
 * Created by Hieu It on 12/26/2016.
 */
public class BombControllerManager extends ControllerManager {
    private int timerCount=0;

    public void spawnBomb(){

        BombController bombController = creatBomb(new Random().nextInt(400) + 100,0);
        controllers.add(bombController);
    }

    @Override
    public void run() {
        super.run();
        timerCount++;
        if (timerCount>50) {
            spawnBomb();
            timerCount =0;
        }
    }
}
