package Controllers.notifications;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Hieu It on 12/28/2016.
 */
public class NotificationCenter {

    private Vector<EventSubcriber> subcribers;

    public static final NotificationCenter instance = new NotificationCenter();

    private NotificationCenter() {
        subcribers = new Vector<>();
    }

    public void register(EventSubcriber subcriber){
        subcribers.add(subcriber);
    }


    public void onEvent(EventType eventType,Object params){
        Iterator<EventSubcriber> interator = this.subcribers.iterator();
        while (interator.hasNext()){
            EventSubcriber e = interator.next();
            if (!e.onEvent(eventType,params)){
                interator.remove();
            }
        }
//        for (EventSubcriber subcriber : subcribers) {
//            subcriber.onEvent(eventType,params);
//        }
    }
}
