package Controllers.scenes;

import Utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Hieu It on 12/28/2016.
 */
public class MenuScene extends GameScenes {

    private Image backgroundMenu;

    public MenuScene() {
        backgroundMenu = Utils.loadImage("resources/gameMenu.png");
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundMenu,0,0,800,600,null);
    }

    @Override
    public void rụn() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            this.sceneListener.replaceScene(
                    new PlayGameScenes(),
                    true
            );
        }
    }

}
