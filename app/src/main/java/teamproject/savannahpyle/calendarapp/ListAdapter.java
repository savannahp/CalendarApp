package teamproject.savannahpyle.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by savannahpyle on 3/21/18.
 *
 * The list adapter helps control the view of a to-do list.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    // Holds the data to fill list with
    private List<String> mDataset = new ArrayList<>();

    /**
     * Our custom ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    /**
     * Constructor that adds a set of list names to our data set
     * @param listNames Contains set of string list names
     */
    public ListAdapter(Set<String> listNames) {
        mDataset.addAll(listNames);
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_text_view, parent, false);

        // TODO: Do we need to add anything here?

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
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
