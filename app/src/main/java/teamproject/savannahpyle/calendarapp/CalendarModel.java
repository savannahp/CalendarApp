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

    private Map<String, List<Event>> events;


    private static final CalendarModel ourInstance = new CalendarModel();

    public static CalendarModel getInstance() {
        return ourInstance;
    }

    private CalendarModel() {
        events = new HashMap<>();

    }

    public void addEvent(String date, String name, GregorianCalendar start,
                         GregorianCalendar end) {

        Event event = new Event(name,start,end);

        if(events.get(date) == null) {
            List <Event> e = new ArrayList<>();
            e.add(event);
            events.put(date, e);
        }
        else
            events.get(date).add(event);

    }


}
