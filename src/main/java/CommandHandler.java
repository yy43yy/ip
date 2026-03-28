import java.util.ArrayList;

/**
 * Handles execution of supported user commands.
 */
public class CommandHandler {

    /**
     * Parses and validates a task number argument.
     *
     * @param argument The task number entered by the user.
     * @param tasks The current task list.
     * @return The zero-based index of the task.
     * @throws DukeException If the task number is missing, not an integer, or out of range.
     */
    private static int parseTaskNumber(String argument, TaskList tasks) throws DukeException {
        if (argument.isEmpty()) {
            throw new DukeException("Please specify the task number.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer.");
        }

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DukeException("Task number is out of range.");
        }

        return taskNumber - 1;
    }

    /**
     * Adds a task to the task list, saves the updated list, and shows a confirmation message.
     *
     * @param task The task to add.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     */
    private static void addTask(Task task, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        ui.taskAddedMessage(task, tasks.size());
    }

    /**
     * Marks the specified task as done.
     *
     * @param arguments The task number entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the task number is invalid.
     */
    public static void handleMark(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseTaskNumber(arguments, tasks);
        tasks.markTask(index);
        storage.writeToFile(tasks.getTasks());
        ui.taskMarkedMessage(tasks.get(index));
    }

    /**
     * Marks the specified task as not done.
     *
     * @param arguments The task number entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the task number is invalid.
     */
    public static void handleUnmark(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseTaskNumber(arguments, tasks);
        tasks.unmarkTask(index);
        storage.writeToFile(tasks.getTasks());
        ui.taskUnmarkedMessage(tasks.get(index));
    }

    /**
     * Creates and adds a todo task.
     *
     * @param arguments The description of the todo task.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the description is empty.
     */
    public static void handleTodo(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task task = new Todos(arguments);
        addTask(task, tasks, ui, storage);
    }

    /**
     * Creates and adds a deadline task.
     *
     * Expected format: {@code deadline <description> /by <date>}
     *
     * @param arguments The description and deadline details entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the input format is invalid.
     */
    public static void handleDeadline(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // Split into description and deadline using the /by delimiter.
        String[] parts = arguments.split("\\s+/by\\s+", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Use the format: deadline <description> /by <date>");
        }

        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        addTask(task, tasks, ui, storage);
    }

    /**
     * Creates and adds an event task.
     *
     * Expected format: {@code event <description> /from <start> /to <end>}
     *
     * @param arguments The description and event timing entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the input format is invalid.
     */
    public static void handleEvent(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new DukeException("Use the format: event <description> /from <start> /to <end>");
        }

        // Extract the description, start time, and end time from the input.
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("Use the format: event <description> /from <start> /to <end>");
        }

        Task task = new Events(description, from, to);
        addTask(task, tasks, ui, storage);
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param arguments The task number entered by the user.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @param storage The storage object used to save task changes.
     * @throws DukeException If the task number is invalid.
     */
    public static void handleDelete(String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseTaskNumber(arguments, tasks);
        Task removed = tasks.removeTask(index);
        storage.writeToFile(tasks.getTasks());
        ui.taskDeletedMessage(removed, tasks.size());
    }

    /**
     * Finds and displays tasks whose descriptions match the given keyword.
     *
     * @param arguments The keyword to search for.
     * @param tasks The current task list.
     * @param ui The user interface used to display messages.
     * @throws DukeException If the keyword is empty.
     */
    public static void handleFind(String arguments, TaskList tasks, Ui ui) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("The keyword for find cannot be empty.");
        }

        ArrayList<Task> matchingTasks = tasks.findTasks(arguments);
        ui.showMatchingTasks(matchingTasks);
    }
}