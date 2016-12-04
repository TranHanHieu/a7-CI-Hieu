package Controllers;

import Models.BulletModel;
import Views.BulletView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class BulletController {
    private BulletModel bulletModel;
    private BulletView bulletView;

    public BulletModel getBulletModel() {
        return bulletModel;
    }

    public BulletView getBulletView() {
        return bulletView;
    }

    public BulletController(BulletModel bulletModel, BulletView bulletView) {
        this.bulletModel = bulletModel;
        this.bulletView = bulletView;
    }

    public void draw(Graphics g){
        bulletView.draw(g,bulletModel);
    }

    public void run(){

    }
}
