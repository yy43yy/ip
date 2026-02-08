import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine();

            if (input.contains("list")) {
                for (int j = 0; j < taskCount; j++) {
                    System.out.println((j+1) + ". " + tasks[j]);
                }
            } else if (input.contains("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");

            } else if (input.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if(input.startsWith("mark ")){
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]);
                tasks[taskNumber-1].markDone();


                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber-1]);
                System.out.println("____________________________________________________________");


            } else if(input.startsWith("unmark ")){
                String[] parts = input.split(" ");
                int taskNumberblah = Integer.parseInt(parts[1]);
                tasks[taskNumber-1].unmarkDone();

                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber-1]);
                System.out.println("____________________________________________________________");


            }else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");

            }

        }
    }
}