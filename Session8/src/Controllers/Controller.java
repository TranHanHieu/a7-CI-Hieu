package Controllers;

import Models.Model;
import Views.View;

import java.awt.*;

/**
 * Created by Hieu It on 12/7/2016.
 */
public class Controller implements BaseController {

    protected Model model;
    protected View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void move(GameVector gameVector){
        this.model.move(gameVector);
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

    public void destroy(){
        this.model.setAlive(false);
    }

    public void draw(Graphics g) {
        view.draw(g, model);
    }

}
