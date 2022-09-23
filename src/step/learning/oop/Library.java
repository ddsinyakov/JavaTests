package step.learning.oop;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// class that describes library
public class Library {

    private final List<Literature> funds; // list of items

    public Library() {
        funds = new ArrayList<>(); // initialize list with ArrayList
    }

    // add new item
    public void add(Literature literature) {
        funds.add(literature);
    }

    // show all items
    public  void printFunds() {
        for(Literature literature : funds) {
            print(literature);
        }
    }

    // show Periodic items
    public void printPeriodic() {
        for(Literature literature : funds) {
            if (literature instanceof Periodic) // show item only if it is Periodic and Printable
                print(literature);
        }
    }

    // show nonPeriodic items
    public void printNonPeriodic() {
        for(Literature literature : funds) {
            if (!(literature instanceof Periodic)) // show item only if it is not Periodic and Printable
                print(literature);
        }
    }

    private void print(Literature literature) {
        if(literature instanceof Printable){
            ((Printable) literature).print(); // show data if literature is instance of Printable interface
        } else {
            System.out.println("Unprintable: " + literature.getTitle());
        }
    }

    public void run() {
        // add new Book
        add(new Book()
                .setAuthor("Dima")
                .setTitle("Learning Java")
        );

        // add new Journal
        add(new Journal()
                .setNumber(23)
                .setTitle("Learning C#")
        );

        // add new Hologram
        add(new Hologram()
                .setTitle("Leia asks Obi-Wan for help")
        );

        // add new Poster
        add(new Poster()
                .setTitle("Poster 1")
        );

        add(new Poster()
                .setTitle("Poster 2")
        );

        // add new Newspaper
        try {
            add(new Newspaper()
                    .setDate("2022-09-22")
                    .setTitle("Daily Bugle")
            );
            add(new Newspaper()
                    .setDate("2021-07-15")
                    .setTitle("Daily Planet")
            );
            add(new Newspaper()
                    .setDate("2020-12-20")
                    .setTitle("New York Times")
            );
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }


        // show all items in library
        printFunds();
        printPeriodic();
        printNonPeriodic();
    }
}
