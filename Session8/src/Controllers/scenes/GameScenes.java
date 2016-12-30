package Controllers.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Hieu It on 12/28/2016.
 */
public abstract class GameScenes {
    protected SceneListener sceneListener;

    public void setSceneListener(SceneListener sceneListener){
        this.sceneListener = sceneListener;
    }
    public abstract void update(Graphics g);
    public abstract void rụn();
    public abstract void keyPressed(KeyEvent e);
}
