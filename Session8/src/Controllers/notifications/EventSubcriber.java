package Controllers.notifications;

/**
 * Created by Hieu It on 12/28/2016.
 */
public interface EventSubcriber {
    boolean onEvent(EventType eventType,Object params);
}
