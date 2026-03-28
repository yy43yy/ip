/**
 * Parses raw user input and dispatches it to the appropriate command handler.
 */
public class Parser {

    /**
     * Parses the user's input and executes the corresponding command.
     *
     * @param input The full command entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @return True if the user entered the exit command, otherwise false.
     * @throws DukeException If the input is invalid or the command cannot be processed.
     */
    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String trimmedInput = input.trim();

        if (trimmedInput.isEmpty()) {
            throw new DukeException("Please enter a command.");
        }

        // Split input into command word and remaining arguments.
        String[] words = trimmedInput.split("\\s+", 2);
        String command = words[0];
        String arguments = words.length > 1 ? words[1].trim() : "";

        switch (command) {
        case "list":
            ui.printList(tasks);
            return false;
        case "mark":
            CommandHandler.handleMark(arguments, tasks, ui, storage);
            return false;
        case "unmark":
            CommandHandler.handleUnmark(arguments, tasks, ui, storage);
            return false;
        case "todo":
            CommandHandler.handleTodo(arguments, tasks, ui, storage);
            return false;
        case "deadline":
            CommandHandler.handleDeadline(arguments, tasks, ui, storage);
            return false;
        case "event":
            CommandHandler.handleEvent(arguments, tasks, ui, storage);
            return false;
        case "delete":
            CommandHandler.handleDelete(arguments, tasks, ui, storage);
            return false;
        case "find":
            CommandHandler.handleFind(arguments, tasks, ui);
            return false;
        case "bye":
            ui.goodbyeMessage();
            return true;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}