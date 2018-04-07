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

    private Map<String, Object> lists; // Object is actually type ToDoList

    // For accessing the Firebase Database
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(user.getUid());

    /**
     * Default constructor with no args required for Firebase Database.
     * Initializes listener for list model and adds it to even listeners.
     */
    private ListModel() {

        // Listener for changes in the database
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Preparing to update the ListModel");
                lists = (Map<String, Object>) dataSnapshot.child(TAG).getValue();
                Log.d(TAG, "ListModel has been updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        };

        // Add the database listeners (I hope this works here *fingers crossed*)
        databaseRef.addValueEventListener(postListener);
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
        Map listMap = (Map)lists.get(listName);

        String name = (String)listMap.get("listName");
        List tasks = (List) listMap.get("tasks");

        ToDoList toDoList = new ToDoList(name, tasks);

        toDoList.addTask(description);

        lists.replace(toDoList.getListName(), toDoList);
    }

    /**
     * Add a task to a list using the the TaskList's addTask()
     * function.
     *
     * @param listName The name of the list to add task to
     * @param description Description of the task
     * @param dueDate Due date of the task
     */
    public void addTask(String listName, String description, GregorianCalendar dueDate) {
        ToDoList list = (ToDoList) lists.get(listName);
        list.addTask(description, dueDate);
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
            lists = new TreeMap<>();

        // If lists doesn't contain the listName...
        if (!lists.containsKey(listName)) {
            ToDoList toDoList = new ToDoList(listName);
            lists.put(listName, toDoList);
            Log.d(TAG, "To Do List added to model");

            // Update branch in database with current lists value
            update();

            return true;
        }

        return false;
    }

    /**
     * Get a specific TaskList by its name as the key
     *
     * @param listName The name of the list to be returned
     * @return A TaskList or null if that key is not in TaskList map
     */
    public Object getToDoList(String listName) {
        return lists.get(listName);
    }

    /**
     * Gets the TaskLists map. Mainly for use by Firebase Database.
     *
     * @return The map of TaskLists (String key, TaskList value)
     */
    public Map<String, Object> getLists() {
        return lists;
    }

    /**
     * Sets the TaskList map. Mainly for use by Firebase Database.
     *
     * @param toDoLists Map of TaskLists (String key, TaskList value)
     */
    public void setLists(Map<String, Object> toDoLists) {
        lists = toDoLists;
    }

}
