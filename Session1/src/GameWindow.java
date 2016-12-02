import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hieu It on 11/30/2016.
 */
public class GameWindow extends Frame {

    Image background,player1, planeD1, player2;
    private int player1X = 450;
    private int player1Y = 400;
    private int planeD1x = 300, player2X = 300;
    private int planeD1y = 100, player2Y = 400;

    public GameWindow() {

        setVisible(true);
        setSize(800, 600);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened !");
                System.exit(0);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing !");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed !");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        try {
            background = ImageIO.read(new File("resources/background.png"));
            player1 = ImageIO.read(new File("resources/plane3.png"));
            planeD1 = ImageIO.read(new File("resources/plane1.png"));
            player2 = ImageIO.read(new File("resources/plane2.png"));
        } catch (IOException e) {
            System.out.println("Load Failed !");
            e.printStackTrace();
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped !");
                switch (e.getKeyChar()) {
                    case 'w':
                        player2Y -= 5;
                        repaint();
                        break;
                    case 's':
                        player2Y += 5;
                        repaint();
                        break;
                    case 'd':
                        player2X += 5;
                        repaint();
                        break;
                    case 'a':
                        player2X -= 5;
                        repaint();
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed !");
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player1Y -= 5;
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        player1Y += 5;
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player1X += 5;
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        player1X -= 5;
                        repaint();
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased !");
            }
        });
        repaint();//gọi hàm paint(khi thay đổi mơi đc goi)
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, 800, 600, null);
        g.drawImage(player1, player1X, player1Y, 70, 50, null);
        g.drawImage(planeD1, planeD1x, planeD1y, 70, 50, null);
        g.drawImage(player2, player2X, player2Y, 70, 50, null);
    }
}
