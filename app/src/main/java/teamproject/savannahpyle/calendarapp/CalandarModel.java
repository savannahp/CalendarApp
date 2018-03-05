package teamproject.savannahpyle.calendarapp;

import android.app.usage.UsageEvents; // TODO: What da heck is dis???
import android.view.View;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by paulland on 3/3/18.
 */

public class CalandarModel {

    private List<UsageEvents.Event> events;

    private GregorianCalendar calendar;

    boolean addEvent(View view) {
        return true;
    }

    boolean removeEvent(View view, UsageEvents.Event event) {
        return true;
    }
}
