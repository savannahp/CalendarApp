package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

    public void addList(View view) {

        Log.d(TAG, "Retrieving List Model Instance");
        ListModel model = ListModel.getInstance();

        EditText text = (EditText) findViewById(R.id.listInput);
        String listName = text.getText().toString();

        Boolean addList = model.addList(listName);

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

    public void cancel(View view) {
        Log.i(TAG, "Creating intent for ListMainActivity");
        Intent intent = new Intent(this, ListMainActivity.class);
        Log.i(TAG, "Intent created. Starting Activity with intent");
        startActivity(intent);
    }
}
