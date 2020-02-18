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
            for (String location : locations) {
                Document document = this.connection.connect(location);
                for (String row : rows) {
                    Elements element = document.getElementsByClass(row);
                    String text = element.text();
                    String [] textArray = text.split(" ");
                    System.out.println(Arrays.toString(textArray));
                    
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}