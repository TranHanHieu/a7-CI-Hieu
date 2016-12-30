package Controllers.enemies;

import Controllers.Body;
import Controllers.BulletController;
import Controllers.Controller;
import Controllers.ExplosionController;
import Controllers.gifts.BombController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Controllers.notifications.EventSubcriber;
import Controllers.notifications.EventType;
import Controllers.notifications.NotificationCenter;
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

public class EnemyPlaneController extends Controller implements Body,EventSubcriber {

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
        NotificationCenter.instance.register(this);
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
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletController) {
            System.out.println("HuHuHu");
            this.model.setAlive(false);
            //create bomb
            BombController bombController = BombController.create(model.getX(),model.getY());
            //add to ControllerManager
            ControllerManager.instance.add(bombController);
        }
    }

    public void destroy() {
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(), this.getModel().getY(), 50, 50),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))

        );
        ControllerManager.explosion.add(explosionController);
        this.model.setAlive(false);
    }

    @Override
    public boolean onEvent(EventType eventType, Object params) {
        switch (eventType){
            case BOMB_EXPLOSION:
                this.destroy();
                return false;

        }
        return true;
    }
}
