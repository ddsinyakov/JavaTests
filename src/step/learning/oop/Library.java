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
            if(literature instanceof Printable){
                ((Printable) literature).print(); // show data if literature is instance of Printable interface
            } else {
                System.out.println("Unprintable: " + literature.getTitle());
            }
        }
    }

    // show Periodic items
    public void printPeriodic() {
        for(Literature literature : funds) {
            if (literature instanceof Periodic && literature instanceof Printable)
                ((Printable) literature).print(); // show item only if it is Periodic and Printable
        }
    }

    // show nonPeriodic items
    public void printNonPeriodic() {
        for(Literature literature : funds) {
            if (!(literature instanceof Periodic) && literature instanceof Printable)
                ((Printable) literature).print(); // show item only if it is not Periodic and Printable
        }
    }

    public void run() {
        // add new book to our library
        add(new Book()
                .setAuthor("Dima")
                .setTitle("Learning Java")
        );

        // add new journal to our library
        add(new Journal()
                .setNumber(23)
                .setTitle("Learning C#")
        );

        // add new newspaper to our library
        try {
            add(new Newspaper()
                    .setDate("2022-09-22")
                    .setTitle("Learning C#")
            );
            add(new Newspaper()
                    .setDate("2021-09-22")
                    .setTitle("Learning Java")
            );
            add(new Newspaper()
                    .setDate("2020-09-22")
                    .setTitle("Learning JS")
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
