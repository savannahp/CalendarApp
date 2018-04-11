package teamproject.savannahpyle.calendarapp;


import android.icu.util.Calendar;

/**
 * Created by savannahpyle on 4/7/18.
 */

public class Event {

    public String eventName;
    public String start;
    public String end;

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public Event(String eventName, String start, String end) {
        this.eventName = eventName;
        this.start = start;
        this.end = end;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
