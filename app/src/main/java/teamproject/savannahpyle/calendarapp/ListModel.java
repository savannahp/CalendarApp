package teamproject.savannahpyle.calendarapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    // For the singleton pattern
    private static volatile ListModel instance;
    
    // Log message tag
    private static final String TAG = "ListModel";

    // Tags for member variables to use with database
    private static final String TASK_LISTS = "TaskLists";
    private static final String LISTS = "Lists";

    // These hold the to-do list data
    private Map<String, TaskList> taskLists = new HashMap<>();
    private Set<String> lists = new TreeSet<>();

    // TODO: add these features to the list
    // Sort by due date, by priority level, by overdue
    // Delete completed tasks option/button

//    // For accessing the Firebase Database
//    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//    /**
//     * Default constructor with no args required for Firebase Database.
//     * Initializes listener for list model and adds it to even listeners.
//     */
//    private ListModel() {
//
//        // Listener for changes in the database
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            @SuppressWarnings("unchecked")
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "Preparing to update " + TASK_LISTS);
//                taskLists = dataSnapshot.child(user.getUid()).child(TAG).child(TASK_LISTS).getValue(Map.class);
//                Log.d(TAG, TASK_LISTS + " updated");
//
//                Log.d(TAG, "Preparing to update " + LISTS);
//                lists = dataSnapshot.child(user.getUid()).child(TAG).child(LISTS).getValue(Set.class);
//                Log.d(TAG, LISTS + " updated");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "onCancelled", databaseError.toException());
//            }
//        };
//
//        // Add the database listeners (I hope this works here *fingers crossed*)
//        databaseRef.addValueEventListener(postListener);
//    }

    /**
     * Gets the list model instance.
     *
     * @return The singleton instance of the list model
     */
    public static ListModel getInstance() {
        Log.d(TAG, "Getting ListModel instance");

        // Double-checked-lazy singleton
        if (instance == null) {
            Log.d(TAG, "Instance was NULL");
            synchronized (ListModel.class) {
                if (instance == null) {
                    Log.d(TAG, "Initializing the singleton ListModel");
                    instance = new ListModel();
                    Log.d(TAG, "Instance initialized successfully");
                }
            }
        }
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
        taskLists.get(listName).addTask(description);
    }

    /**
     * Adds a new to-do list for the user.
     *
     * @param listName The name of the list to be created
     */
    public void addList(String listName) {
        // If taskLists doesn't contain the listName...
        if (!taskLists.containsKey(listName)) {
            TaskList taskList = new TaskList(listName);
            lists.add(listName);
            taskLists.put(listName, taskList);
            // ...update branch under users with unique user ID and add tasksByList for that user
//            databaseRef.child(user.getUid()).child(TAG).child(TASK_LISTS).setValue(taskLists);
//            databaseRef.child(user.getUid()).child(TAG).child(LISTS).setValue(lists);
        }
    }

    /**
     * Get a specific TaskList by its name as the key
     *
     * @param listNameKey The name of the list to be returned
     * @return A TaskList or null if that key is not in TaskList map
     */
    public TaskList getTaskList(String listNameKey) {
        return taskLists.get(listNameKey);
    }

    /**
     * Gets the TaskLists map. Mainly for use by Firebase Database.
     *
     * @return The map of TaskLists (String key, TaskList value)
     */
    public Map<String, TaskList> getTaskLists() {
        return taskLists;
    }

    /**
     * Sets the TaskList map. Mainly for use by Firebase Database.
     *
     * @param taskLists Map of TaskLists (String key, TaskList value)
     */
    public void setTaskLists(Map<String, TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    /**
     * Gets the set of all TaskList names in model.
     *
     * @return Set of all TaskList names.
     */
    public Set<String> getLists() {
        return lists;
    }

    /**
     * Set the set of all TaskList names in model.
     *
     * @param lists Set of TaskList names.
     */
    public void setLists(Set<String> lists) {
        this.lists = lists;
    }
}
