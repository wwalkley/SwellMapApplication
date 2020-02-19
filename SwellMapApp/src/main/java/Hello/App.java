package Hello;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Controller contoller = new Controller();
        contoller.runApp();
    }
}
