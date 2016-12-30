package Controllers;

import Models.Model;

/**
 * Created by Hieu It on 12/25/2016.
 */
public class GameSetting {
    private int witdh;
    private int height;

    public static final GameSetting instance = new GameSetting(800,600);

    private GameSetting(int witdh, int height) {
        this.witdh = witdh;
        this.height = height;
    }

    public int getWitdh() {
        return witdh;
    }

    public void setWitdh(int witdh) {
        this.witdh = witdh;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isInScreen( Model model){
        return model.getY()<this.height;
    }

    public boolean isInScreen( Controller controller){
        return controller.getModel().getY()<this.height;
    }
}
