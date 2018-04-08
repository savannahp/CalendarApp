package teamproject.savannahpyle.calendarapp;

import android.renderscript.RenderScript;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

/**
 * Created by paulland on 2/28/18.
 *
 * This class represents a Task that will be in a
 * to-do list ({@link ToDoList})
 *
 * @author Paul Land and Savannah Pyle
 */
public class Task {
    public String description;
//    private static final int[] PRIORITY_TYPES = {0, 1, 2};
//    private int priority = 0;
    public ToDoList list;
//    private List<Task> subtasks; // TODO: Make subtask class so that only one level of subtask is possible
    public Boolean isComplete = false;
    public GregorianCalendar dueDate;

    /**
     * Default constructor with no args required for Firebase Database
     */
    public Task() {
        // Purposely empty
    }

    /**
     * Non-default constructor that
     *
     * @param list A TaskList
     * @param description String description of the new task
     */
    public Task(ToDoList list, String description) {
        this.list = list;
        this.description = description;
    }

    /**
     * Set the priority level
     *
     * @param priority int representing priority level
     */
//    public void setPriority(int priority) {
//
//        // Set the priority level if we have a valid priority type
//        for(int i : PRIORITY_TYPES) {
//            if (i == priority) {
//                this.priority = priority;
//                break;
//            }
//        }
//    }

    /**
     * Set the description.
     *
     * @param description String description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set whether task is complete.
     *
     * @param isComplete Bool for whether task is complete
     */
    public void setComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Set the calendar due date.
     *
     * @param calendar GregorianCalendar set to the due date of task
     */
    public void setDueDate(GregorianCalendar calendar) {
        this.dueDate = calendar;
    }

    /**
     * Set the TaskList this task belongs to.
     *
     * @param list A TaskList
     */
    public void setList(ToDoList list) {
        this.list = list;
    }

    /**
     * Get the current priority
     *
     * @return int current priority level
     */
//    public int getPriority() {
//        return priority;
//    }

    /**
     * Get an array of the possible int priority levels
     *
     * @return array of int priority levels
     */
//    public int[] getPriorityTypes() {
//        return PRIORITY_TYPES;
//    }

    /**
     * Get the TaskList this Task belongs to.
     *
     * @return TaskList for this Task
     */
    public ToDoList getList() {
        return list;
    }

    /**
     * Get the description of this task
     *
     * @return String description of the Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the Name of the TaskList this Task is owned by
     *
     * @return String TaskList name
     */
    public String getListName() {
        return list.getListName();
    }

    /**
     * Get whether task is complete
     *
     * @return Bool whether task is complete
     */
    public Boolean isComplete() {
        return isComplete;
    }

    /**
     * Get the due date of this Task
     * @return GregorianCalendar due date of Task
     */
    public GregorianCalendar getDueDate() {
        return dueDate;
    }


    // TODO: Do we need this?
    public void update() {

    }
}
