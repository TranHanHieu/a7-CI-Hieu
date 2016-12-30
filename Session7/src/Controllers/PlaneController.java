package Controllers;

import Controllers.enemies.EnemyBulletController;
import Controllers.enemies.EnemyPlaneController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Models.Model;
import Utils.Utils;
import Views.Animation;
import Views.SingleView;
import Views.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;
    public KeySetting keySetting;
    private ControllerManager bulletManager;
    private int life = 3;
    private static int timeCount = 0;
    public static boolean gameOn = true;


    public static final PlaneController instance = creatPlane(350, 400);

    private PlaneController(Model model, View view) {
        super(model, view);
        bulletManager = new ControllerManager();
        BodyManager.instance.register(this);
    }


    public void keyPressed(KeyEvent e) {


        if (keySetting != null) {
            int keyCode = e.getKeyCode();
            if (keyCode == keySetting.getKeyUp()) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.getKeyDown()) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.getKeyLeft()) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.getKeyRight()) {
                model.move(SPEED, 0);
            } else if (keyCode == keySetting.getKeySpace()) {
                shoot();
            }
        }
    }

    private void shoot() {
        Utils.playSound("resources/shoot.wav", false);
        BulletController bulletController = BulletController.creatBullet(this.model.getMidX() - BulletController.WIDTH / 2,
                this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    //Design pattern factory.
    private static PlaneController creatPlane(int x, int y) {
        return new PlaneController(
                new Model(x, y, 70, 50),
                new SingleView(loadImage("resources/plane3.png")));

    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyBulletController) {
            this.life -= 1;
            Utils.playSound("resources/explosionPlane.wav", false);
            this.model.setAlive(false);
            destroy();
        }
    }

    @Override
    public void run() {
        if (!model.isAlive()) {

            timeCount++;
            if (timeCount > 100) {
                this.model.setAlive(true);
                BodyManager.instance.register(this);
                timeCount = 0;
            }
        }
        super.run();
        bulletManager.run();
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage image1 = Utils.loadImage("resources/life1.png");
        BufferedImage image2 = Utils.loadImage("resources/life1.png");
        BufferedImage image3 = Utils.loadImage("resources/life1.png");
        if (life == 3) {
            g.drawImage(image1, 30, 30, 50, 50, null);
            g.drawImage(image2, 70, 30, 50, 50, null);
            g.drawImage(image3, 110, 30, 50, 50, null);
        }
        if (life ==2) {
            g.drawImage(image1, 30, 30, 50, 50, null);
            g.drawImage(image2, 70, 30, 50, 50, null);
        }
        if (life ==1) {
            g.drawImage(image1, 30, 30, 50, 50, null);
        }
        if (life <1) {
            g.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 36));
            g.setColor(Color.blue);
            g.drawString("Game Over", 300, 300);
            gameOn = false;
        }
        if (model.isAlive()) {
            super.draw(g);
        }
        bulletManager.draw(g);
    }

    public void destroy() {
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(), this.getModel().getY(), 50, 50),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))

        );
        ControllerManager.explosion.add(explosionController);
    }
}
