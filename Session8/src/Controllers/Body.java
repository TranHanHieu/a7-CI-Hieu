package Controllers;

import Models.Model;

/**
 * Created by Hieu It on 12/14/2016.
 */
public interface Body {//thuần ảo thì chuyển sang interface
    Model getModel();

    void onContact(Body other);
}
