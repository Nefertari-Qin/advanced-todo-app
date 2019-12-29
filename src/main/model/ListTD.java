package model;

import java.util.ArrayList;
import java.util.List;

public class ListTD {
    private String description;
    private List<TaskTD> tasks;

    // Constructor: construct a list for tasks to do.
    public ListTD(String description) {
        this.description = description;
        tasks = new ArrayList<>();
    }


}
