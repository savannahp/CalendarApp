package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class ListMainActivity extends AppCompatActivity {
    private final String TAG = "ListMainActivity";
    public static final String EXTRA_MESSAGE = "teamproject.savannahpyle.calendarapp: ListMainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        Log.d(TAG, "Creating ListMainActivity");

        ListModel listModel = ListModel.getInstance();

        // Fake data to test with
        for (Integer i = 0; i <= 20; i++) {
            listModel.addList("List " + i.toString());
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);

        Log.d(TAG, "Recycler view =" + mRecyclerView.toString());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(listModel.getLists());
        mRecyclerView.setAdapter(mAdapter);

    }

    public void goToSingleList(View view) {
        Button button = (Button) view;
        String listName = (String) button.getText();

        Log.i(TAG, "Creating intent for SingleListActivity");

        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(EXTRA_MESSAGE, listName);

        Log.i(TAG, "Intent created. Starting Activity with intent");

        startActivity(intent);
    }
}
