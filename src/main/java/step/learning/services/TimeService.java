package step.learning.services;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class TimeService {

    static private final SimpleDateFormat datePrinter =
            new SimpleDateFormat("dd.MM.yyyy");

    static private final SimpleDateFormat timePrinter =
            new SimpleDateFormat("hh.mm.ss");

    public String getDate() {
        return datePrinter.format(new Date());
    }

    public String getTime() {
        return timePrinter.format(new Date());
    }
}
