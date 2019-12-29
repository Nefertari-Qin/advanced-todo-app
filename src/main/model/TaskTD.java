package model;

import java.time.LocalDate;
import java.util.Objects;

// Represent a task to do in a application with description, status, creation date, completion date and priority.
public class TaskTD {
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

    // Represent priority of a task in a application.
    public enum Priority {
        N("None"),
        I("!"),
        II("!!"),
        III("!!!");

        private final String pLabel;

        Priority(String pLabel) {
            this.pLabel = pLabel;
        }

        public String getPriorityLabel() {
            return pLabel;
        }
    }
}
