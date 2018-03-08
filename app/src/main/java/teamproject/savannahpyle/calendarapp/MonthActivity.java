package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MonthActivity extends AppCompatActivity {

//    static final String KEY_ACTIVITY = "teamproject.savannahpyle.calendarapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_main);
    }

    @Override
    public void onPause() {
        super.onPause();

        Context context = new ContextWrapper(getApplicationContext());

        // Create editor for shared preferences and add the scripture parts using intent
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(HomeActivity.KEY_ACTIVITY, "MonthActivity");
        edit.apply();

//        SharedPreferences.Editor editor = getSharedPreferences(KEY_ACTIVITY, MODE_PRIVATE).edit();
//
//        editor.putString("mostRecentActivity", "MonthActivity");
//        editor.apply();
    }
}
