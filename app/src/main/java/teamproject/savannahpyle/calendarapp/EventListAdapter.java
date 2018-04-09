package teamproject.savannahpyle.calendarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Calendar;
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
    public EventListAdapter(List<Event> tasks) {
        if (tasks != null)
            mDataset = tasks;
        else
            mDataset = new ArrayList<>();
    }

    /**
     * Creates and returns a {@link CheckListAdapter.ViewHolder}
     * @param parent The parent View
     * @param viewType Integer that specified the type of view
     * @return A new {@link CheckListAdapter.ViewHolder}
     */
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button b = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_list_select, parent, false);

        // TODO: Do we need to add anything here?

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

        // Event start time string
        String startTime;

        // Get the hours for the start time
        Integer intHour = mDataset.get(position).getStart().get(Calendar.HOUR_OF_DAY); // This uses 24 hour time

        // Format the start time string for the hour portion
        if (intHour >= 10) {
            if (intHour > 12) {
                intHour -= 12; // Because of 24 hour time stuff
                if (intHour < 10)
                    startTime = "  " + intHour.toString();
                else
                    startTime = intHour.toString();
            }
            else
                startTime = intHour.toString();
        }
        else if (intHour == 0) {
            intHour += 12;
            startTime = intHour.toString();
        }
        else
            startTime = "  " + intHour.toString();

        // Get the minutes for the start time
        Integer intMinute = mDataset.get(position).getStart().get(Calendar.MINUTE);

        // Format the start time string for the minute portion
        if (intMinute >= 10)
            startTime = startTime.concat(":" + intMinute.toString());
        else
            startTime = startTime.concat(":0" + intMinute.toString());

        // Get the integer representing am or pm for the start time
        Integer amPm = mDataset.get(position).getStart().get(Calendar.AM_PM);

        // Add am or pm to the start time string
        if (amPm.equals(Calendar.AM))
            startTime = startTime.concat("AM");
        else
            startTime = startTime.concat("PM");


        // Get the hour of the end time
        intHour = mDataset.get(position).getEnd().get(Calendar.HOUR_OF_DAY); // This uses 24 hour time

        // String to hold the end time of the event
        String endTime;

        // Format the end time string for the hour portion
        if (intHour >= 10) {
            if (intHour > 12) {
                intHour -= 12; // Because of 24 hour time stuff
                if (intHour < 10)
                    endTime = "  " + intHour.toString();
                else
                    endTime = intHour.toString();
            }
            else
                endTime = intHour.toString();
        }
        else if (intHour == 0) {
            intHour += 12;
            endTime = intHour.toString();
        }
        else
            endTime = "  " + intHour.toString();

        // get the minute for the end time
        intMinute = mDataset.get(position).getEnd().get(Calendar.MINUTE);

        // Format the start time string for the minute portion
        if (intMinute >= 10)
            endTime = endTime.concat(":" + intMinute.toString());
        else
            endTime = endTime.concat(":0" + intMinute.toString());

        // Get the am or pm integer from the end time
        amPm = mDataset.get(position).getEnd().get(Calendar.AM_PM);

        // Add am or pm to the end time string
        if (amPm.equals(Calendar.AM))
            endTime = endTime.concat("AM");
        else
            endTime = endTime.concat("PM");


        // Text to put in the button for the view holder
        String text = startTime + " - " + endTime + ": " + mDataset.get(position).getEventName();

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
