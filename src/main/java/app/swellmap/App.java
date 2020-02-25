package app.swellmap;

import java.io.IOException;
import app.swellmap.ControlApp;

import org.json.simple.parser.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        ControlApp contoller = new ControlApp();
        contoller.runApp();
        System.out.println("running");
    }
}
