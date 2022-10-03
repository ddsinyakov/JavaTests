package step.learning.oop;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.Scanner;

@DemoClass(priority = 1)
public class LibraryMenu {

    private static final String separator =
            "===============================================================================";
    @EntryPoint
    public void run() {
        Scanner scan = new Scanner(System.in);                  // scanner for console input
        Library lib = new Library().setFileName("save.ser");    // library object
        lib.deserializeFunds();                                 // get literature from file via deserialization

        int answer;

        do { // menu cycle
            System.out.println("Choose your option");
            System.out.println("1. Show all funds");
            System.out.println("2. Show periodic funds");
            System.out.println("3. Show non periodic funds");
            System.out.println("0. exit");

            answer = scan.nextInt(); // get answer for menu option
            scan.nextLine();         // clear console buffer

            System.out.println(separator);
            switch (answer) { // switch correct option
                case 1 -> lib.printFunds();
                case 2 -> lib.printPeriodic();
                case 3 -> lib.printNonPeriodic();
            }
            System.out.println(separator);

        } while (answer != 0);

        System.out.println("Goodbye!");

        lib.serializeFunds(); // serialize literature back to file
    }
}
