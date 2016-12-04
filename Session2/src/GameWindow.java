import Controllers.BulletController;
import Controllers.KeySetting;
import Controllers.PlaneController;
import Models.BulletModel;
import Models.PlaneModel;
import Views.BulletView;
import Views.PlaneView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class GameWindow extends Frame implements Runnable {

    Image background;
    BufferedImage backBuffered;

    PlaneController planeController;
    Vector<BulletController> bulletVector;

    KeySetting keySetting;

    public GameWindow() {

        bulletVector = new Vector<>();

        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        planeController = new PlaneController(new PlaneModel(350, 400),
                new PlaneView(loadImage("resources/plane3.png")), keySetting);

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
                    BulletController bulletController = new BulletController(
                            new BulletModel(planeController.getPlaneModel().getX() + 29, planeController.getPlaneModel().getY() - 30),
                            new BulletView(loadImage("resources/bullet.png")));
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

    public Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphic = backBuffered.getGraphics();

        backBufferGraphic.drawImage(background, 0, 0, 800, 600, null);
        planeController.draw(backBufferGraphic);

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
                for (int i = 0; i < bulletVector.size(); i++) {
                    bulletVector.get(i).getBulletModel().move(0, -5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
