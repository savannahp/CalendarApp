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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

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
    
    // Log message tag
    private static final String TAG = "ListModel";

    // These hold the to-do list data
    private Map<String, Object> lists;

    // TODO: add these features to the list
    // Sort by due date, by priority level, by overdue
    // Delete completed tasks option/button

    // For accessing the Firebase Database
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference(user.getUid());


    /**
     * Default constructor with no args required for Firebase Database.
     * Initializes listener for list model and adds it to even listeners.
     */
    private ListModel() {

        databaseRef.setValue("Hello world");
//        if (user != null) {
//            // Listener for changes in the database
//            ValueEventListener postListener = new ValueEventListener() {
//                @Override
//                @SuppressWarnings("unchecked")
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.d(TAG, "Preparing to update " + TASK_LISTS);
//                    taskLists = (Map) dataSnapshot.child(user.getUid()).child(TAG).child(TASK_LISTS).getValue();
//                    Log.d(TAG, TASK_LISTS + " updated");
//
//                    Log.d(TAG, "Preparing to update " + LISTS);
//                    lists = (List<String>) dataSnapshot.child(user.getUid()).child(TAG).child(LISTS).getValue();
//                    Log.d(TAG, LISTS + " updated");
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Getting Post failed, log a message
//                    Log.w(TAG, "onCancelled", databaseError.toException());
//                }
//            };
//
//            // Add the database listeners (I hope this works here *fingers crossed*)
//            databaseRef.addValueEventListener(postListener);
            lists = new TreeMap<>();
//        }
    }

    /**
     * Gets the list model instance.
     *
     * @return The singleton instance of the list model
     */
    public static ListModel getInstance() {
        Log.d(TAG, "Getting ListModel instance");

        // TODO: EVERY TIME YOU ADD THINGS CHECK IF THE INSTANCE IS NULL

//        // Double-checked-lazy singleton
//        if (instance == null) {
//            Log.d(TAG, "Instance was NULL");
//            synchronized (ListModel.class) {
//                if (instance == null) {
//                    Log.d(TAG, "Initializing the singleton ListModel");
//                    instance = new ListModel();
//                    taskLists = new HashMap<>();
//                    lists = new ArrayList<>();
//                    Log.d(TAG, "Instance initialized successfully");
//                }
//            }
//        }
        Log.d(TAG, "Returning instance");
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
        ToDoList list = (ToDoList) lists.get(listName);
        list.addTask(description);
    }

    /**
     * Adds a new to-do list for the user.
     *
     * @param listName The name of the list to be created
     */
    public void addList(String listName) {

        // This should not be null
        if (lists == null)
            lists = new TreeMap<>();

        // If taskLists doesn't contain the listName...
        if (!lists.containsKey(listName)) {
            ToDoList taskList = new ToDoList(listName);
            lists.put(listName, taskList);
            Log.d(TAG, "To Do List added to model");

            // If the user is not null, we update the database
            if (user != null) {
                Log.d(TAG, "Updating database");
                // ...update branch under users with unique user ID and add tasksByList for that user
                databaseRef.child(TAG).setValue(lists);
                Log.d(TAG, "Database update complete");
            }
        }
    }

    /**
     * Get a specific TaskList by its name as the key
     *
     * @param listNameKey The name of the list to be returned
     * @return A TaskList or null if that key is not in TaskList map
     */
    public Object getTaskList(String listNameKey) {
        return lists.get(listNameKey);
    }

    /**
     * Gets the TaskLists map. Mainly for use by Firebase Database.
     *
     * @return The map of TaskLists (String key, TaskList value)
     */
    public Map<String, Object> getToDoLists() {
        return lists;
    }

    /**
     * Sets the TaskList map. Mainly for use by Firebase Database.
     *
     * @param taskLists Map of TaskLists (String key, TaskList value)
     */
    public void setLists(Map<String, Object> toDoLists) {
        lists = toDoLists;
    }

}
