package teamproject.savannahpyle.calendarapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savannahpyle on 3/21/18.
 */

public class TaskList {


    private String listName;
    private List<Task> tasks = new ArrayList<>();


    // constructors

    public TaskList() {
    }

    public TaskList(String listName, List<Task> task) {
        this.listName = listName;
        this.tasks = task;
    }

    public TaskList(String listName) {
        this.listName = listName;
    }

    // add and remove

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){

        if(tasks.contains(tasks)) {
            tasks.remove(task);
        }
    }

    // getters and setters

    public String getListName() {
        return listName;
    }

    public List<Task> getTask() {
        return tasks;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setTask(List<Task> task) {
        this.tasks = task;
    }
}
