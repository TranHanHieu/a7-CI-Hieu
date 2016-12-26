package Views;

import Models.Model;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Hieu It on 12/25/2016.
 */
public class SingleView implements View {
    private Image image;

    public SingleView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, Model model) {
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
