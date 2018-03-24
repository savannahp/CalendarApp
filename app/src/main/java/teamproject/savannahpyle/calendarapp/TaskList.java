package teamproject.savannahpyle.calendarapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savannahpyle on 3/21/18.
 *
 * Task List is a container for {@link Task} objects. This container
 * is basically a to-do list.
 *
 * @author Paul Land and Savannah Pyle
 */
public class TaskList {

    private String listName;
    private List<String> tasksAsStrings = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public List<String> getTasksAsStrings() {
        return tasksAsStrings;
    }

    public void setTasksAsStrings(List<String> tasksAsStrings) {
        this.tasksAsStrings = tasksAsStrings;
    }

    /**
     * Default constructor with no arguments for Firebase use.
     */
    public TaskList() {
        // Purposely blank for use with Firebase Database
    }

    /**
     * Non-default constructor that takes a name and the list of tasks
     *
     * @param listName Name of the list
     * @param task A list of tasks to add to this list
     */
    public TaskList(String listName, List<Task> task) {
        this.listName = listName;
        this.tasks = task;

        for (Task t : this.tasks) {
            tasksAsStrings.add(t.getDescription());
        }
    }

    /**
     * Another non-default constructor that takes only the name of the list
     *
     * @param listName The name of the new list
     */
    public TaskList(String listName) {
        this.listName = listName;
    }

    /**
     * Adds a new task to this to-do list.
     *
     * @param Description Description of the new task being added
     */
    public void addTask(String Description) {
        Task task = new Task(this, Description);
        tasks.add(task);
        tasksAsStrings.add(Description);
    }

    /**
     * Removes a task from the list
     *
     * @param task The task to be removed
     */
    public void removeTask(Task task){
        // Remove the task if it exists
        if(tasks.contains(task)) {
            tasks.remove(task);
            tasksAsStrings.remove(task.getDescription());
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
    public List<Task> getTasks() {
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
    public void setTasks(List<Task> task) {
        this.tasks = task;
    }
}
