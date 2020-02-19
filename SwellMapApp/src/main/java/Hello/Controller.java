package Hello;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Controller {
    private Connection connection;
    private DataFetch dataFetcher;
    private DateFetcher dateFetcher;
    private RowsSelector rowsSelector;
    ArrayList<String> locations;
    ArrayList<String> rows;

    public Controller() {
        this.connection = new Connection();
        this.dataFetcher = new DataFetch();
        this.dateFetcher = new DateFetcher();
        this.rowsSelector = new RowsSelector();
        this.locations = new ArrayList<String>();
        this.rows = new ArrayList<String>();
    }

    public void runApp() {
        try {
            String dateTimeNow = dateFetcher.getTodaysDateTime();
            this.rows = this.rowsSelector.rowsSelector(dateTimeNow);
            this.locations = dataFetcher.fetchLocations();
            if (rows.isEmpty()) {
                System.out.println("No data to collect");
            } else {
                for (String location : locations) {
                    Document document = this.connection.connect(location);
                    for (String row : rows) {
                        Elements element = document.getElementsByClass(row);
                        Forecast forecast = extractForecast(element, location);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Forecast extractForecast(Elements element, String location) {
        String text = element.text();
        String[] textArray = text.split(" ");
        System.out.println(Arrays.toString(textArray));
        Forecast forecast = new Forecast();
        forecast.setLocation(location);
        forecast.setTime(textArray[0]);
        forecast.setRating(textArray[1]);
        forecast.setSeaHeight(textArray[2]);
        forecast.setSwellHeight(textArray[3]);
        forecast.setChopHeight(textArray[4]);
        forecast.setPeriod(textArray[5]);
        forecast.setSwellDirection(textArray[6]);
        forecast.setSeaDirection(textArray[7]);
        forecast.setWindInformation(textArray[8]);
        forecast.setGust(textArray[9]);
        System.out.println(forecast);
        return forecast;
    }

}