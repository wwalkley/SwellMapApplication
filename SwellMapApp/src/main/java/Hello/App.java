package Hello;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import Hello.Controller;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Controller contoller = new Controller();
        contoller.runApp();
    }
}
