package step.learning.oop;

public class Book extends Literature {
    private String author;

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this; // return for chaining
    }

    // shows data in console
    @Override
    public void print() {
        System.out.printf("Book. Author: %s. Title: %s%n",
                this.author, super.getTitle());
    }
}
