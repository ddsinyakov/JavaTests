package step.learning.oop;

public class Journal extends Literature {
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
        System.out.printf("Book. Number: %s. Title: %s%n",
                this.number, super.getTitle());
    }
}
