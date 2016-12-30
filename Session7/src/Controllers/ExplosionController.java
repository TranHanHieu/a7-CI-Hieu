package Controllers;

import Models.Model;
import Views.Animation;
import Views.View;

import java.awt.*;

/**
 * Created by Hieu It on 12/26/2016.
 */
public class ExplosionController extends Controller{

    public ExplosionController(Model model, View view) {
        super(model, view);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Animation animation = (Animation)this.view;
        if (animation.isAnimationReachEnd()){
            model.setAlive(false);
        }
    }
}
