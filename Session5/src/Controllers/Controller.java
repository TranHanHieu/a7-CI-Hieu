package Controllers;

import Models.Model;
import Views.Animation;
import Views.View;

import java.awt.*;

/**
 * Created by Hieu It on 12/7/2016.
 */
public class Controller {

    protected Model model;
    protected View view;
    public static Animation animation;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void run() {

    }

    public void draw(Graphics g) {
        view.draw(g, model);
    }

}
