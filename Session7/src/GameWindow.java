import Controllers.*;
import Controllers.managers.BodyManager;
import Controllers.managers.BombControllerManager;
import Controllers.managers.ControllerManager;
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

    Animation animation;
    GameSetting gameSetting;

    Vector<BaseController> controllers;

    Random random;

    public GameWindow() {
        configSettings();
        controllers = new Vector<>();
        controllers.add(new EnemyControllerManager());
        controllers.add(PlaneController.instance);
        controllers.add(BodyManager.instance);
        controllers.add(ControllerManager.enemyBullet);
        controllers.add(ControllerManager.explosion);

        //controllers.add(new BombControllerManager());
        //controllers.add(ControllerManager.bomb);
        controllers.add(BombController.creatBomb(300,0));


        random = new Random();

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
                PlaneController.instance.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased !");
            }
        });

        setVisible(true);
        setSize(gameSetting.getWitdh(), gameSetting.getHeight());

        background = loadImage("resources/background.png");
        backBuffered = new BufferedImage(gameSetting.getWitdh(), gameSetting.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

    }

    private void configSettings() {
        PlaneController.instance.keySetting = (new KeySetting(KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_SPACE));
        gameSetting = GameSetting.instance;
    }


    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphic = backBuffered.getGraphics();

        backBufferGraphic.drawImage(background, 0, 0, gameSetting.getWitdh(), gameSetting.getHeight(), null);
        for (BaseController baseController : controllers) {
            baseController.draw(backBufferGraphic);
        }

        g.drawImage(backBuffered, 0, 0, gameSetting.getWitdh(), gameSetting.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);
                for (BaseController baseController : controllers) {
                    baseController.run();
                }
                if (!PlaneController.gameOn){
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
