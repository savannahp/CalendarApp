package teamproject.savannahpyle.calendarapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by savannahpyle on 4/7/18.
 *
 * The calendar model holds a list of data corresponding to user created
 * calendar events. It connects to firebase database to pull and push
 * data.
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
     * @return The singleton instance of the Calendar Model
     */
    public static CalendarModel getInstance() {
        return ourInstance;
    }

    /**
     * Private constructor. Just initializes the events map
     */
    private CalendarModel() {
        events = new ArrayList<>();
    }

    /**
     * Clears the contents of our events list
     */
    public void reset() {
        events.clear();
    }

    /**
     * Creates a one time listener to the database to pull data
     * to the calendar model.
     */
    public void pullData() {
        // Listener for changes in the database
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Preparing to update the Calendar");

                Object o = dataSnapshot.child(TAG).getValue();

                if (o == null)
                    return;

                // We don't know what type object will be
                List <Map<String, Object>> list = new ArrayList<>();

                if (o.getClass().isInstance(list))
                    list = (List<Map<String, Object>>) o;

                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = list.get(i);

                    // Get name of the list from the map
                    String date;
                    if (map.get("date") == null)
                        ; // Purposely empty because
                        // We don't want to add a ToDoList
                        // with a null name.
                    else {// Otherwise, we translate the map data to create an event list
                        date = (String) map.get("date");

                        // Get List of bools from the map
                        List<Object> event = new ArrayList<>();
                        if (map.get("eventList") != null) {
                            event = (List<Object>) map.get("eventList");

                            EventList eventList = new EventList(date);

                            for (int j = 0; j < event.size(); j++) {
                                Map<String, Object> eventMap = (Map<String, Object>) event.get(j);

                                String end;
                                String start;
                                String eventName;

                                if (eventMap.get("end") != null) {
                                    end = (String) eventMap.get("end");
                                }
                                else
                                    end = "";
                                if (eventMap.get("start") != null) {
                                    start = (String) eventMap.get("start");
                                }
                                else
                                    start = "";
                                if (eventMap.get("eventName") != null) {
                                    eventName = (String) eventMap.get("eventName");
                                }
                                else
                                    eventName = "";

                                eventList.add(new Event(eventName, start, end));
                            }

                            events.add(eventList);
                        }
                    }
                }

                Log.d(TAG, "Calendar has been updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        };

        // Add the database listeners (I hope this works here *fingers crossed*)
        databaseRef.addListenerForSingleValueEvent(postListener);
    }

    /**
     * Update the values stored in the database
     */
    public void update() {
        Log.d(TAG, "Updating database");
        databaseRef.child(TAG).setValue(events);
        Log.d(TAG, "Database update complete");
    }

    /**
     * Adds an event to the event list
     *
     * @param date String for the date in "MM/DD/YYYY" format, used to find the EventList to add to
     * @param name Title of the event
     * @param start String representing start time of the event
     * @param end String representing end time of the event
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

    /**
     * Returns the list of events for specified date.
     *
     * @param date Date in "MM/DD/YYYY" form
     * @return the list of events on the specified date or null if no events for that date.
     */
    public EventList getEventList(String date) {
        for (EventList e : events) {
            if (e.getDate().equals(date)) {
                return e;
            }
        }
        return null;
    }
}
