package Controllers.enemies;

import Controllers.Body;
import Controllers.BulletController;
import Controllers.Controller;
import Controllers.ExplosionController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Models.Model;
import Utils.Utils;
import Views.Animation;
import Views.SingleView;
import Views.View;

import javax.rmi.CORBA.Util;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static Controllers.enemies.EnemyBulletController.creatEnemyBulletController;
import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */

public class EnemyPlaneController extends Controller implements Body {

    private static final int WIDTH = 70;
    private static final int HEIGHT = 50;
    private int timeCounter;
    private int bulletCount = 0;

    private MoveBehavior moveBehavior;

    private ShootBehavior shootBehavior;

//    private Vector<EnemyBulletController> enemyBulletControllerVector;

    private EnemyBulletController enemyBulletController;


    public EnemyPlaneController(Model model, View view,MoveBehavior moveBehavior,ShootBehavior shootBehavior) {
        super(model, view);

        timeCounter = 0;
        this.moveBehavior = moveBehavior;
        this.shootBehavior = shootBehavior;
        BodyManager.instance.register(this);
    }

    public static EnemyPlaneController creatEnemyPlane(int x, int y,EnemyType type) {
        switch (type){
            case BROWN:
                return new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(loadImage("resources/plane1.png")),
                        new MoveZigZagBehavior(),
                        new ShootStraightDownBehavior());

            case GREEN:
                return new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new SingleView(loadImage("resources/enemy-green-3.png")),
                        new MoveLeftRightBehavior(),
                        new ShootOnTargetBehavior());
            case WHITE:
                Vector<BufferedImage> images = new Vector<>();
                images.add(Utils.loadImage("resources/enemy_plane_white_1.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_2.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_3.png"));
                return new EnemyPlaneController(
                        new Model(x, y, WIDTH, HEIGHT),
                        new Animation(images),
                        new MoveLeftRightBehavior(),
                        new ShootOnTargetBehavior()
                );
        }

        return null;
    }

    public void shoot() {

//        bulletCount++;
//
//        if (bulletCount%2==0) {
//            enemyBulletController = creatEnemyBulletController(getModel().getMidX() - enemyBulletController.WIDTH / 2 + 10,
//                    getModel().getBottom());
//        }else {
//            enemyBulletController = creatEnemyBulletController(getModel().getMidX() - enemyBulletController.WIDTH / 2 + 10,
//                    getModel().getBottom(), BulletMoveType.FOLLOW);
//        }
//
//        enemyBulletControllerVector.add(enemyBulletController);
        if (shootBehavior!=null){
            shootBehavior.doShoot(this);
        }
    }

    public void run() {
        if (moveBehavior!=null){
            this.moveBehavior.doMove(this);
        }
        timeCounter++;
        if (timeCounter > 30) {
            shoot();
            timeCounter = 0;
        }

        //làm sao để viết lại class timecount
//        for (int i = 0; i < enemyBulletControllerVector.size(); i++) {
//            enemyBulletControllerVector.get(i).run();
//            if (enemyBulletControllerVector.get(i).getModel().getY() > 600) {
//                enemyBulletControllerVector.remove(i);
//            }
//        }
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletController) {
            Utils.playSound("resources/explosionEnemy.wav",false);
            System.out.println("HuHuHu");
            this.model.setAlive(false);
        }
    }

    public void destroy(){
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(),this.getModel().getY(),50,50),
                new Animation(Utils.loadSheet("resources/explosion.png",32,32,1,6))

        );
        ControllerManager.explosion.add(explosionController);
    }
}
