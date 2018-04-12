package teamproject.savannahpyle.calendarapp;

/**
 * Created by savannahpyle on 4/7/18.
 */

public class Event {

    public String eventName;
    public String start;
    public String end;

    /**
     * Constructor for new event
     *
     * @param eventName name of the event
     */
    public Event(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     * @param eventName Name of event
     * @param start Start time in digital-clock form
     * @param end End time in digital-clock form
     */
    public Event(String eventName, String start, String end) {
        this.eventName = eventName;
        this.start = start;
        this.end = end;
    }

    /**
     *
     * @return the name of this event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set the name of this event
     * @param eventName name to set event name to
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     * @return the string start time of this event
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the start time string
     * @param start string to set start time to
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     *
     * @return the string representing the end time of the event
     */
    public String getEnd() {
        return end;
    }

    /**
     * Set the event's end time
     * @param end the string end time to set event end time to
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
