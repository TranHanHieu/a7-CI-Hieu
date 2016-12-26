package Controllers.enemies;

/**
 * Created by Hieu It on 12/21/2016.
 */
public class MoveLeftRightBehavior implements MoveBehavior {
    @Override
    public void doMove(EnemyPlaneController enemyController) {
        enemyController.getModel().move(5,5);
    }
}
