package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListTD implements Comparable<ListTD> {
    private String description;
    private List<TaskTD> tasks;

    // Constructor: construct a list for tasks to do.
    public ListTD(String description) {
        this.description = description;
        tasks = new ArrayList<>();
    }

    // Getters and Setters:
    public String getDescription() {
        return description;
    }

    public List<TaskTD> getTasks() {
        return tasks;
    }

    public void changeDescriptionTo(String description) {
        this.description = description;
    }

    // EFFECTS: return the number of TaskTD in this ListTD
    public int size() {
        return tasks.size();
    }

    // EFFECTS: if this ListTD contains given TaskTD, return true;
    //          OW return false.
    public boolean contains(TaskTD task) {
        return tasks.contains(task);
    }

    public void add(TaskTD taskTD) {
        tasks.add(taskTD);
    }

    public void remove(TaskTD taskTD) {
        tasks.remove(taskTD);
    }

    // EFFECTS: return the number of TaskTDs with given priority in this ListTD.
    public int countTaskTDWith(TaskTD.Priority priority) {
        int count = 0;
        for (TaskTD t : tasks) {
            if (priority.equals(t.getPriority())) {
                count++;
            }
        }
        return count;
    }

    // Re-define equality for ListTD:
    // for any ListTDs have same description, consider them as the same ListTD.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListTD listTD = (ListTD) o;
        return description.equals(listTD.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    // Default comparision behaviour:
    // Compare ListTD by its description alphabetically.
    @Override
    public int compareTo(ListTD that) {
        return this.description.compareTo(that.description);
    }
}
