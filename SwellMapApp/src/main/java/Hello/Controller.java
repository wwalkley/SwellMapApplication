package Hello;

import org.jsoup.nodes.Document;

import Hello.Connection;

public class Controller {
    private Connection connection;
    private Document document;
    private DataFetch dataFetcher;


    public Controller(){
        this.connection = new Connection();
        this.dataFetcher = new DataFetch();
    }

    

    public void runApp(){
        this.document = connection.connect();
        if (this.document != null){
            this.dataFetcher.fetchLocations(document);
        }
    }


}