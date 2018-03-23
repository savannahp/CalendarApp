package teamproject.savannahpyle.calendarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashSet;
import java.util.Set;

public class ListMainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        ListModel model = ListModel.getInstance();

//        // Fake data to test with
//        Set<String> listNames = new HashSet<>();
//        listNames.add("Grocery List");
//        listNames.add("Shopping List");
//        listNames.add("Walmart List");
//        listNames.add("School List");
//        listNames.add("Broulim's List");
//        listNames.add("Task List");
//        listNames.add("1");
//        listNames.add("2");
//        listNames.add("3");
//        listNames.add("4");
//        listNames.add("5");
//        listNames.add("6");
//        listNames.add("7");
//        listNames.add("8");
//        listNames.add("9");
//        listNames.add("10");
//        listNames.add("q");
//        listNames.add("w");
//        listNames.add("e");
//        listNames.add("t");
//        listNames.add("r");
//        listNames.add("y");
//        listNames.add("u");
//        listNames.add("i");
//        listNames.add("o");
//        listNames.add("p");
//        listNames.add("a");
//        listNames.add("s");
//        listNames.add("d");
//        listNames.add("f");
//        listNames.add("g");
//        listNames.add("h");
//        listNames.add("j");
//        listNames.add("k");
//        listNames.add("l");
//        listNames.add("z");
//        listNames.add("x");
//        listNames.add("c");
//        listNames.add("v");
//        listNames.add("b");
//        listNames.add("n");
//        listNames.add("m");
//        listNames.add("e123");
//        listNames.add("w123");
//        listNames.add("a22235");
//        model.setLists(listNames);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(model.getLists());
        mRecyclerView.setAdapter(mAdapter);

    }
}
