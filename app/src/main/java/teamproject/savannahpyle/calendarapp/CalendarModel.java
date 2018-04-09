package teamproject.savannahpyle.calendarapp;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by savannahpyle on 4/7/18.
 */

public class CalendarModel {

    // Key is string in date format "MM/DD/YYYY", value holds list of all events for that day
    private Map<String, List<Event>> events;

    // For singleton purposes
    private static final CalendarModel ourInstance = new CalendarModel();

    /**
     *
     * @return The singleton instance of the Calendar Model
     */
    public static CalendarModel getInstance() {
        return ourInstance;
    }

    /**
     * Private constructor. Just initializes the events map
     */
    private CalendarModel() {
        events = new HashMap<>();
    }

    /**
     *
     * @param date String for the date in "MM/DD/YYYY" format, used as key for events map
     * @param name Title of the event
     * @param start GregorianCalendar representing start of the event
     * @param end GregorianCalendar representing end of the event
     */
    public void addEvent(String date, String name, GregorianCalendar start, GregorianCalendar end) {

        // Create event based on the parameters
        Event event = new Event(name, start, end);

        // Check if there is already an event list at the slot of the map then add event to the list
        if(events.get(date) == null) {
            List <Event> e = new ArrayList<>();
            e.add(event);
            events.put(date, e);
        }
        else
            events.get(date).add(event);

    }

    public List<Event> getEventList(String date) {
        return events.get(date);
    }


}
