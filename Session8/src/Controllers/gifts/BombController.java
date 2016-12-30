package Controllers.gifts;

import Controllers.Body;
import Controllers.Controller;
import Controllers.PlaneController;
import Controllers.managers.BodyManager;
import Controllers.notifications.EventType;
import Controllers.notifications.NotificationCenter;
import Models.Model;
import Utils.Utils;
import Views.SingleView;
import Views.View;

import java.awt.*;


/**
 * Created by Hieu It on 12/28/2016.
 */
public class BombController extends Controller implements Body{

    public BombController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);

    }

    @Override
    public void run() {
        super.run();
        model.move(0,1);
    }

    public static BombController create (int x, int y){
        return new BombController(
                new Model(x,y,30,30),
                new SingleView(Utils.loadImage("resources/bomb.png"))
        );
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController){
            Utils.playSound("resources/ExplosionEnemy.wav",false);
            NotificationCenter.instance.onEvent(EventType.BOMB_EXPLOSION,model);
            this.destroy();
        }
    }
}
