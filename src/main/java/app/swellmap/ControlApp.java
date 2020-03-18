package app.swellmap;

import app.swellmap.handlers.RowSelectHandler;
import app.swellmap.dao.ForecastDAO;
import app.swellmap.models.Forecast;
import app.swellmap.models.Region;
import app.swellmap.helpers.HttpHelper;
import app.swellmap.helpers.DateHelper;
import app.swellmap.handlers.LocationsHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ControlApp {

    final private LocationsHandler dataFetcher;
    final private RowSelectHandler rowsSelector;
    ArrayList<String> locations;
    ArrayList<String> rows;
    ArrayList<Region> regions;

    public ControlApp() {
        this.dataFetcher = new LocationsHandler();
        this.rowsSelector = new RowSelectHandler();
        this.regions = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public void runApp() throws IOException, ParseException, InterruptedException {
        this.rows = this.rowsSelector.rowsSelector();
        this.regions = this.dataFetcher.getRegions();
        if (rows.isEmpty()) {
            System.out.println("No data to collect");
        } else {
            int threadCount = totalLocations(regions);
            final CountDownLatch latch = new CountDownLatch(threadCount);
            for (Region region : this.regions) {
                for (final String location : region.getLocations()) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Document document = (new HttpHelper()).get(location);
                                for (String row : rows) {
                                    Elements element = document.getElementsByClass(row);
                                    String summary = getSummary(element);
                                    String regionName = region.getName();
                                    String date = (new DateHelper()).getTodaysDate();
                                    Forecast forecast = extractForecast(element, location, summary, regionName, date);
                                    ForecastDAO dao = new ForecastDAO();
                                    dao.delete(forecast);
                                    dao.insert(forecast);
                                    latch.countDown();
                                }
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                }
            }
            latch.await(30, TimeUnit.SECONDS);
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
    
    private int totalLocations(ArrayList<Region> regions) {
        int cnt = 0;
        for(Region region : regions) {
            cnt += region.getLocations().size();
        }
        return cnt;
    }

}
