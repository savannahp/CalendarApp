package teamproject.savannahpyle.calendarapp;

import java.util.GregorianCalendar;

/**
 * Created by savannahpyle on 4/7/18.
 */

public class Event {

    private String EventName;
    private GregorianCalendar start;
    private GregorianCalendar end;

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public void setStart(GregorianCalendar start) {
        this.start = start;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }
}
