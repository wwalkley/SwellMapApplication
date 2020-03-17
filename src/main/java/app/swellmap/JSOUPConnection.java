package app.swellmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSOUPConnection {

    public Document get(String location) throws ParseException, FileNotFoundException, IOException {
        ConfigHandler conf = ConfigHandler.getInstance();
        if (conf.getProxyEnabled()) {
            final String proxyHost = conf.getProxyHost();
            final int proxyPort = conf.getProxyPort();
            return Jsoup.connect("http://www.swellmap.com/boating/new-zealand/" + location)
                    .proxy(proxyHost, proxyPort)
                    .userAgent(RandomUserAgent.getRandomUserAgent())
                    .get();
        } else {
            return Jsoup.connect("http://www.swellmap.com/boating/new-zealand/" + location)
                    .userAgent(RandomUserAgent.getRandomUserAgent())
                    .get();
        }
    }
}
