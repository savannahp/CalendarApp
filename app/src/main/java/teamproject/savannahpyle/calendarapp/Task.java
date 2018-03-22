package teamproject.savannahpyle.calendarapp;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

/**
 * Created by paulland on 2/28/18.
 * @author Paul Land
 */

public class Task {

    private String description;
    private String listName;
    private List<Task> subtasks; // TODO: Make so only one level of subtasks possible
    private boolean isComplete = false;
    private GregorianCalendar dueDate;

    public Task() {
        // Default constructor with no args required for Firebase Database
    }

    // Non-default constructor
    public Task(String listName, String description) {
        this.description = description;
        this.listName = listName;
    }


    // Setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setListName(String listName) {
        this.listName = listName;
    }
    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setDueDate(GregorianCalendar calendar) {
        this.dueDate = calendar;
    }

    // Getters
    public String getDescription() {

        return description;
    }
    public String getListName() {
        return listName;
    }
    public List<Task> getSubtasks() {
        return subtasks;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public GregorianCalendar getDueDate() {
        return dueDate;
    }


    // TODO: What are these below???
    public void setNumDaysCompleted(Integer num) {

    }

    public void update() {

    }
}
