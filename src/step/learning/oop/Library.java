package step.learning.oop;

import java.util.ArrayList;
import java.util.Date;
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
            literature.print();
        }
    }

    public void Run() {
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
        add(new Newspaper()
                .setDate(new Date())
                .setTitle("Learning C#")
        );

        // show all items in library
        printFunds();
    }
}
