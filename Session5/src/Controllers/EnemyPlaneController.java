package Controllers;

import Controllers.managers.BodyManager;
import Models.Model;
import Views.Animation;
import Views.View;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static Controllers.EnemyBulletController.creatEnemyBulletController;
import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyPlaneController extends Controller implements Body {

    private static final int SPEED = 1;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 50;
    private int hp;

    private int timeCounter;

    private Vector<EnemyBulletController> enemyBulletControllerVector;

    private EnemyBulletController enemyBulletController;

    public Vector<EnemyBulletController> getEnemyBulletControllerVector() {
        return enemyBulletControllerVector;
    }

    public EnemyPlaneController(Model model, View view) {
        super(model, view);
        enemyBulletControllerVector = new Vector<>();
        timeCounter = 0;
        hp = 100;
        BodyManager.instance.register(this);
    }

    public static EnemyPlaneController creatEnemyPlane(int x, int y) {
        return new EnemyPlaneController(
                new Model(x, y, WIDTH, HEIGHT),
                new View(loadImage("resources/plane1.png")));
    }

    public void addBullet() {

        enemyBulletController = creatEnemyBulletController(getModel().getMidX() - enemyBulletController.WIDTH / 2 + 10,
                getModel().getBottom());

        enemyBulletControllerVector.add(enemyBulletController);
    }

    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 0; i < enemyBulletControllerVector.size(); i++) {
            enemyBulletControllerVector.get(i).draw(g);

        }
        if (animation != null) {
            animation.draw(g);
        }
    }

    public void run() {
        this.model.move(0, SPEED);
        timeCounter++;
        if (timeCounter > 300) {
            addBullet();
            timeCounter = 0;
        }
        //làm sao để viết lại class timecount
        for (int i = 0; i < enemyBulletControllerVector.size(); i++) {
            enemyBulletControllerVector.get(i).run();
            if (enemyBulletControllerVector.get(i).getModel().getY() > 600||!enemyBulletControllerVector.get(i).getModel().isAlive()) {
                enemyBulletControllerVector.remove(i);
            }
        }
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletController) {
            hp -= 50;
            System.out.println("Hp Enemy : " + hp);
            if (hp == 0) {
                this.getModel().setAlive(false);
                animation = new Animation(model, view, "resources/explosion1.png,resources/explosion2.png,resources/explosion3.png,resources/explosion4.png,resources/explosion5.png,resources/explosion6.png");
            }
        }
    }
}
