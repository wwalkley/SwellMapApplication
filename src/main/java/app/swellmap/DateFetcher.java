package app.swellmap;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFetcher {

    public String getTodaysDateForRowSelector() {
        DateFormat dateFormat = setDateFormatForRowSelector();
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getTodaysDate(){
        DateFormat dateFormat = setDateFormat();
        Date date = new Date();
        return dateFormat.format(date);
    }

    private DateFormat setDateFormatForRowSelector() {
        DateFormat dateFormat = new SimpleDateFormat("EEE HH:mm:ss");
        return dateFormat;
    }

    private DateFormat setDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat;
    }
}