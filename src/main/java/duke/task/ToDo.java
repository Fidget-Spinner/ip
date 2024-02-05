package duke.task;

/**
 * A Todo Task.
 */
public class ToDo extends Task {
    /**
     * Todo task contains *what* to todo.
     *
     * @param todo what it is the user needs to do.
     */
    public ToDo(String todo) {
        super();
        this.event = todo;
    }

    @Override
    public String toString() {
        String isDone = this.done ? "X" : " ";
        return String.format("[T][%s] %s", isDone, this.event);
    }
}
