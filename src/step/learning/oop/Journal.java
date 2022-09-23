package step.learning.oop;

import java.io.Serializable;

public class Journal
        extends Literature
        implements Printable, Periodic, Serializable {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public Journal setNumber(Integer number) {
        this.number = number;
        return this; // return for chaining
    }

    // shows data in console
    @Override
    public void print() {
        System.out.printf("Journal. Number: %s. Title: %s%n",
                this.number, super.getTitle());
    }
}
