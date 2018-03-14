package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    static final String KEY_ACTIVITY = "teamproject.savannahpyle.calendarapp";

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
            Intent intent = new Intent(this, MonthActivity.class);
            startActivity(intent);
        }
    }

    // TODO: Add stuff
    void loadGraphic(View v) {

    }

    void loadNav(View v) {

    }

}
