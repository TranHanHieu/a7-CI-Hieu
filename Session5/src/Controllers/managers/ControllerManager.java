package Controllers.managers;

import Controllers.Controller;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Hieu It on 12/10/2016.
 */
public class ControllerManager {
    protected Vector<Controller> controllers;

    public Vector<Controller> getControllers() {
        return controllers;
    }

    public ControllerManager() {
        controllers = new Vector<>();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).draw(g);
            if (!controllers.get(i).getModel().isAlive()) {
                controllers.get(i).animation.draw(g);
            }
        }
    }

    public void run() {

        Iterator<Controller> iterator = this.controllers.iterator();
        while (iterator.hasNext()) {
            Controller controller = iterator.next();
            if (!controller.getModel().isAlive()) {
                iterator.remove();
            }
        }
        for (Controller controller : controllers) {
            controller.run();
        }
    }

    public void add(Controller controller) {
        this.controllers.add(controller);
    }

    public void remove(Controller controller) {
        this.controllers.remove(controller);
    }
}
