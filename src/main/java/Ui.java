import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user, including reading input
 * and displaying messages to the console.
 */
public class Ui {
    private final Scanner in;

    /**
     * Creates an Ui object and initializes the scanner for user input.
     */
    public Ui(){
        in = new Scanner(System.in);
    }

    /**
     * Prints a divider line to separate sections of output.
     */

    public void printLine(){
        System.out.println("-------------------------------------");
    }

    /**
     * Displays the welcome message shown when the chatbot starts.
     */

    public void welcomeMessage(){
        printLine();
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");
        printLine();
    }
    /**
     * Displays the goodbye message shown when the chatbot ends.
     */

    public void goodbyeMessage(){
        printLine();
        System.out.println("Bye! See you next time!");
        printLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */

    public void errorMessage(String message){
        printLine();
        System.out.println("OOPSSS " + message);
        printLine();
    }
    /**
     * Displays all tasks currently in the task list.
     *
     * @param tasks The task list to be displayed.
     */

    public void printList(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }


    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks in the list.
     */

    public void taskAddedMessage(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        printLine();
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated number of tasks in the list.
     */

    public void taskDeletedMessage(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        printLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked.
     */

    public void taskMarkedMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */

    public void taskUnmarkedMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    /**
     * Displays the list of tasks whose descriptions match the given keyword.
     *
     * @param matchingTasks The list of matching tasks.
     */

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        printLine();
    }

    /**
     * Reads and returns the next command entered by the user.
     *
     * @return The user's input command.
     */

    public String readCommand() {
        return in.nextLine();
    }



}
