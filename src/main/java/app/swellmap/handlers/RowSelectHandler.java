package app.swellmap.handlers;

import app.swellmap.helpers.DateHelper;
import java.util.ArrayList;

public class RowSelectHandler {

    private ArrayList<String> rows;
    private ArrayList<String> hours;
    private DateHelper dateFetcher;

    public RowSelectHandler() {
        this.rows = new ArrayList<String>();
        this.hours = new ArrayList<String>();
        this.dateFetcher = new DateHelper();
    }

    public ArrayList<String> rowsSelector() {
        String dateTimeNow = dateFetcher.getTodaysDateForRowSelector();
        whichHoursToSelect(dateTimeNow);
        for (String hour : this.hours) {
            this.rows.add(getAbbreviation(dateTimeNow, hour));
        }
        return this.rows;
    }

    private int getHour(String date) {
        return Integer.parseInt(date.substring(4, 6));
    }

    private String getAbbreviation(String date, String hour) {
        String day = getDayAbbreviation(date);
        return day + " " + hour + "h";
    }

    private String getDayAbbreviation(String dateTime) {
        return dateTime.substring(0, 3);
    }

    public void whichHoursToSelect(String date) {
        int unroundedHour = getHour(date);
        if (unroundedHour >= 4) {
            this.hours.add("4");
            if (unroundedHour >= 10) {
                this.hours.add("10");
                if (unroundedHour >= 16) {
                    this.hours.add("16");
                    if (unroundedHour >= 22) {
                        this.hours.add("22");
                    }
                }
            }
        }
    }
}