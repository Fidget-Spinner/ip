package duke.task;

import java.time.LocalDateTime;

/**
 * A deadline with an event and a due date.
 */
public class Deadline extends Task {

    private final LocalDateTime dueBy;

    /**
     * Constructor.
     *
     * @param event The event itself.
     * @param dueBy When the event is due by.
     */
    public Deadline(String event, LocalDateTime dueBy) {
        super();
        this.event = event;
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        String isDone = this.done ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", isDone, this.event, this.dateFormat(this.dueBy));
    }

}
