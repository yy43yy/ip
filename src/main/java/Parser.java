public class Parser {
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
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return false;
    }

    public static void handleMark(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.markTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskMarkedMessage(tasks.get(taskNumber - 1));
    }

    public static void handleUnmark(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.unmarkTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskUnmarkedMessage(tasks.get(taskNumber - 1));
    }

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

    public static void handleDelete(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);

        Task removed = tasks.removeTask(taskNumber - 1);
        storage.writeToFile(tasks.getTasks());
        ui.taskDeletedMessage(removed, tasks.size());
    }
}