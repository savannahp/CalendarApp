package teamproject.savannahpyle.calendarapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

// TODO: add these features to the list
// Sort by due date, by priority level, by overdue
// Delete completed tasks option/button

/**
 * Created by paulland on 3/16/18.
 *
 * This class contains various Objects to model the data of
 * the to-do lists created by the user.
 *
 * It established a connection to the Firebase Database in
 * order to load and sync data for the list model.
 *
 * @author Paul Land
 */
public class ListModel {

    // For the singleton pattern (Eager singleton)
    private static final ListModel instance = new ListModel();

    private static final String TAG = "ListModel";

    public List<ToDoList> lists;

    // For accessing the Firebase Database
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(user.getUid());

    /**
     * Default constructor with no args required for Firebase Database.
     * Initializes listener for list model and adds it to even listeners.
     */
    private ListModel() {

        lists = new ArrayList<>();

        // Listener for changes in the database
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Preparing to update the ListModel");

                Object o = dataSnapshot.child(TAG).getValue();

                if (o == null)
                    return;

                // We don't know what type object will be
                List <Map<String, Object>> list = new ArrayList<>();

                if (o.getClass().isInstance(list))
                    list = (List<Map<String, Object>>) o;

                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = list.get(i);

                    // Get name of the list from the map
                    String name;
                    if (map.get("listName") == null)
                        ; // Purposely empty because
                          // We don't want to add a ToDoList
                          // with a null name.
                    else {// Otherwise, we translate the map data to create a ToDoList
                        name = (String) map.get("listName");

                        // Get List of bools from the map
                        List<Boolean> bools = new ArrayList<>();
                        if (map.get("isComplete") != null)
                            bools.addAll((List<Boolean>) map.get("isComplete"));
                        else
                            bools = null;

                        // Get list of strings (tasks) from the map
                        List<String> tasks = new ArrayList<>();
                        if (map.get("tasks") != null)
                            tasks.addAll((List<String>) map.get("tasks"));
                        else
                            tasks = null;

                        lists.add(new ToDoList(name, tasks, bools));
                    }
                }


                Log.d(TAG, "ListModel has been updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        };

        // Add the database listeners (I hope this works here *fingers crossed*)
        databaseRef.addListenerForSingleValueEvent(postListener);
    }

    public void update() {
        Log.d(TAG, "Updating database");
        databaseRef.child(TAG).setValue(lists);
        Log.d(TAG, "Database update complete");
    }

    /**
     * Gets the list model instance.
     *
     * @return The singleton instance of the list model
     */
    public static ListModel getInstance() {
        Log.d(TAG, "Getting and returning ListModel instance");
        return instance;
    }

    /**
     * Add a task to a list using the the TaskList's addTask()
     * function.
     *
     * @param listName The name of the list to add task to
     * @param description Description of the task
     */
    public void addTask(String listName, String description) {

        ToDoList t = new ToDoList();

        for (int i = 0; i < lists.size(); i++) {
            t = lists.get(i);
            if (t.getListName().equals(listName)) {
                break;
            }
        }
        t.addTask(description);
        update();
    }

    /**
     * Add a task to a list using the the TaskList's addTask()
     * function.
     *
     * @param listName The name of the list to add task to
     * @param description Description of the task
     * @param date String due date of the task
     */
    public void addTask(String listName, String description, String date) {

        ToDoList t = new ToDoList();

        for (int i = 0; i < lists.size(); i++) {
            t = lists.get(i);
            if (t.getListName().equals(listName)) {
                break;
            }
        }
        t.addTask(description, date);
        update();
    }

    /**
     * Adds a new to-do list for the user.
     *
     * @param listName The name of the list to be created
     */
    public Boolean addList(String listName) {

        // Initialize lists if this is the first to-do list
        if (lists == null)
            lists = new ArrayList<>();

        ToDoList t;

        // If lists doesn't contain the listName...
        for (int i = 0; i <= lists.size(); i++) {
            if (i == lists.size()) {

                ToDoList toDoList = new ToDoList(listName);
                t = new ToDoList(listName);
                lists.add(t);
                Log.d(TAG, "To Do List added to model");

                // Update branch in database with current lists value
                update();

                return true;
            }

            t = lists.get(i);

            if (t.getListName().equals(listName)) {
                // TODO: add toast saying list already exists
                break;
            }
        }

        return false;
    }

    /**
     * Gets a to-do list by it's name
     *
     * @param listName The name of the list to be returned
     * @return A To-do list or null if that to-do list is not in lists
     */
    public ToDoList getToDoList(String listName) {
        ToDoList t;
        for (int i = 0; i < lists.size(); i++) {
            t = lists.get(i);
            if (t.getListName().equals(listName)) {
                return t;
            }
        }

        return null;
    }

    /**
     * Sets the To-Do List. Mainly for use by Firebase Database.
     *
     * @param toDoLists List of To-Do Lists
     */
    public void setLists(List<ToDoList> toDoLists) {
        lists = toDoLists;
    }

    /**
     * Getter for lists
     * @return List of ToDoLists
     */
    public List<ToDoList> getLists() {
        return lists;
    }
}
