import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in  = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm yy");
        System.out.println("What can I do for you?");

        while(true) {
            line = in.nextLine();

            if (line.contains("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("list");
                System.out.println("____________________________________________________________");

            } else if (line.contains("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");

            } else if (line.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else{
                System.out.println("____________________________________________________________");
                System.out.println(" Invalid, retry!");
                System.out.println("____________________________________________________________");

            }
        }

    }
}
