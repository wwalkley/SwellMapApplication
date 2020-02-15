package Hello;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DataFetch {

    public void fetchLocations(Document document) {
        Element container = document.getElementById("sitelist");
        System.out.println(container);
    }
    
}