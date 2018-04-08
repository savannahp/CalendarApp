package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private static final int RC_SIGN_IN = 123; // For FirebaseAuth

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        if (user != null) {
            ListModel model = ListModel.getInstance();
        }



        // Login with FirebaseAuth if there is no current user
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "Starting OnActivityResult");
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Log.i(TAG, "User Successfully signed in");
                // To initialize the list model and get the data immediately
                user = FirebaseAuth.getInstance().getCurrentUser();
                ListModel model = ListModel.getInstance();

            } else {
                Log.i(TAG, "User unable to sign in");
                // Sign in failed, check response for error code TODO
            }
        }
    }

    // TODO: Add stuff
    void loadNav(View v) {

    }

    /**
     * Create intent for and go to the List Main Activity
     *
     * @param view the view that was clicked to call this method
     */
    public void goToLists(View view) {
        Intent intent = new Intent(this, ListMainActivity.class);
        startActivity(intent);
    }

    /**
     * Create intent for an go to the calendar Month Activity
     *
     * @param view the view that called this mehtod
     */
    public void goToCalendar(View view) {
        Intent intent = new Intent(this, MonthActivity.class);
        startActivity(intent);
    }
}
