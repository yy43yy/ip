import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Duke().run();
    }
}