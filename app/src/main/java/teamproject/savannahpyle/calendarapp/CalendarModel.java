package teamproject.savannahpyle.calendarapp;

import android.icu.util.Calendar;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by savannahpyle on 4/7/18.
 */
public class CalendarModel {

    // Key is string in date format "MM/DD/YYYY", value holds list of all events for that day
    public List<EventList> events;

    private static final String TAG = "CalendarModel";

    // For accessing the Firebase Database
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(user.getUid());

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
        events = new LinkedList<>();
    }

    public void update() {
        Log.d(TAG, "Updating database");
        databaseRef.child(TAG).setValue(events);
        Log.d(TAG, "Database update complete");
    }

    /**
     *
     * @param date String for the date in "MM/DD/YYYY" format, used as key for events map
     * @param name Title of the event
     * @param start GregorianCalendar representing start of the event
     * @param end GregorianCalendar representing end of the event
     */
    public void addEvent(String date, String name, String start, String end) {

        // Create event based on the parameters
        Event event = new Event(name, start, end);

        Boolean foundEventList = false;

        for (EventList e : events) {
            if (e.getDate().equals(date)) {
                e.add(event);
                foundEventList = true;
                break;
            }
        }

        if (!foundEventList) {
            EventList eventList = new EventList(date);
            eventList.add(event);
            events.add(eventList);
        }

        update();
    }

    public EventList getEventList(String date) {
        for (EventList e : events) {
            if (e.getDate().equals(date)) {
                return e;
            }
        }

        return null;
    }


}
