package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddEventActivity extends AppCompatActivity {

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        date = intent.getStringExtra(Extra.EVENT);

    }

    public void addEvent(View view) {

        CalendarModel model = CalendarModel.getInstance();

        EditText eventName = (EditText) findViewById(R.id.eventNameText);
        EditText startTime = (EditText) findViewById(R.id.startTimeText);
        EditText endTime =   (EditText) findViewById(R.id.endTimeText);

        String eventN = eventName.getText().toString();
        String startT = startTime.getText().toString();
        String endT = endTime.getText().toString();

        Log.d("LOSERLOSERLOSERLOSERLOSER", "STAR TIME:" + startT);

    }
}
