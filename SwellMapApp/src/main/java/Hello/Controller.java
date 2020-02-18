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
                        String text = element.text();
                        String[] textArray = text.split(" ");
                        System.out.println(Arrays.toString(textArray));
                        Forecast forecast = extractForecast(textArray, location);
                        System.out.println(forecast.getLocation());
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Forecast extractForecast(String[] textArray, String location) {
        Forecast forecast = new Forecast();
        forecast.setLocation(location);
        forecast.setTime(textArray[0]);
        forecast.setRating(Integer.parseInt(textArray[1]));
        forecast.setSeaHeight(Double.parseDouble(textArray[2]));
        forecast.setSwellHeight(Double.parseDouble(textArray[3]));
        forecast.setChopHeight(Double.parseDouble(textArray[4]));
        forecast.setPeriod(Integer.parseInt(textArray[5]));
        forecast.setSwellDirection(textArray[6]);
        forecast.setSeaDirection(textArray[7]);
        forecast.setWindSpeed(textArray[8]);
        forecast.setGust(Integer.parseInt(textArray[9]));
        System.out.println(forecast);
        return forecast;
    }

}