package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

import java.util.GregorianCalendar;

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

        // Add task to the list based on whether there is a due date
        Switch s = findViewById(R.id.dueDateSwitch);
        if (s.isChecked()) {
            DatePicker datePicker = findViewById(R.id.datePicker);
            Integer month = datePicker.getMonth();
            Integer day = datePicker.getDayOfMonth();
            Integer year = datePicker.getYear();

            date = month + "/" + day + "/" + year;

            Log.d(TAG, "date: " + date);

            model.addTask(listName, taskName);
        } else {
            model.addTask(listName, taskName);
        }

        // Return to the single list activity
        Log.i(TAG, "Creating intent to return to the single list activity");
        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);
        Log.i(TAG, "Intent created with extra message (list name).");
        Log.i(TAG, "Starting single list activity");
        startActivity(intent);
    }

    public void enableDueDate(View view) {

        Log.d(TAG, "Getting the Switch and DatePicker");
        // Get the switch and the date picker
        Switch s = findViewById(R.id.dueDateSwitch);
        DatePicker picker = findViewById(R.id.datePicker);

        Log.d(TAG, "Switch = " + s);
        Log.d(TAG, "Picker = " + picker);


        // Set the date picker visibility according to the switch
        if (s.isChecked()) {
            Log.i(TAG, "DatePicker: Visible");
            picker.setVisibility(View.VISIBLE);
        } else {
            Log.i(TAG, "DatePicker: Invisible");
            picker.setVisibility(View.INVISIBLE);
        }
    }

    public void cancel(View view) {
        Log.i(TAG, "Creating intent for SingleListActivity");
        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);
        Log.i(TAG, "Intent created. Starting Activity with intent");
        startActivity(intent);
    }
}
