package teamproject.savannahpyle.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by savannahpyle on 3/21/18.
 *
 * The list adapter helps control the view of a to-do list.
 */
public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.ViewHolder>{

    private static final String TAG = "CheckListAdapter";

    /**
     * For holding all of our data
     */
    private List<Task> mDataset = new ArrayList<>();

    /**
     * Our custom ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public ViewHolder(CheckBox c) {
            super(c);
            checkBox = c;
        }
    }

    /**
     * Constructor that adds a set of list names to our data set
     * @param tasks Contains set of string list names
     */
    public CheckListAdapter(List<Task> tasks) {
        try {
            mDataset.addAll(tasks);
        } catch (NullPointerException np) {
            Log.d(TAG, "List of Tasks is null: " + np.getMessage());
        }
    }

    /**
     * Creates and returns a {@link CheckListAdapter.ViewHolder}
     * @param parent The parent View
     * @param viewType Integer that specified the type of view
     * @return A new {@link CheckListAdapter.ViewHolder}
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckBox b = (CheckBox) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_list, parent, false);

        // TODO: Do we need to add anything here?

        return new ViewHolder(b);
    }

    /**
     * Binds data from our data set to the view
     *
     * @param holder {@link CheckListAdapter.ViewHolder} to bind to
     * @param position The index of the data in the list
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.checkBox.setText(mDataset.get(position).getDescription());

        if (mDataset.get(position).isComplete()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
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
