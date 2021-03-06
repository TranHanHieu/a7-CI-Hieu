import Controllers.*;
import Controllers.managers.BodyManager;
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

    Random random;

    public GameWindow() {

        random = new Random();

        bulletVector = new Vector<>();

        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        planeController = PlaneController.creatPlane(350, 400);
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
                if (planeController != null) {
                    planeController.keyPressed(e);

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        int bulletX = planeController.getModel().getX() + 30;
                        int bulletY = planeController.getModel().getY() - 30;
                        BulletController bulletController = BulletController.creatBullet(bulletX, bulletY);
                        bulletVector.add(bulletController);
                    }
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
        if (planeController != null) {
            planeController.draw(backBufferGraphic);

        }

        enemyControllerManager.draw(backBufferGraphic);

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
                if (planeController != null) {
                    if (!planeController.getModel().isAlive()) {
                        planeController = null;
                    }
                }
                for (int i = 0; i < bulletVector.size(); i++) {
                    if (!bulletVector.get(i).getModel().isAlive()) {
                        bulletVector.remove(i);
                    } else {
                        bulletVector.get(i).run();
                    }
                }
                BodyManager.instance.checkContact();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
