package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class MonthActivity extends AppCompatActivity {


    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        setTitle("Calendar");

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                month++;
                String date = month + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
                Intent intent = new Intent(MonthActivity.this, DayEvents.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });
    }
}
