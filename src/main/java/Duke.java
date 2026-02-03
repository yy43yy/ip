import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        Task[] addList = new Task[100];
        int i = 0;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");

        while (true) {
            line = in.nextLine();

            if (line.contains("list")) {
                for (int j = 0; j < i; j++) {
                    System.out.println((j+1) + ". " + addList[j]);
                }
            } else if (line.contains("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");

            } else if (line.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if(line.startsWith("mark ")){
                String[] parts = line.split(" ");
                int numb = Integer.parseInt(parts[1]);
                addList[numb-1].markDone();


                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(addList[numb-1]);
                System.out.println("____________________________________________________________");


            } else if(line.startsWith("unmark ")){
                String[] parts = line.split(" ");
                int numb = Integer.parseInt(parts[1]);
                addList[numb-1].unmarkDone();

                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(addList[numb-1]);
                System.out.println("____________________________________________________________");


            }else {
                addList[i] = new Task(line);
                i++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + line);
                System.out.println("____________________________________________________________");

            }

        }
    }
}