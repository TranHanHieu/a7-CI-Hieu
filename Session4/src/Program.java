/**
 * Created by Hieu It on 12/4/2016.
 */
public class Program {

    public static void main(String[] args) {
        Thread thread = new Thread(new GameWindow());
        thread.start();
    }
}
