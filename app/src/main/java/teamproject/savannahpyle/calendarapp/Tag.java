package teamproject.savannahpyle.calendarapp;

import java.util.List;

/**
 * Created by paulland on 3/16/18.
 *
 */

public class Tag {

    private String tagName;
    private List<Task> tasks;

    public Tag() {
        // Default constructor with no args required for Firebase Database
    }

    public Tag(String tagName, List<Task> tasks) {
        this.tagName = tagName;
        this.tasks = tasks;
    }

    public void addTask(Task task) {

        tasks.add(task);

    }

    public String getTagName() {
        return tagName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
