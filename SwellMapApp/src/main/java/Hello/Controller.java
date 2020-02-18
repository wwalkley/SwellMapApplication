package Hello;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Hello.Connection;

public class Controller {
    private Connection connection;
    private DataFetch dataFetcher;
    ArrayList<String> locations;

    public Controller() {
        this.connection = new Connection();
        this.dataFetcher = new DataFetch();
        this.locations = new ArrayList<String>();
    }

    public void runApp() {
        try {
            this.locations = dataFetcher.fetchLocations();
            for (String location : locations){
                Document document = this.connection.connect(location);
                Element table = document.getElementById("tables");
                System.out.println(table);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}