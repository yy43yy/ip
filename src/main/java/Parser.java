import java.util.ArrayList;

/**
 * Parses user input and executes the corresponding command.
 */

public class Parser {

    /**
     * Parses the user's input and performs the requested action.
     *
     * @param input The full command entered by the user.
     * @param tasks The task list to be modified or displayed.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @return True if the command is "bye", otherwise false.
     * @throws DukeException If the command is invalid or cannot be processed.
     */

    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            ui.printList(tasks);
            break;
        case "mark":
            handleMark(input, tasks, ui, storage);
            break;
        case "unmark":
            handleUnmark(input, tasks, ui, storage);
            break;
        case "todo":
            handleTodo(input, tasks, ui, storage);
            break;
        case "deadline":
            handleDeadline(input, tasks, ui, storage);
            break;
        case "event":
            handleEvent(input, tasks, ui, storage);
            break;
        case "delete":
            handleDelete(input, tasks, ui, storage);
            break;
        case "bye":
            ui.goodbyeMessage();
            return true;
        case "find":
            handleFind(input, tasks, ui);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return false;
    }

    /**
     * Marks the specified task as done.
     *
     * @param input The user's input command.
     * @param tasks The task list containing the task.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the task number is invalid.
     */

    public static void handleMark(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.markTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskMarkedMessage(tasks.get(taskNumber - 1));
    }

    /**
     * Marks the specified task as not done.
     *
     * @param input The user's input command.
     * @param tasks The task list containing the task.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the task number is invalid.
     */

    public static void handleUnmark(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.unmarkTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskUnmarkedMessage(tasks.get(taskNumber - 1));
    }

    /**
     * Creates and adds a todo task to the task list.
     *
     * @param input The user's input command.
     * @param tasks The task list to add the task to.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the todo description is empty.
     */

    public static void handleTodo(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task task = new Todos(description);
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        ui.taskAddedMessage(task, tasks.size());
    }

    /**
     * Creates and adds a deadline task to the task list.
     *
     * @param input The user's input command.
     * @param tasks The task list to add the task to.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the deadline description or date is invalid.
     */

    public static void handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String description = input.substring(8).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] parts = description.split("/by");
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        ui.taskAddedMessage(task, tasks.size());
    }

    /**
     * Creates and adds an event task to the task list.
     *
     * @param input The user's input command.
     * @param tasks The task list to add the task to.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the event description or timing is invalid.
     */

    public static void handleEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String[] parts = description.split("/");
        Task task = new Events(parts[0].trim(), parts[1].substring(4).trim(), parts[2].substring(2).trim());
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        ui.taskAddedMessage(task, tasks.size());
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param input The user's input command.
     * @param tasks The task list containing the task.
     * @param ui The user interface used for displaying messages.
     * @param storage The storage object used for saving tasks.
     * @throws DukeException If the task number is invalid.
     */

    public static void handleDelete(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);

        Task removed = tasks.removeTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskDeletedMessage(removed, tasks.size());
    }

    /**
     * Finds and displays tasks whose descriptions contain the given keyword.
     *
     * @param input The user's input command.
     * @param tasks The task list to search in.
     * @param ui The user interface used for displaying messages.
     * @throws DukeException If the search keyword is empty.
     */

    public static void handleFind(String input, TaskList tasks, Ui ui) throws DukeException {
        String keyword = input.substring(4).trim();

        if (keyword.isEmpty()) {
            throw new DukeException("The keyword for find cannot be empty.");
        }

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}