package teamproject.savannahpyle.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Shows all the events and todolists associated with this selected day
 */
public class SingleDayActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btngocalendar;
    private String date;
    private int year;
    private int month;
    private int dayOfMonth;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView mRecyclerViewList;
    private RecyclerView.Adapter mAdapterList;
    private RecyclerView.LayoutManager mLayoutManagerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_day);

        btngocalendar = (Button) findViewById(R.id.btngocalendar);

        Intent incoming = getIntent();
        date = incoming.getStringExtra(Extra.DATE);

        // Set title to the date selected
        setTitle(date);

        year = incoming.getIntExtra(Extra.YEAR, 0);
        month = incoming.getIntExtra(Extra.MONTH, 0);
        dayOfMonth = incoming.getIntExtra(Extra.DAY, 0);

        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleDayActivity.this, MonthActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.includeRecycler);

        Log.d(TAG, "Recycler view =" + mRecyclerView.toString());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        CalendarModel model = CalendarModel.getInstance();

        // specify an adapter (see also next example)
        mAdapter = new EventListAdapter(model.getEventList(date));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerViewList = (RecyclerView) findViewById(R.id.includeRecyclerListItems);

        Log.d(TAG, "Recycler List view =" + mRecyclerViewList.toString());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerViewList.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManagerList = new LinearLayoutManager(this);
        mRecyclerViewList.setLayoutManager(mLayoutManagerList);

        ListModel listModel = ListModel.getInstance();

        ToDoList tasks = listModel.getToDoListByDate(date);

        if (tasks != null) {
            // specify an adapter (see also next example)
            mAdapterList = new CheckListAdapter(tasks.getTasks(), tasks.getIsComplete());

            TextView text = findViewById(R.id.textViewListItems);
            text.setText(tasks.getListName());
        }
        mRecyclerViewList.setAdapter(mAdapterList);

    }

    /**
     * Goes to the add event activity
     *
     * @param view add button
     */
    public void addEvent(View view) {

        Intent intent = new Intent(this, AddEventActivity.class);
        intent.putExtra(Extra.DATE, date);
        intent.putExtra(Extra.YEAR, year);
        intent.putExtra(Extra.MONTH, month);
        intent.putExtra(Extra.DAY, dayOfMonth);
        startActivity(intent);
    }
}
