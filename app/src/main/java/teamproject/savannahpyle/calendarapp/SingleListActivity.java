package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Map;

public class SingleListActivity extends AppCompatActivity {

    private static final String TAG = "SingleListActivity";
    private ToDoList toDoList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<CheckListAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list);

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        Log.d(TAG, "About to receive intent");

        Intent intent = getIntent();

        Log.d(TAG, "Intent received");

        String listName = intent.getStringExtra(Extra.LIST);

        Log.d(TAG, "intent.getStringExtra(Extra.LIST): " + listName);

        // Sets the title of the activity to the name of current list
        setTitle("List: " + listName);

        ListModel model = ListModel.getInstance();

        toDoList = model.getToDoList(listName);

        Log.d(TAG, "toDoList = " + toDoList);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CheckListAdapter(this.toDoList.getTasks(), this.toDoList.getIsComplete());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Starts the add task activity.
     *
     * @param view The view that was clicked to call this function
     */
    public void addTask(View view) {
        // Create intent and put the list name in as extra so the new task knows
        // which list it is being added to.
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra(Extra.TASK, toDoList.getListName());
        startActivity(intent);
    }

    /**
     * Updates the completion of the task that was clicked.
     *
     * @param view View that was clicked (CheckBox)
     */
    public void markTaskComplete(View view) {

        CheckBox checkBox = (CheckBox) view;

        String task = checkBox.getText().toString();

        toDoList.setTaskComplete(task);
    }

    /**
     * Start the home activity
     *
     * @param view The view that called this method
     */
    public void homeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void removeTasks(View view) {
        toDoList.removeCompleted();
        mAdapter = new CheckListAdapter(this.toDoList.getTasks(), this.toDoList.getIsComplete());
        mRecyclerView.setAdapter(mAdapter);
    }
}





























































































































































































































































































































// HA. GOT 'EM.