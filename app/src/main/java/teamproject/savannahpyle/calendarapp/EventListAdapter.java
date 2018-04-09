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

        // Get the start time of the event and put it in a string

        String startTime;

        Integer intHour = mDataset.get(position).getStart().get(Calendar.HOUR_OF_DAY); // This uses 24 hour time

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

        Integer intMinute = mDataset.get(position).getStart().get(Calendar.MINUTE);

        if (intMinute >= 10)
            startTime = startTime.concat(":" + intMinute.toString());
        else
            startTime = startTime.concat(":0" + intMinute.toString());

        Integer amPm = mDataset.get(position).getStart().get(Calendar.AM_PM);

        if (amPm.equals(Calendar.AM))
            startTime = startTime.concat("AM");
        else
            startTime = startTime.concat("PM");


        intHour = mDataset.get(position).getEnd().get(Calendar.HOUR_OF_DAY); // This uses 24 hour time
        String endTime;

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

        intMinute = mDataset.get(position).getEnd().get(Calendar.MINUTE);

        if (intMinute >= 10)
            endTime = endTime.concat(":" + intMinute.toString());
        else
            endTime = endTime.concat(":0" + intMinute.toString());

        amPm = mDataset.get(position).getEnd().get(Calendar.AM_PM);

        if (amPm.equals(Calendar.AM))
            endTime = endTime.concat("AM");
        else
            endTime = endTime.concat("PM");


        // Text to put in the button for the view holder
        String text = startTime + " - " + endTime + ": " + mDataset.get(position).getEventName();

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
