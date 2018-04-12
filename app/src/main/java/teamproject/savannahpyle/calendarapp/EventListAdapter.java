package teamproject.savannahpyle.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private static final String TAG = "EventListAdapter";

    /**
     * For holding all of our data
     */
    private List<Event> mDataset;

    /**
     * Our custom ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        public ViewHolder(Button b) {
            super(b);
            button = b;
        }
    }

    /**
     * Constructor that adds a set of list names to our data set
     * @param tasks Contains set of string list names
     */
    public EventListAdapter(EventList tasks) {
        if (tasks != null)
            mDataset = tasks.getEventList();
        else
            mDataset = new ArrayList<>();
    }

    /**
     * Creates and returns a {@link EventListAdapter.ViewHolder}
     * @param parent The parent View
     * @param viewType Integer that specified the type of view
     * @return A new {@link EventListAdapter.ViewHolder}
     */
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button b = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_list_select, parent, false);

        return new EventListAdapter.ViewHolder(b);
    }

    /**
     * Binds data from our data set to the view
     *
     * @param holder {@link EventListAdapter.ViewHolder} to bind to
     * @param position The index of the data in the list
     */
    @Override
    public void onBindViewHolder(EventListAdapter.ViewHolder holder, int position) {

        String start = mDataset.get(position).getStart();
        String end = mDataset.get(position).getEnd();
        String name = mDataset.get(position).getEventName();

        // Text to put in the button for the view holder
        String text = start + " - " + end + ": " + name;

        // Set the text of the button
        holder.button.setText(text);
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
