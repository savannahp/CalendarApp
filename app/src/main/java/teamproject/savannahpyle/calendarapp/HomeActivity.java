package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    static final String KEY_ACTIVITY = "teamproject.savannahpyle.calendarapp";

    private static final String TAG = "HomeActivity";

    private static final int RC_SIGN_IN = 123; // For FirebaseAuth
    // TODO: Add things to this later
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());

        //Logs
        Log.i(TAG, "Starting Login Activity");
        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

//        Context context = new ContextWrapper(getApplicationContext());
//
//        // Load the preferences
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String activity = preferences.getString(KEY_ACTIVITY, "No saved state");
//
//        if (activity.equals("MonthActivity")) {
//            Intent intent = new Intent(this, MonthActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "Starting OnActivityResult");
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Log.i(TAG, "User Successfully signed in");

            } else {
                Log.i(TAG, "User unable to sign in");
                // Sign in failed, check response for error code TODO
            }
        }
    }

    // TODO: Add stuff
    void loadGraphic(View v) {

    }

    void loadNav(View v) {

    }

}
