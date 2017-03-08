package util;

public class EventTrans {

    private String eventName;
    private Object data;

    public EventTrans(String eventName, Object data) {
        this.eventName = eventName;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getEventName() {
        return eventName;
    }
}
