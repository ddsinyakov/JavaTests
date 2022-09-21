package step.learning.oop;

import java.util.Date;

public class Newspaper extends Literature {
    private Date date;

    public Date getDate() {
        return date;
    }

    public Newspaper setDate(Date date) {
        this.date = date;
        return this; // return for chaining
    }

    // shows data in console
    @Override
    public void print() {
        System.out.printf("Book. Date: %s. Title: %s%n",
                this.date, super.getTitle());
    }
}
