package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * This is the main activity where user can see all of the lists created
 */
public class ListMainActivity extends AppCompatActivity {
    private final String TAG = "ListMainActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        Log.d(TAG, "Firebase User: " + FirebaseAuth.getInstance().getCurrentUser());

        Log.d(TAG, "Creating ListMainActivity");

        setTitle("All Lists");

        ListModel listModel = ListModel.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.includeRecycler);

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

    /**
     * Start the single list activity
     *
     * @param view The view that called this method
     */
    public void goToSingleList(View view) {
        Button button = (Button) view;
        String listName = (String) button.getText();

        Log.i(TAG, "Creating intent for SingleListActivity");

        Intent intent = new Intent(this, SingleListActivity.class);
        intent.putExtra(Extra.LIST, listName);

        Log.i(TAG, "Intent created. Starting Activity with intent");

        startActivity(intent);
    }

    /**
     * Start the add list activity
     *
     * @param view The view that called this method
     */
    public void addNewList(View view) {
        Intent intent = new Intent(this, AddListActivity.class);
        startActivity(intent);

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
}
