package Controllers.enemies;

import Controllers.GameVector;

/**
 * Created by Hieu It on 12/24/2016.
 */
public class MoveZigZagBehavior implements MoveBehavior {
    private static final int DIRECTION_DURATION =50 ;
    private GameVector moveVector;

    private int counter=0;

    public MoveZigZagBehavior() {
        this.moveVector = new GameVector(2,2);
    }

    @Override
    public void doMove(EnemyPlaneController enemyController) {
        counter++;
        if (counter >= DIRECTION_DURATION){
            counter=0;
            this.moveVector.dx = - this.moveVector.dx;
        }

        enemyController.move(moveVector);
    }
}
