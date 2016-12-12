package Controllers;

import Models.Model;
import Views.View;

import java.awt.*;

import static Utils.Utils.loadImage;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class BulletController extends Controller {

    public BulletController(Model model, View view) {
        super(model, view);
    }

    public void run() {
        this.model.move(0, -5);
    }

    public static BulletController creatBullet(int x, int y) {
        return new BulletController(
                new Model(x, y, 12, 30),
                new View(loadImage("resources/bullet.png")));

    }
}
