package model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

// Represent a task to do in a application with description, status, creation date, completion date and priority.
public class TaskTD implements Comparable<TaskTD> {
    private String description;
    private boolean isDone;
    private LocalDate creation;
    private LocalDate completion;
    private Priority priority;

    public TaskTD(String description, LocalDate dueDate, Priority priority) {
        this.description = description;
        isDone = false;
        creation = LocalDate.now();
        completion = dueDate;
        this.priority = priority;
    }

    // Getters and Setters:
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public LocalDate getCompletion() {
        return completion;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void setDueDate(LocalDate completion) {
        this.completion = completion;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    // EFFECTS: if date now is not before due date, return true;
    //          OW return false.
    public boolean isDue() {
        return !LocalDate.now().isBefore(completion);
    }

    // Re-define equality for TaskTD:
    // for any TaskTDs have same description, creation date completion date and priority level,
    // consider them as the same TaskTD.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTD taskTD = (TaskTD) o;
        return description.equals(taskTD.description)
                && creation.equals(taskTD.creation)
                && completion.equals(taskTD.completion)
                && priority == taskTD.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, creation, completion, priority);
    }

    // Default comparision behaviour:
    // Compare TaskTD by its description alphabetically.
    @Override
    public int compareTo(TaskTD that) {
        return this.description.compareTo(that.description);
    }

    // Extra Comparator:
    // Compare TaskTD first by its priority level; if have same pLevel, then use default comparision behaviour.
    class PriorityComparator implements Comparator<TaskTD> {

        @Override
        public int compare(TaskTD one, TaskTD two) {
            if (one.priority.level == two.priority.level) {
                return one.description.compareTo(two.description);
            } else if (one.priority.level < two.priority.level) {
                return -1;
            }
            return 1;
        }
    }

    // Extra Comparator:
    // Compare TaskTD first by its due date; if have same due date, then use default comparision behaviour.
    class DueDateComparator implements Comparator<TaskTD> {

        @Override
        public int compare(TaskTD one, TaskTD two) {
            if (one.completion.isEqual(two.completion)) {
                return one.description.compareTo(two.description);
            }
            return one.completion.compareTo(two.completion);
        }
    }

    // Extra Comparator:
    // Compare TaskTD first by its creation date; if have same creation date, then use default comparision behaviour.
    class CreationComparator implements Comparator<TaskTD> {

        @Override
        public int compare(TaskTD one, TaskTD two) {
            if (one.creation.isEqual(two.creation)) {
                return one.creation.compareTo(two.creation);
            }
            return one.creation.compareTo(two.creation);
        }
    }

    // Represent priority of a task in a application.
    public enum Priority {
        N("None", 0),
        I("!", 1),
        II("!!", 2),
        III("!!!", 3);

        private final String pLabel;
        private final int level;

        Priority(String pLabel, int level) {
            this.pLabel = pLabel;
            this.level = level;
        }

        public String getPriorityLabel() {
            return pLabel;
        }

        public int getLevel() {
            return level;
        }
    }
}
