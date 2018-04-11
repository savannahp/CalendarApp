package teamproject.savannahpyle.calendarapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by savannahpyle on 3/21/18.
 *
 * Task List is a container for {@link Task} objects. This container
 * is basically a to-do list.
 *
 * @author Paul Land and Savannah Pyle
 */
public class ToDoList {

    private static final String TAG = "ToDoList";

    private String firstTask = "Tap the '+' icon to add a task";

    private String listName;
    private List<String> tasks;
    private List<Boolean> isComplete;
    private List<String> dueDates;

    /**
     * Default constructor with no arguments for Firebase use.
     */
    public ToDoList() {

        dueDates = new ArrayList<>();
        tasks = new ArrayList<>();
        isComplete = new ArrayList<>();

        // There must be something in these vars for it to work with firebase
        tasks.add(firstTask);
        isComplete.add(true);
        dueDates.add(""); // Empty string means no due date
    }

    /**
     * Non-default constructor that takes a name and the list of tasks
     *
     * @param listName Name of the list
     * @param tasks A list of tasks to add to this list
     * @param isComplete list of booleans of whether task is complete
     */
    public ToDoList(String listName, List<String> tasks, List<Boolean> isComplete) {
        if (listName != null)
            this.listName = listName;

        if (tasks != null)
            this.tasks = tasks;
        else {
            this.tasks = new ArrayList<>();
            this.tasks.add(firstTask); // For firebase
        }

        if (isComplete != null)
            this.isComplete = isComplete;
        else {
            this.isComplete = new ArrayList<>();
            this.isComplete.add(true); // For firebase
        }
    }

    /**
     * Another non-default constructor that takes only the name of the list
     *
     * @param listName The name of the new list
     */
    public ToDoList(String listName) {
        this.listName = listName;
        tasks = new ArrayList<>();
        dueDates = new ArrayList<>();
        isComplete = new ArrayList<>();

        // For firebase
        tasks.add(firstTask);
        isComplete.add(true);
        dueDates.add(""); // empty string for no due date

    }

    /**
     * Adds a new task to this to-do list.
     *
     * @param Description Description of the new task being added
     */
    public void addTask(String Description) {

        if (Description == null)
            return;

        // An initial to-do list sets the value of the first task to "" and
        // the first isComplete to "true" so that it will be stored in firebase.
        // We must clear those initial values.
        if (tasks.size() == 1 && tasks.contains(firstTask)){
            tasks.clear();
            isComplete.clear();
            dueDates.clear();
        }

        // Add description and set completion to false
        tasks.add(Description);
        isComplete.add(false);
        dueDates.add("");
    }

    /**
     * Adds a new task to this to-do list.
     *
     * @param Description Description of the new task being added
     * @param date string due date for the task
     */
    public void addTask(String Description, String date) {

        if (Description == null)
            return;

        // An initial to-do list sets the value of the first task to "" and
        // the first isComplete to "true" so that it will be stored in firebase.
        // We must clear those initial values.
        if (tasks.size() == 1 && tasks.contains(firstTask)){
            tasks.clear();
            isComplete.clear();
            dueDates.clear();
        }

        // Add description and set completion to false
        tasks.add(Description);
        isComplete.add(false);
        dueDates.add(date);
    }

    /**
     * Removes a task from the list
     *
     * @param task The task to be removed
     */
    public void removeTask(String task){
        // Remove the task if it exists
        for (int i = 0; i < tasks.size(); i++) {
            if (task.equals(tasks.get(i))) {
                tasks.remove(i);
                isComplete.remove(i);
                dueDates.remove(i);

                // For purpose of firebase storage
                if (tasks.isEmpty()) {
                    tasks.add(firstTask);
                    isComplete.add(true);
                    dueDates.add("");
                }

                break;
            }
        }
    }

    /**
     * Gets the name of this to-do list
     *
     * @return The name of the list
     */
    public String getListName() {
        return listName;
    }

    /**
     * Gets the list of all task on this to-do list
     *
     * @return The list of tasks for this to-do list
     */
    public List<String> getTasks() {
        return tasks;
    }

    /**
     * Change/set the name of this to-do list
     *
     * @param listName Name of the to-do list
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     * Set the list of tasks. This should usually only be used for
     * Firebase Database updates.
     *
     * @param task The list of tasks to associate with this to-do list
     */
    public void setTasks(List<String> task) {
        this.tasks = task;
    }

    public List<Boolean> getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(List<Boolean> isComplete) {
        this.isComplete = isComplete;
    }

    public void setTaskComplete(String task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (task.equals(tasks.get(i))) {
                Boolean b = !isComplete.get(i);
                isComplete.set(i, b);
                i = tasks.size();
            }
        }
    }

    /**
     * Removes all of the completed tasks from the to-do list
     */
    public void removeCompleted() {
        // Loop through and remove completed tasks
        for (int i = 0; i < isComplete.size(); i++) {
            if (isComplete.get(i)) {
                isComplete.remove(i);
                tasks.remove(i);
                dueDates.remove(i);
                i--; // Since we've removed items we need the index to go back one
            }
        }
    }

    /**
     * Getter for due date list
     * @return the list of string due dates
     */
    public List<String> getDueDates() {
        return dueDates;
    }

    /**
     * Sets the string of due dates
     * @param dueDates list of string due dates
     */
    public void setDueDates(List<String> dueDates) {
        this.dueDates = dueDates;
    }
}
