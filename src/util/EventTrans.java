package util;

public class EventTrans {

    private String eventName;
    private Object data;

    public EventTrans(String eventName, Object data) {
        this.eventName = eventName;
        this.data = data;
    }

    public EventTrans(String eventName) {
        this.eventName = eventName;
    }

    public Object getData() {
        return data;
    }

    public String getEventName() {
        return eventName;
    }
}
