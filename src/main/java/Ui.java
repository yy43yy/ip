import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;

    public Ui(){
        in = new Scanner(System.in);
    }

    public void printLine(){
        System.out.println("-------------------------------------");
    }

    public void welcomeMessage(){
        printLine();
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void goodbyeMessage(){
        printLine();
        System.out.println("Bye! See you next time!");
        printLine();
    }

    public void errorMessage(String message){
        printLine();
        System.out.println("OOPSSS " + message);
        printLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }

    public void taskAddedMessage(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        printLine();
    }

    public void taskDeletedMessage(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        printLine();
    }

    public void taskMarkedMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    public void taskUnmarkedMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        printLine();
    }
    public String readCommand() {
        return in.nextLine();
    }



}
