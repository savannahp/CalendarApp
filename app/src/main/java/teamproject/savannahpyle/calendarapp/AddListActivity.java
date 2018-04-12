package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AddListActivity extends AppCompatActivity {
    private static final String TAG = "AddListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        setTitle("Create List");
    }

    /**
     * Adds a list based on the data in the activity's form
     *
     * @param view button that was clicked
     */
    public void addList(View view) {

        Log.d(TAG, "Retrieving List Model Instance");
        ListModel model = ListModel.getInstance();

        EditText text = (EditText) findViewById(R.id.listInput);
        String listName = text.getText().toString();

        Switch s = findViewById(R.id.dueDateSwitch);
        DatePicker picker = findViewById(R.id.datePicker);

        String date;
        if (s.isChecked())
            date = (picker.getMonth() + 1) + "/" + picker.getDayOfMonth() + "/" + picker.getYear();
        else
            date = "";

        Boolean addList = model.addList(listName, date);

        if (!addList) {
            Context context = getApplicationContext();
            CharSequence message = "This list already exists. Taking you to " + listName;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }

        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);
        startActivity(intent);
    }

    /**
     * Cancels the adding of a new list
     * @param view cancel button
     */
    public void cancel(View view) {
        Log.i(TAG, "Creating intent for ListMainActivity");
        Intent intent = new Intent(this, ListMainActivity.class);
        Log.i(TAG, "Intent created. Starting Activity with intent");
        startActivity(intent);
    }

    /**
     * Enables a due date to be set
     *
     * @param view due date switch
     */
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
}
