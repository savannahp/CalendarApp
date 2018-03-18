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

    // Strings for identifying log messages and also organizing things in the database
    private static final String TAG = "ListModel";
    private static final String TASK_BY_LIST = "TaskByList";
    private static final String TASK_BY_TAG = "TaskByTag";
    private static final String LISTS = "Lists";
    private static final String TAGS= "Tags";

    // These hold the to-do list data
    private Map<String, List<Task>> tasksByList = new HashMap<>();
    private Map<String, List<Task>> tasksByTag = new HashMap<>();
    private Set<String> lists = new HashSet<>();
    private Set<String> tags = new HashSet<>();

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

            Log.d(TAG, "Preparing to update " + TASK_BY_TAG);
            tasksByTag = dataSnapshot.child(user.getUid()).child(TAG).child(TASK_BY_TAG).getValue(Map.class);
            Log.d(TAG, TASK_BY_TAG + " updated");

            Log.d(TAG, "Preparing to update " + LISTS);
            lists = dataSnapshot.child(user.getUid()).child(TAG).child(LISTS).getValue(Set.class);
            Log.d(TAG, LISTS + " updated");

            Log.d(TAG, "Preparing to update " + TAGS);
            tags = dataSnapshot.child(user.getUid()).child(TAG).child(TAGS).getValue(Set.class);
            Log.d(TAG, TAGS + " updated");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "onCancelled", databaseError.toException());
        }
    };

    // Default constructor with no args required for Firebase Database
    public ListModel() {

        // Add the database listeners (I hope this works here **fingers crossed**)
        databaseRef.addValueEventListener(postListener);

    }

    /**
     * Add Task
     *
     * Adds a task to its corresponding list
     *
     * @param newTask the new task to be added to a list
     */
    public void addTask(Task newTask) {

        // Get the list to be added to
        List<Task> taskList = getTasksByList(newTask.getListName());
        // If list does not exist yet, getTaskByList() creates it

        // Add the new task to the list
        taskList.add(newTask);

        // For loop to add new tags to our taskByTag map
        for(Tag t : newTask.getTags()) {
            addTag(t);
            tasksByTag.get(t.getTagName()).add(newTask);
        }
    }

    /**
     * Add List
     *
     * This function adds a new to do list for the user.
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
        }
    }

    /**
     * Add tag
     *
     * This function adds a tag to our model and database
     *
     * @param tag The tag to be added
     */
    public void addTag(Tag tag) {
        // If taskByTag doesn't contain the tag name...
        if (!tasksByTag.containsKey(tag.getTagName())) {
            // ...add the tag name to the set of all tags
            tags.add(tag.getTagName());
            // ...add the tag to our tasksByTag map
            tasksByTag.put(tag.getTagName(), new ArrayList<Task>());
            // ...update the database
            databaseRef.child(user.getUid()).child(TAG).child(TAGS).setValue(tags);
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

    public Map<String, List<Task>> getTasksByTag() {
        return tasksByTag;
    }

    public List<Task> getTasksByTag(Tag tag) {
        return tasksByTag.get(tag.getTagName());
    }

    public List<Task> getTasksByTag(String tag) {
        return tasksByTag.get(tag);
    }

    public Set<String> getLists() {
        return lists;
    }

    public Set<String> getTags() {
        return tags;
    }

    // Setters
    public void setTasksByList(Map<String, List<Task>> tasksByList) {
        this.tasksByList = tasksByList;
    }

    public void setTasksByTag(Map<String, List<Task>> tasksByTag) {
        this.tasksByTag = tasksByTag;
    }

    public void setLists(Set<String> lists) {
        this.lists = lists;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
