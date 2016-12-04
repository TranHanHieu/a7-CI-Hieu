package Views;

import Models.BulletModel;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Hieu It on 12/4/2016.
 */
public class BulletView {
    private Image image;

    public BulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, BulletModel bulletModel) {
        g.drawImage(image, bulletModel.getX(), bulletModel.getY(), 13, 33, null);
    }
}
