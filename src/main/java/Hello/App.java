package Hello;

import java.io.IOException;
import Hello.ControlApp;

import org.json.simple.parser.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        ControlApp contoller = new ControlApp();
        contoller.runApp();
        System.out.println("running");
    }
}
