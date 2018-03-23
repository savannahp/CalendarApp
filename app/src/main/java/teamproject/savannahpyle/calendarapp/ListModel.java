package teamproject.savannahpyle.calendarapp;

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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private static volatile ListModel instance;
    
    // Strings for identifying log messages and also organizing things in the database
    private static final String TAG = "ListModel";
    private static final String TASK_BY_LIST = "TaskByList";
    private static final String LISTS = "Lists";

    //------------------------------------------------------------
    // These hold the to-do list data
    private Map<String, List<Task>> tasksByList = new HashMap<>();
    private Set<String> lists = new HashSet<>();
    //------------------------------------------------------------

    // Priority levels 1. no priority 2. ! 3. !!

    // Sort by due date, by priority level, by overdue
    // Delete completed tasks option/button


    // For accessing the database
    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // Listener for changes in the database
    ValueEventListener postListener = new ValueEventListener() {
        @Override
        @SuppressWarnings("unchecked")
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(TAG, "Preparing to update " + TASK_BY_LIST);
            tasksByList = dataSnapshot.child(user.getUid()).child(TAG).child(TASK_BY_LIST).getValue(Map.class);
            Log.d(TAG, TASK_BY_LIST + " updated");

            Log.d(TAG, "Preparing to update " + LISTS);
            lists = dataSnapshot.child(user.getUid()).child(TAG).child(LISTS).getValue(Set.class);
            Log.d(TAG, LISTS + " updated");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "onCancelled", databaseError.toException());
        }
    };

    /** Default constructor with no args required for Firebase Database */
    private ListModel() {

        // Add the database listeners (I hope this works here **fingers crossed**)
        databaseRef.addValueEventListener(postListener);

    }

    public static ListModel getInstance() {
        if (instance == null) {
            synchronized (ListModel.class) {
                if (instance == null) {
                    instance = new ListModel();
                }
            }
        }
        return instance;
    }
    /**
     * Add a task to its corresponding list
     *
     * @param newTask the new task to be added to a list, tasks have member variable for list name
     */
    public void addTask(Task newTask) {

        // Get the list to be added to
        List<Task> taskList = getTasksByList(newTask.getListName());
        // If list does not exist yet, getTaskByList() creates it

        // Add the new task to the list
        taskList.add(newTask);
    }

    /**
     * Adds a new to-do list for the user.
     *
     * @param listName The name of the list to be created
     */
    public void addList(String listName) {
        // If tasksByList doesn't contain the listName...
        if (!tasksByList.containsKey(listName)) {
            // ...add the list name to the set of all list names
            lists.add(listName);
            // ...create a new to-do list and put it in our map
            tasksByList.put(listName, new ArrayList<Task>());
            // ...update branch under users with unique user ID and add tasksByList for that user
            databaseRef.child(user.getUid()).child(TAG).child(TASK_BY_LIST).setValue(tasksByList);
            databaseRef.child(user.getUid()).child(TAG).child(LISTS).setValue(lists);
        }
    }

    // Getters
    public Map<String, List<Task>> getTasksByList() {
        return tasksByList;
    }

    public List<Task> getTasksByList(String list) {

        // If the list has not been created, create the list
        if (!tasksByList.containsKey(list))
            addList(list);
        return tasksByList.get(list);
    }

    public Set<String> getLists() {
        return lists;
    }

    // Setters
    public void setTasksByList(Map<String, List<Task>> tasksByList) {
        this.tasksByList = tasksByList;
    }

    public void setLists(Set<String> lists) {
        this.lists = lists;
    }
}
