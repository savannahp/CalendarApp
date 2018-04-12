package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";
    private String listName; // Name of the list we are adding a task to

    /**
     * Set up the layout and get the name of the list we add to
     *
     * @param savedInstanceState App's saved data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        // Use intent to get the list name to add task to
        Intent intent = getIntent();
        listName = intent.getStringExtra(Extra.TASK);

        // Set the activity title
        setTitle("Add Task to " + listName);
    }

    /**
     * Updates the list model with new task based on the data
     * from the activity's form.
     *
     * @param view the button that was clicked
     */
    public void addTask(View view) {
        // Model of the list data
        ListModel model = ListModel.getInstance();

        // String for the date in "mm/dd/yyyy" format
        String date;

        // Get the name of the task
        EditText text = (EditText) findViewById(R.id.taskInput);
        String taskName = text.getText().toString();

        // Add list to model
        model.addTask(listName, taskName);


        // Return to the single list activity
        Log.i(TAG, "Creating intent to return to the single list activity");
        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);
        Log.i(TAG, "Intent created with extra message (list name).");
        Log.i(TAG, "Starting single list activity");
        startActivity(intent);
    }

    /**
     * Return to single list view and do not add task to list
     * @param view cancel button
     */
    public void cancel(View view) {
        Log.i(TAG, "Creating intent for SingleListActivity");
        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);
        Log.i(TAG, "Intent created. Starting Activity with intent");
        startActivity(intent);
    }
}
