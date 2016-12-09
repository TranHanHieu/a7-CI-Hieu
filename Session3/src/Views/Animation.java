package Views;

import Controllers.Controller;
import Models.Model;
import Utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Vector;

/**
 * Created by Hieu It on 12/9/2016.
 */
public class Animation extends Controller {
    private Vector<Image> imageVector = new Vector<>();
    private int index = 0;
    private int time = 0;

    public Animation(Model model, View view, String path) {
        super(model, view);
        String[] split = path.split(",");
        for (int i = 0; i < split.length; i++) {
            Image image = Utils.loadImage(split[i]);
            imageVector.add(image);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (imageVector != null) {
            Image image = imageVector.get(index);
            g.drawImage(image, this.getModel().getX() + 5, this.getModel().getY() - 5, 60, 60, null);
            time++;
            if (time / 5 == 1) {
                time = 0;
                index++;
                if (index == imageVector.size()) {
                    imageVector = null;
                }
            }
        }
    }
}
