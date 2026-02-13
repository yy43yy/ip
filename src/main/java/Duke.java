import java.util.Scanner;

public class Duke {
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] tasks, int taskCount){
        for(int i=0;i<taskCount;i++){
            System.out.println((i+1)+". "+tasks[i]);
        }
    }

    public static void handleMark(String input,Task[] tasks){
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks[taskNumber-1].markDone();

        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskNumber-1]);
        printLine();
    }

    public static void handleUnmark(String input,Task[] tasks){
        String[] parts = input.split(" ");
        int taskNumber = Integer.parseInt(parts[1]);
        tasks[taskNumber-1].unmarkDone();

        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[taskNumber-1]);
        printLine();
    }

    public static int handleTodo(String input,Task[] tasks,int taskCount ) throws DukeException{
        input = input.substring(4).trim();
        if(input.isEmpty()){
            throw new DukeException("The description of a todo cannot be empty.");
        }
        tasks[taskCount] = new Todos(input);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printLine();
        return taskCount;
    }
    public static int handleDeadline(String input,Task[] tasks,int taskCount) throws DukeException{
        input = input.substring((8)).trim();
        if(input.isEmpty()){
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] parts = input.split("/by");
        tasks[taskCount] = new Deadlines(parts[0],parts[1]);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printLine();
        return taskCount;
    }
    public static int handleEvent(String input,Task[] tasks,int taskCount)throws DukeException{
        input = input.substring((5)).trim();
        if(input.isEmpty()){
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] parts = input.split("/");
        tasks[taskCount]= new Events(parts[0],parts[1].substring(4),parts[2].substring(2));
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount].toString());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printLine();
        return taskCount;
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;
        printLine();
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine();
            String [] words = input.split(" ",2);
            String command = words[0];
            try {
                switch (command) {
                    case "list":
                        printList(tasks, taskCount);
                        break;
                    case "mark":
                        handleMark(input, tasks);
                        break;
                    case "unmark":
                        handleUnmark(input, tasks);
                        break;
                    case "todo":
                        try {
                            taskCount = handleTodo(input, tasks, taskCount);
                        } catch (DukeException e) {
                            printLine();
                            System.out.println(" OOPS!!! " + e.getMessage());
                            printLine();
                        }
                        break;
                    case "deadline":
                        try {
                            taskCount = handleDeadline(input, tasks, taskCount);
                        } catch (DukeException e) {
                            printLine();
                            System.out.println(" OOPS!!! " + e.getMessage());
                            printLine();
                        }
                        break;
                    case "event":
                        try {
                            taskCount = handleEvent(input, tasks, taskCount);
                        } catch (DukeException e) {
                            printLine();
                            System.out.println(" OOPS!!! " + e.getMessage());
                            printLine();
                        }
                        break;
                    case "bye":
                        printLine();
                        System.out.println(" Bye. Hope to see you again soon!");
                        printLine();
                        return;
                    default: throw new DukeException("I'm sorry, but I don't know what that means :-(");

                }
            }catch(DukeException e){
                    printLine();
                    System.out.println(" OOPS!!! " + e.getMessage());
                    printLine();
                }

        }
    }
}