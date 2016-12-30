package Controllers.scenes;

/**
 * Created by Hieu It on 12/28/2016.
 */
public interface SceneListener {
    void back();
    void replaceScene(GameScenes newScene,boolean addToBackStack);
}
