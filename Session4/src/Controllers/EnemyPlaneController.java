package Controllers;

import Models.Model;
import Views.View;

import java.awt.*;

import java.util.Vector;

import static Controllers.EnemyBulletController.creatEnemyBulletController;
import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyPlaneController extends Controller {

    private static final int SPEED = 1;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 50;
    private int timeCounter;
    private int time;

    private EnemyType enemyType;

    public EnemyType getEnemyType() {
        return enemyType;
    }

    private Vector<EnemyBulletController> enemyBulletControllerVector;

    private EnemyBulletController enemyBulletController;

    private Vector<Controller> controllerVector;

    public Vector<EnemyBulletController> getEnemyBulletControllerVector() {
        return enemyBulletControllerVector;
    }

    public EnemyPlaneController(Model model, View view, EnemyType enemyType) {
        super(model, view);
        this.enemyType = enemyType;
        enemyBulletControllerVector = new Vector<>();
        controllerVector = new Vector<>();
        timeCounter = 0;
        time = 0;
    }

    public static EnemyPlaneController creatEnemyPlane(int x, int y, EnemyType enemyType) {
        if (enemyType == EnemyType.LEFT) {
            return new EnemyPlaneController(
                    new Model(x, y, WIDTH, HEIGHT),
                    new View(loadImage("resources/enemy-green-2.png")), enemyType);
        } else if (enemyType == EnemyType.DOWN) {
            return new EnemyPlaneController(
                    new Model(x, y, WIDTH, HEIGHT),
                    new View(loadImage("resources/plane1.png")), enemyType);
        } else if (enemyType == EnemyType.ALWAYS) {
            return new EnemyPlaneController(
                    new Model(x, y, WIDTH, HEIGHT),
                    new View(loadImage("resources/enemy-green-1.png")), enemyType);
        }
        return null;
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
    }

    public void run() {
        time++;
        if (enemyType == EnemyType.DOWN) {
            this.model.move(0, SPEED);
        } else if (enemyType == EnemyType.LEFT) {
            this.model.move(-SPEED, SPEED);
        } else if (enemyType == EnemyType.ALWAYS) {
            if (time < 150) {
                this.model.move(2, 2);
            } else {
                if (time < 200) {
                    this.model.move(2, 0);
                } else {
                    if (time < 240) {
                        this.model.move(2, -2);
                    } else {
                        if (time < 290) {
                            this.model.move(0, -2);
                        } else {
                            if (time < 330) {
                                this.model.move(-2, -2);
                            } else {
                                if (time < 380) {
                                    this.model.move(-2, 0);
                                } else {
                                    if (time < 420) {
                                        this.model.move(-2, 2);
                                    } else {
                                        if (time < 470) {
                                            this.model.move(0, 2);
                                        } else {
                                            time = 110;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        timeCounter++;
        if (timeCounter > 100) {
            addBullet();
            //addPlane();
            timeCounter = 0;
        }
        //làm sao để viết lại class timecount
        for (int i = 0; i < enemyBulletControllerVector.size(); i++) {
            enemyBulletControllerVector.get(i).run();
            if (enemyBulletControllerVector.get(i).getModel().getY() > 600) {
                enemyBulletControllerVector.remove(i);
            }
        }
    }
}
