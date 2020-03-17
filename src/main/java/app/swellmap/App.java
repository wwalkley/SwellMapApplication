package app.swellmap;

import app.swellmap.handlers.ConfigHandler;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class App {

    /**
     * @param args Expected argument to configuration file E.g. --conf c:\swellmapapp\config.json
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        validateArgs(args);
        ConfigHandler.setConfigPath(args[1]);
        ControlApp contoller = new ControlApp();
        try {
            contoller.runApp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IOException("Incorrect argument length. Expecting 2 arguments");
        }
        if (!args[0].toString().equals("--conf")) {
            throw new IOException("Invalid arguments passed. Expecting --conf and got " + args[0]);
        }
    }
}
