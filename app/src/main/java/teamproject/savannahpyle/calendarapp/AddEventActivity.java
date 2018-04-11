package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import android.icu.util.Calendar;
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
        String event = eventName.getText().toString();
        String start = startTime.getText().toString();
        String end = endTime.getText().toString();

        // Display Toast if there are blank fields in the form
        if (event.equals("")) {
            Toast.makeText(this, "Event name required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (start.equals("")) {
            Toast.makeText(this, "Start time required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (end.equals("")) {
            Toast.makeText(this, "End time required", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton startAM = (RadioButton) findViewById(R.id.startAm);

        if (startAM.isChecked())
            start = start.concat("am");
        else
            start = start.concat("pm");

        RadioButton endAM = (RadioButton) findViewById(R.id.endAm);

        if (endAM.isChecked())
            end = end.concat("am");
        else
            end = end.concat("pm");

        // Get the calendar model and add our event to it
        CalendarModel model = CalendarModel.getInstance();

        model.addEvent(date, event, start, end);

        Intent intent = new Intent(this, SingleDayActivity.class);
        intent.putExtra(Extra.DATE, date);
        intent.putExtra(Extra.YEAR, year);
        intent.putExtra(Extra.MONTH, month);
        intent.putExtra(Extra.DAY, day);
        startActivity(intent);

    }
}
