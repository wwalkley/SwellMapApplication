package app.swellmap;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSOUPConnection {

    public Document connect(String location) {
        try {
            Document document = Jsoup.connect("http://www.swellmap.com/boating/new-zealand/" + location).get();
            return document;
        } catch (IOException e) {
            e.getMessage();
            return null;
        }

    }
}