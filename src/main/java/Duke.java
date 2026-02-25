import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void handleMark(String input, ArrayList<Task> tasks,Storage storage) throws DukeException {
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.get(taskNumber-1).markDone();

        storage.writeToFile(tasks);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber-1));
        printLine();
    }

    public static void handleUnmark(String input, ArrayList<Task> tasks,Storage storage) throws  DukeException{
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.get(taskNumber-1).unmarkDone();

        storage.writeToFile(tasks);
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber-1));
        printLine();
    }

    public static void handleTodo(String input, ArrayList<Task> tasks,Storage storage) throws DukeException {
        input = input.substring(4).trim();
        if (input.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks.add(new Todos(input)) ;

        storage.writeToFile(tasks);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size()-1).toString());

        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printLine();

    }

    public static void handleDeadline(String input, ArrayList<Task> tasks,Storage storage) throws DukeException {
        input = input.substring((8)).trim();
        if (input.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] parts = input.split("/by");
        tasks.add(new Deadline(parts[0], parts[1]));

        storage.writeToFile(tasks);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size()-1).toString());

        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printLine();

    }

    public static void handleEvent(String input, ArrayList<Task> tasks,Storage storage) throws DukeException {
        input = input.substring((5)).trim();
        if (input.isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] parts = input.split("/");
        tasks.add(new Events(parts[0], parts[1].substring(4), parts[2].substring(2)))  ;

        storage.writeToFile(tasks);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size()-1).toString());

        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printLine();

    }

    public static void handleDelete(String input, ArrayList<Task> tasks){
        String [] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);

        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskNumber-1).toString());

        tasks.remove(taskNumber-1);

        System.out.println("Now you have " + tasks.size() + " tasks in the list");

    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        Storage storage = new Storage("data","yy.txt");

        ArrayList<Task> tasks;

        try{
            tasks=storage.load();
        }catch (DukeException e){
            tasks=new ArrayList<>();
            printLine();
            System.out.println("Oops" +e.getMessage());
            printLine();
        }

        printLine();
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0];
            try {
                switch (command) {
                case "list":
                    printList(tasks);
                    break;
                case "mark":
                    handleMark(input, tasks,storage);
                    break;
                case "unmark":
                    handleUnmark(input, tasks,storage);
                    break;
                case "todo":
                    try {
                        handleTodo(input,tasks,storage);
                    } catch (DukeException e) {
                        printLine();
                        System.out.println(" OOPS!!! " + e.getMessage());
                        printLine();
                    }
                    break;
                case "deadline":
                    try {
                        handleDeadline(input, tasks,storage);
                    } catch (DukeException e) {
                        printLine();
                        System.out.println(" OOPS!!! " + e.getMessage());
                        printLine();
                    }
                    break;
                case "event":
                    try {
                        handleEvent(input, tasks,storage);
                    } catch (DukeException e) {
                        printLine();
                        System.out.println(" OOPS!!! " + e.getMessage());
                        printLine();
                    }
                    break;
                case "delete":
                    handleDelete(input,tasks);
                    break;
                case "bye":
                    printLine();
                    System.out.println(" Bye. Hope to see you again soon!");
                    printLine();
                    return;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(" OOPS!!! " + e.getMessage());
                printLine();
            }

        }
    }
}