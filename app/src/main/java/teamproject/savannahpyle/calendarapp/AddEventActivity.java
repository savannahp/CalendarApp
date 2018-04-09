package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {

    private String date;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Receive the intent and set our member variables from intent
        Intent intent = getIntent();
        date = intent.getStringExtra(Extra.DATE);
        year = intent.getIntExtra(Extra.YEAR, 0);
        month = intent.getIntExtra(Extra.MONTH, 0);
        day = intent.getIntExtra(Extra.DAY, 0);

    }

    public void addEvent(View view) {

        // Get the various fields for the event creation
        EditText eventName = (EditText) findViewById(R.id.eventNameText);
        EditText startTime = (EditText) findViewById(R.id.startTimeText);
        EditText endTime =   (EditText) findViewById(R.id.endTimeText);

        // Turn them to strings
        String eventN = eventName.getText().toString();
        String startT = startTime.getText().toString();
        String endT = endTime.getText().toString();

        // Display Toast if there are blank fields in the form
        if (eventN.equals("")) {
            Toast.makeText(this, "Event name required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (startT.equals("")) {
            Toast.makeText(this, "Start time required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (endT.equals("")) {
            Toast.makeText(this, "End time required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Call the get time function to parse the time strings
        Map<String, Integer> start = getTime(startT);
        Map<String, Integer> end = getTime(endT);

        // Get the specific data from the maps received from getTime function
        int startHour = start.get("hour");
        int startMinute = start.get("minute");
        int endHour = end.get("hour");
        int endMinute = end.get("minute");

        // Create calendar for the start time of the event
        GregorianCalendar s = new GregorianCalendar(year, month, day, startHour, startMinute);

        // Get the radio buttons for am and pm to set them for the GregorianCalendar
        RadioButton startAM = (RadioButton) findViewById(R.id.startAm);
        RadioButton startPM = (RadioButton) findViewById(R.id.startPm);
        if (startAM.isChecked())
            s.set(Calendar.AM_PM, Calendar.AM);
        else if (startPM.isChecked())
            s.set(Calendar.AM_PM, Calendar.PM);

        // Create calendar for the end time of the event
        GregorianCalendar e = new GregorianCalendar(year, month, day, endHour, endMinute);

        // Get the radio buttons for am and pm to set the for the end GregorianCalendar
        RadioButton endAM = (RadioButton) findViewById(R.id.endAm);
        RadioButton endPM = (RadioButton) findViewById(R.id.endPm);
        if (endAM.isChecked())
            e.set(Calendar.AM_PM, Calendar.AM);
        else if (endPM.isChecked())
            e.set(Calendar.AM_PM, Calendar.PM);

        // Get the calendar model and add our event to it
        CalendarModel model = CalendarModel.getInstance();
        model.addEvent(date, eventN, s, e);

        Intent intent = new Intent(this, SingleDayActivity.class);
        intent.putExtra(Extra.DATE, date);
        intent.putExtra(Extra.YEAR, year);
        intent.putExtra(Extra.MONTH, month);
        intent.putExtra(Extra.DAY, day);
        startActivity(intent);

    }

    private Map<String, Integer> getTime(String time) {

        // Create map to return
        Map<String, Integer> map = new HashMap<>();

        // Variables for use in the parsing loop
        Boolean colonFound = false;
        String hour = "";
        String minute = "";

        // Parse the string, store the hour and minute values, skip the colon
        for (Character c : time.toCharArray()) {
            if (!Character.isDigit(c))
                colonFound = true;
            else if (!colonFound)
                hour = hour.concat(c.toString());
            else
                minute = minute.concat(c.toString());
        }

        // Add the data to our map, then return
        map.put("hour", Integer.parseInt(hour));
        map.put("minute", Integer.parseInt(minute));

        return map;
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, SingleDayActivity.class);
        intent.putExtra(Extra.DATE, date);
        intent.putExtra(Extra.YEAR, year);
        intent.putExtra(Extra.MONTH, month);
        intent.putExtra(Extra.DAY, day);
        startActivity(intent);
    }
}
