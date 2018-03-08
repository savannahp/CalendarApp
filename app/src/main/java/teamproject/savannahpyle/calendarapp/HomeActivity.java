package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    static final String KEY_ACTIVITY = "teamproject.savannahpyle.calendarapp";

    private User user;

    // TODO: Add things to this later
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = new ContextWrapper(getApplicationContext());

        // Load the preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String activity = preferences.getString(KEY_ACTIVITY, "No saved state");

        if (activity.equals("MonthActivity")) {
            // Create intent and put the scripture information in it.
            Intent intent = new Intent(this, MonthActivity.class);
            startActivity(intent);
        }


//        SharedPreferences preferences = getSharedPreferences(KEY_ACTIVITY, MODE_PRIVATE);
//        String mostRecentActivity = preferences.getString("mostRecentActivity", null);
    }

    // TODO: Add stuff
    void loadGraphic(View v) {

    }

    void loadNav(View v) {

    }

}
