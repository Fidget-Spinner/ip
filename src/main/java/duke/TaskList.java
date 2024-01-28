package duke;

import duke.exceptions.ChatException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {


    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfMessages) {
        this.tasks = listOfMessages;
    }

    public Task get(int idx) {
        try {
            return this.tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Not a valid task number.");
        }
    }

    public void remove(int idx) {
        try {
            this.tasks.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Not a valid task number.");
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public String prelude() {
        return "Got it. I've added this task:\n";
    }
    public String trailer() {
        return String.format("Now you have %d tasks in the list.\n", this.tasks.size());
    }
    public String standardize(Task msg) {
        return this.prelude() + msg + "\n" + this.trailer();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int idx = 1;
        for (Task msg : tasks) {
            res.append(String.format("%d. %s\n", idx, msg));
            idx++;
        }
        return res.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    public void save() {
        Storage.save(this.tasks.toArray());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TaskList)) {
            return false;
        }
        return this.tasks.equals(((TaskList)other).tasks);
    }

}
