package Hello;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Hello.Connection;
import Hello.DateFetcher;

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
            System.out.println(rows);
            this.locations = dataFetcher.fetchLocations();
            for (String location : locations) {
                Document document = this.connection.connect(location);
                for (String row : rows) {
                    System.out.println(row);
                    Elements element = document.getElementsByClass(row);
                    System.out.println(element);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}