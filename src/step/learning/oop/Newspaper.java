package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Newspaper
        extends Literature
        implements Printable, Periodic
{
    private Date date;         // date of publishing
    private Calendar calendar; //

    static private final SimpleDateFormat dateParser =
            new SimpleDateFormat("yyyy-MM-dd");         // to parse from setters
    static private final SimpleDateFormat datePrinterShort =
            new SimpleDateFormat("dd.MM");              // to show
    static private final SimpleDateFormat datePrinter =
            new SimpleDateFormat("dd.MM.yyyy");         // to show in short format

    public Date getDate() {
        return date;
    }

    public Newspaper setDate(String date) throws ParseException {
        this.date = dateParser.parse(date);
        this.calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        return this; // return for chaining
    }

    @Override
    public Newspaper setTitle(String title) {
        return (Newspaper) super.setTitle(title);
    }

    // shows data in console
    @Override
    public void print() {

        // get instance of calendar
        Calendar now = Calendar.getInstance();

        String dateString = null;

        if (now.equals(calendar))
            dateString = "Today";

        // create date output string
        dateString = now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                    && dateString == null
                ? datePrinterShort.format(this.getDate()) // if this is current year
                : datePrinter.format(this.getDate());     // if this is not current year

        // shows data
        System.out.printf("Newspaper. Date: %s. Title: %s%n",
                dateString, super.getTitle());
    }
}
