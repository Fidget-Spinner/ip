package duke;

import duke.exceptions.ChatException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A list of tasks.
 */
public class TaskList implements Iterable<Task> {


    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfMessages) {
        this.tasks = listOfMessages;
    }

    /**
     * Return a task at a certain index in the list.
     * @param idx Zero-based index in task list.
     * @return The task at said index.
     */
    public Task get(int idx) {
        try {
            return this.tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Not a valid task number.");
        }
    }

    /**
     * Remove a task at a certain index.
     * @param idx Zero-based index of task to remove from the list.
     */
    public void remove(int idx) {
        try {
            this.tasks.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("Not a valid task number.");
        }
    }

    /**
     * Add a task to the end of the list.
     * @param task the task to add.
     */
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
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    /**
     * Save our tasks to persistence.
     */
    public void save() {
        Storage.save(this.tasks);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TaskList)) {
            return false;
        }
        return this.tasks.equals(((TaskList)other).tasks);
    }

}
