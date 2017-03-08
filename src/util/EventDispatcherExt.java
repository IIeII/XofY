package util;

import view.EventListenerEx;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcherExt {

    private List<EventVO> listeners = new ArrayList<EventVO>();

    public void addListener(String eventName, EventListenerEx listener) {
        listeners.add(new EventVO(eventName, listener));
    }

    protected void dispatchEvent(EventTrans event) throws Throwable {
        for(EventVO eventVO : listeners){
            if (eventVO.eventName.equals(event.getEventName())){
                eventVO.listener.eventHandler(event);
            }
        }
    }
}

class EventVO {
    public String eventName;
    public EventListenerEx listener;

    public EventVO(String eventName, EventListenerEx listener) {
        this.eventName = eventName;
        this.listener = listener;
    }
}
