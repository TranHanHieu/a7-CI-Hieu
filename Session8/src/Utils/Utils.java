package Utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hieu It on 12/7/2016.
 */
public class Utils {
    //utilities
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if(repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Vector<BufferedImage> loadSheet(String URL,int witdh,int height,int border,int imgeCount){
        Vector<BufferedImage> imageVector = new Vector<>();
        BufferedImage image = Utils.loadImage(URL);
        for (int i = 0; i < imgeCount; i++) {
            int x =i*witdh+border*(i+1);
            int y = border;
            BufferedImage subImage = image.getSubimage(x,y,witdh,height);
            imageVector.add(subImage);
        }

        return imageVector;
    }
}
