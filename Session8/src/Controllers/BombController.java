package Controllers;

import Controllers.enemies.EnemyPlaneController;
import Controllers.managers.BodyManager;
import Controllers.managers.ControllerManager;
import Models.Model;
import Utils.Utils;
import Views.SingleView;
import Views.View;

import java.awt.*;

/**
 * Created by Hieu It on 12/26/2016.
 */
public class BombController extends Controller implements Body {
    private static final int WITDH = 30;
    private static final int HEIGHT = 30;
    public static boolean isAliveBomb = true;


    public BombController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public static BombController creatBomb(int x,int y){
        return new BombController(new Model(x,y,WITDH,HEIGHT),new SingleView(Utils.loadImage("resources/bomb.png")));
    }

    @Override
    public void run() {
        super.run();
        this.model.move(0,2);
    }

    @Override
    public void draw(Graphics g) {
        if (model.isAlive()) {
            super.draw(g);
        }
    }

    @Override
    public void onContact(Body other) {
        System.out.println("hhhhh");
        if (other instanceof PlaneController){
            System.out.println("kkkkkkk");
            this.getModel().setAlive(false);
            isAliveBomb =false;
        }
    }
}
