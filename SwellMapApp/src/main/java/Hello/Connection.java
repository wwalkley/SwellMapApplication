package Hello;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Connection {

    public Document connect(String location){
        try {           
            Document document= Jsoup.connect("http://www.swellmap.com" + location).get();
            return document;
            }
        catch (IOException e){
            e.getMessage();
            return null;
        }

    }
}