import Controllers.*;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Controllers.managers.EnemyControllerManager;
import Controllers.scenes.GameScenes;
import Controllers.scenes.MenuScene;
import Controllers.scenes.PlayGameScenes;
import Controllers.scenes.SceneListener;
import Views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class GameWindow extends Frame implements Runnable, SceneListener {

    BufferedImage backBuffered;

    GameSetting gameSetting;
    GameScenes currentScene;

    Stack<GameScenes> gameScenesStack;
    Random random;

    public GameWindow() {

        gameScenesStack = new Stack<>();

        this.replaceScene(new MenuScene(), false);

        configSettings();

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
                currentScene.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased !");
            }
        });

        setVisible(true);
        setSize(gameSetting.getWitdh(), gameSetting.getHeight());

        backBuffered = new BufferedImage(gameSetting.getWitdh(), gameSetting.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

    }

    public void replaceScene(GameScenes newScene, boolean addToBackStack) {
        if (addToBackStack && currentScene != null) {
            gameScenesStack.push(currentScene);
        }
        currentScene = newScene;
        currentScene.setSceneListener(this);
    }

    public void back() {
        if (!gameScenesStack.isEmpty()) {
            currentScene = gameScenesStack.pop();
        }
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
        currentScene.update(backBufferGraphic);
        g.drawImage(backBuffered, 0, 0, gameSetting.getWitdh(), gameSetting.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(17);
                currentScene.rụn();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
