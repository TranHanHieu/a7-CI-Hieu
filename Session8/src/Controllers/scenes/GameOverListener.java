package Controllers.scenes;

import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Hieu It on 12/30/2016.
 */
public class GameOverListener extends GameScenes {
    private Image backgroundOver;

    public GameOverListener() {
        backgroundOver = Utils.loadImage("resources/gameOver.png");
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundOver,0,0,800,600,null);
    }

    @Override
    public void rụn() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.sceneListener.back();
        }
    }
}
