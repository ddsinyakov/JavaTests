package step.learning.oop;

// abstract class for items in library
public abstract class Literature {
    private String title;

    public String getTitle() {
        return title;
    }

    public Literature setTitle(String title) {
        this.title = title;
        return this; // return for chaining
    }
}
