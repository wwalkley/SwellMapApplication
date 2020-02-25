package app.swellmap;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ControlApp {
    private JSOUPConnection connection;
    private DataFetch dataFetcher;
    private DateFetcher dateFetcher;
    private RowsSelector rowsSelector;
    private Database database;
    ArrayList<String> locations;
    ArrayList<String> rows;
    ArrayList<Region> regions;

    public ControlApp() {
        this.connection = new JSOUPConnection();
        this.dataFetcher = new DataFetch();
        this.dateFetcher = new DateFetcher();
        this.rowsSelector = new RowsSelector();
        this.database = new Database();
        this.regions = new ArrayList<Region>();
        this.locations = new ArrayList<String>();
        this.rows = new ArrayList<String>();
    }

    public void runApp() throws IOException, ParseException {
        this.rows = this.rowsSelector.rowsSelector();
        System.out.println("running");
        this.regions = this.dataFetcher.fetchLocations();
        System.out.println("running");
        if (rows.isEmpty()) {
            System.out.println("No data to collect");
        } else {
            for (Region region : this.regions) {
                for (String location : region.getLocations()) {
                    Document document = this.connection.connect(location);
                    for (String row : rows) {
                        Elements element = document.getElementsByClass(row);
                        String summary = getSummary(element);
                        String regionName = region.getName();
                        String date = this.dateFetcher.getTodaysDate();
                        Forecast forecast = extractForecast(element, location, summary, regionName, date);
                        this.database.insert(forecast);
                    }
                }
            }
        }
    }

    private String getSummary(Elements element) {
        Element summaryElement = element.select(".wx-summary").first();
        String summary = summaryElement.attr("title");
        return summary;
    }

    private Forecast extractForecast(Elements element, String location, String summary, String region, String date) {
        String text = element.text();
        String[] textArray = text.split(" ");
        Forecast forecast = new Forecast();
        forecast.setRegion(region);
        forecast.setLocation(location);
        forecast.setDate(date);
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
        forecast.setSummary(summary);
        return forecast;
    }

}