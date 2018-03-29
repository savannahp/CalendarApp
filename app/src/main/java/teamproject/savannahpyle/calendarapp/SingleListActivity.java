package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class SingleListActivity extends AppCompatActivity {

    private static final String TAG = "SingleListActivity";
    private TaskList taskList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list);

        Log.d(TAG, "About to receive intent");
        Intent intent = getIntent();
        String listName = intent.getStringExtra(ListMainActivity.EXTRA_MESSAGE);
        Log.d(TAG, "Intent received");

        Log.d(TAG, "Name of list: " + listName);

        ListModel listModel = ListModel.getInstance();

        this.taskList = listModel.getTaskList(listName);

        for (Integer i = 1; i <= 20; i++) {
            taskList.addTask(listName + " Task " + i.toString());
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(this.taskList.getTasksAsStrings());
        mRecyclerView.setAdapter(mAdapter);
    }
}
