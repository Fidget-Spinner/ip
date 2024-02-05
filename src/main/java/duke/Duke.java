package duke;

import duke.command.Command;
import duke.exceptions.ChatException;

/**
 * The Chatbot.
 */
public class Duke {

    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructor.
     */
    public Duke() {
        this.ui = new Ui();
        // Inspired and referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        try {
            this.taskList = new TaskList(Storage.load());
        } catch (ChatException e) {
            this.ui.printError(e);
            this.taskList = new TaskList();
        }

    }

    /**
     * The main read-eval-respond loop.
     */
    public void loop() {
        String output = null;
        boolean isExit = false;
        // Inspired and referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                String result = c.execute(this.taskList, ui, null);
                ui.respondUser(result);
                ui.showLine();
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.printError(e);
            } finally {
                taskList.save();
            }
        }
    }

    public void end() {
        this.ui.close();
    }

}
