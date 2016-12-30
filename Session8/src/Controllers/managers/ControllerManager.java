package Controllers.managers;

import Controllers.BaseController;
import Controllers.Controller;
import Controllers.GameSetting;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Hieu It on 12/10/2016.
 */
public class ControllerManager implements BaseController {
    protected Vector<Controller> controllers;

    public static  ControllerManager explosion = new ControllerManager();

    public static  ControllerManager enemyBullet = new ControllerManager();
    public static  ControllerManager instance = new ControllerManager();

    public Vector<Controller> getControllers() {
        return controllers;
    }

    public ControllerManager() {
        controllers= new Vector<>();
    }

    public void draw(Graphics g){
        for (Controller controller : controllers) {
            controller.draw(g);
        }
    }

    public void run(){

        Iterator<Controller> iterator = this.controllers.iterator();
        while (iterator.hasNext()){
            Controller controller = iterator.next();
            if (!controller.getModel().isAlive()|| !GameSetting.instance.isInScreen(controller)){
                iterator.remove();
                System.out.println("isAlive" + iterator);
            }
        }
        for (Controller controller : controllers) {
            controller.run();
        }
    }

    public void reset(){
        explosion = new ControllerManager();
        enemyBullet = new ControllerManager();
        instance = new ControllerManager();
    }

    public void add(Controller controller){
        this.controllers.add(controller);
    }

    public void remove(Controller controller){
        this.controllers.remove(controller);
    }
}
