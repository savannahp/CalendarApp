package teamproject.savannahpyle.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savannahpyle on 3/21/18.
 *
 * The list adapter helps control the view of a to-do list.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private static final String TAG = "ListAdapter";

    /**
     * For holding all of our data
     */
    private List<String> mDataset = new ArrayList<>();

    /**
     * Our custom ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mButton;
        public ViewHolder(Button v) {
            super(v);
            mButton = v;
        }
    }

    /**
     * Constructor that adds a set of list names to our data set
     * @param listNames Contains set of string list names
     */
    public ListAdapter(List<ToDoList> listNames) {
        if (listNames != null) {
            for (ToDoList t : listNames) {
                mDataset.add(t.getListName());
            }
        }
    }


    /**
     * Creates and returns a {@link ViewHolder}
     * @param parent The parent View
     * @param viewType Integer that specified the type of view
     * @return A new {@link ViewHolder}
     */
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button v = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_list_select, parent, false);

        return new ViewHolder(v);
    }

    /**
     * Binds data from our data set to the view
     *
     * @param holder {@link ViewHolder} to bind to
     * @param position The index of the data in the list
     */
    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mButton.setText(mDataset.get(position));
    }

    /**
     * Gets num items from data set
     * @return The number of items in the data set
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
