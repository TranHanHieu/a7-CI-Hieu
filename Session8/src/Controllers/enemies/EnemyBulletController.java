package Controllers.enemies;

import Controllers.Body;
import Controllers.Controller;
import Controllers.GameVector;
import Controllers.PlaneController;
import Controllers.managers.BodyManager;
import Models.Model;
import Views.SingleView;
import Views.View;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/8/2016.
 */
public class EnemyBulletController extends Controller implements Body {

    private static final int SPEED=5;
    public static final int WIDTH=32;
    public static final int HEIGHT=32;

    private GameVector moveVector;

    public EnemyBulletController(Model model, View view) {
        this(model,view,new GameVector(0,SPEED));
        BodyManager.instance.register(this);
    }

    public EnemyBulletController(Model model, View view, GameVector gameVector) {
        super(model, view);
        this.moveVector = gameVector;
        BodyManager.instance.register(this);
    }

    public static EnemyBulletController creatEnemyBulletController(int x, int y) {

                return new EnemyBulletController(
                        new Model(x - 11, y - 10, WIDTH, HEIGHT),
                        new SingleView(loadImage("resources/enemy_bullet.png")));
    }

    public static EnemyBulletController creatEnemyBulletController(int x, int y,GameVector gameVector) {

        return new EnemyBulletController(
                new Model(x - 11, y - 10, WIDTH, HEIGHT),
                new SingleView(loadImage("resources/enemy_bullet.png")),
                gameVector);
    }

    @Override
    public void run() {
        model.move(moveVector);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            //System.out.println("aaaaaa");
            this.model.setAlive(false);
            System.out.println(model.isAlive());
        }
    }
}
