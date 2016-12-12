import Controllers.BulletController;
import Controllers.EnemyPlaneController;
import Controllers.KeySetting;
import Controllers.PlaneController;
import Controllers.managers.EnemyControllerManager;
import Views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class GameWindow extends Frame implements Runnable {

    Image background;
    BufferedImage backBuffered;

    PlaneController planeController;

    EnemyControllerManager enemyControllerManager;
    Vector<BulletController> bulletVector;

    KeySetting keySetting;
    Animation animation;

    Random random;

    public GameWindow() {

        random = new Random();

        bulletVector = new Vector<>();

        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        planeController = PlaneController.creatPlane(350, 500);
        planeController.setKeySetting(keySetting);

        enemyControllerManager = new EnemyControllerManager();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped !");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed !");
                planeController.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    int bulletX = planeController.getModel().getX() + 30;
                    int bulletY = planeController.getModel().getY() - 30;
                    BulletController bulletController = BulletController.creatBullet(bulletX, bulletY);
                    bulletVector.add(bulletController);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased !");
            }
        });

        setVisible(true);
        setSize(800, 600);

        background = loadImage("resources/background.png");
        backBuffered = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);

    }


    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphic = backBuffered.getGraphics();

        backBufferGraphic.drawImage(background, 0, 0, 800, 600, null);
        planeController.draw(backBufferGraphic);
        enemyControllerManager.draw(backBufferGraphic);
//        if (animation != null) {
//            animation.draw(backBufferGraphic);
//        }

        for (int i = 0; i < bulletVector.size(); i++) {
            bulletVector.get(i).draw(backBufferGraphic);
        }

        g.drawImage(backBuffered, 0, 0, 800, 600, null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);
                enemyControllerManager.run();

                for (int i = 0; i < bulletVector.size(); i++) {
                    bulletVector.get(i).run();
                }
//                    if (enemyPlaneController != null) {
//                        //bắt sự kiện bắn trúng enemy.
//                        for (int j = 0; j < enemyControllerManager.getControllers().size(); j++) {
//                            if (bulletVector.get(i).getModel().getX() >= enemyControllerManager.getControllers().get(i).getModel().getX() - 5 &&
//                                    bulletVector.get(i).getModel().getX() <= enemyControllerManager.getControllers().get(i).getModel().getX() + 65 &&
//                                    bulletVector.get(i).getModel().getY() < enemyControllerManager.getControllers().get(i).getModel().getY() + 40) {
//
//                                animation = new Animation(enemyControllerManager.getControllers().get(i).getModel(),
//                                        enemyControllerManager.getControllers().get(i).getView(),
//                                        "resources/explosion1.png,resources/explosion2.png,resources/explosion3.png,resources/explosion4.png,resources/explosion5.png,resources/explosion6.png");
//
//                                enemyPlaneX = random.nextInt(500) + 100;
//                                enemyControllerManager.getControllers().add(EnemyPlaneController.creatEnemyPlane(enemyPlaneX, -20));
//                                bulletVector.remove(i);
//                            } else {
//
//                                if (bulletVector.get(i).getModel().getY() < 0) {
//                                    bulletVector.remove(i);
//                                }
//                                if (enemyControllerManager.getControllers().get(i).getModel().getY() > 600) {
//                                    enemyPlaneX = random.nextInt(500) + 100;
//                                    enemyControllerManager.getControllers().add(EnemyPlaneController.creatEnemyPlane(enemyPlaneX, -10));
//                                }
//                            }
//                        }
//                    }
                //bắt sự kiện enemy bắn plane.
//                for (int i = 0; i < enemyPlaneController.getEnemyBulletControllerVector().size(); i++) {
//                    if (enemyPlaneController.getEnemyBulletControllerVector().get(i).getModel().getX() >= planeController.getModel().getX() - 5 &&
//                            enemyPlaneController.getEnemyBulletControllerVector().get(i).getModel().getX() <= planeController.getModel().getX() + 65 &&
//                            enemyPlaneController.getEnemyBulletControllerVector().get(i).getModel().getY() < planeController.getModel().getY() - 40) {
//
//                        animation = new Animation(planeController.getModel(),
//                                planeController.getView(),
//                                "resources/explosion1.png,resources/explosion2.png,resources/explosion3.png,resources/explosion4.png,resources/explosion5.png,resources/explosion6.png");
//                        planeController=PlaneController.creatPlane(-100,-100);
//                            enemyPlaneX = random.nextInt(500) + 100;
//                            enemyPlaneController = EnemyPlaneController.creatEnemyPlane(enemyPlaneX, -20);
//                        enemyPlaneController.getEnemyBulletControllerVector().remove(i);
//
//                    }
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
