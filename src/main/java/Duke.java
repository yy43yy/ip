import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point of the Duke chatbot application.
 * It initializes the user interface, storage, and task list,
 * and manages the main execution loop.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object and initializes its components.
     * Loads saved tasks from storage if available.
     * If loading fails, starts with an empty task list.
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("data", "yy.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.errorMessage("Oops " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot and processes user commands until exit.
     */

    public void run() {
        ui.welcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                isExit = Parser.parse(input, tasks, ui, storage);
            } catch (DukeException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * Starts the Duke application.
     *
     * @param args Command line arguments.
     */
    
    public static void main(String[] args) {
        new Duke().run();
    }
}