package Hello;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DateFetcher {

    public String getTodaysDateTime() {
        DateFormat dateFormat = setDateFormat();
        Date date = new Date();
        return dateFormat.format(date);
    }

    private DateFormat setDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("EEE HH:mm:ss");
        return dateFormat;
    }
}