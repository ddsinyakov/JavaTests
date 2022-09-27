package step.learning.files;

import java.util.HashMap;
import java.util.Scanner;

public class FileSystem {

    // separator for menu interface
    private static final String separator =
            "===============================================================================";

    private final HashMap<String, Processable> processes; // list of commands that are available in application
    private final FileObserver observer; // file observer object that save and share current directory

    public FileSystem() {
        observer = new FileObserver("."); // build observer with initial directory at project folder

        processes = new HashMap<>(); // build list of commands and associate them with processors
        processes.put("cd", new CdProcessor(observer));
        processes.put("cat", new CatProcessor(observer));
        processes.put("ls", new LsProcessor(observer));
    }

    /**
     * Menu method to run application
     */
    public void run() {

        Scanner scan = new Scanner(System.in);

        String action;
        do {
            System.out.println(separator);

            System.out.println(observer.getCurrentDirectory().getPath());
            System.out.println("cd __ - go to directory");
            System.out.println("cd .. - go back");
            System.out.println("cat _ - open file");
            System.out.println("ls    - show all files");
            System.out.println("exit  - exit");
            System.out.print("Input command -> ");

            action = scan.nextLine(); // ask for command to run

            String[] commands = action.split(" ");

            if (commands.length >= 1) {
                Processable process = processes.get(commands[0]); // get method associated with command
                if (process != null) {
                    System.out.println(separator);
                    process.runProcess(commands.length > 1 ? commands[1] : "");
                }
                else System.out.println("Unknown command");
            }
            else if (!action.equals("exit"))
                System.out.println("Wrong typo of command");
        }
        while (!action.equals("exit"));

    }

}
