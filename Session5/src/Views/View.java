package Views;

import Models.Model;

import java.awt.*;

/**
 * Created by Hieu It on 12/7/2016.
 */
public class View {

    private Image image;

    public View(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, Model model) {
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
