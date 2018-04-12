package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

/**
 * Home Activity
 *
 * This is the starting activity for the app. It invokes the Firebase UI Auth to
 * securely sign a user in. It has the access buttons to navigate to different parts of the app
 * and to sign the user out.
 */
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private static final int RC_SIGN_IN = 123; // For FirebaseAuth

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Overrides the onCreate for this activity. Insures that
     * the data models have been initialized, or it invokes the
     * sign in method to get the user first.
     *
     * @param savedInstanceState app's saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        if (user != null) {
            ListModel model = ListModel.getInstance();
            CalendarModel calendarModel = CalendarModel.getInstance();
        }
        else
            signIn();
    }

    /**
     * This is for responding to the firebase sign in
     * activity. Gets firebase user if the authentication
     * was successful.
     *
     * @param requestCode What was requested
     * @param resultCode The resulting code
     * @param data data from the resulting activity
     */
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
                ListModel.getInstance().pullData();
                CalendarModel.getInstance().pullData();

            } else {
                Log.i(TAG, "User unable to sign in");
                Toast.makeText(this, "Error: Try logging in again", Toast.LENGTH_SHORT).show();
            }
        }
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

    /**
     * Function to call Firebase UI Auth to sign the user in
     */
    private void signIn() {
        // Start sign in activity again
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

    /**
     * Sign the user out
     * @param view a view
     */
    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this);

        ListModel.getInstance().reset();
        CalendarModel.getInstance().reset();

        signIn();
    }
}
