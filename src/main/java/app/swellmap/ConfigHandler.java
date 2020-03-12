package app.swellmap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Ross Glenn <mwskuzzy@gmail.com>
 */
public class ConfigHandler {

    private static ConfigHandler configHandler;
    private static String configPath = null;
    private final JSONObject config;

    private ConfigHandler() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(configPath));
        this.config = (JSONObject) obj;
    }
    
    public static void setConfigPath(String configPath) {
        ConfigHandler.configPath = configPath;
    }

    public static ConfigHandler getInstance() throws FileNotFoundException, IOException, ParseException {
        if (configHandler == null) {
            configHandler = new ConfigHandler();
        }
        return configHandler;
    }

    private JSONObject getJSONObject(String key) {
        return (JSONObject) this.config.get(key);
    }

    public String getName() {
        return config.get("name").toString();
    }

    public String getDbPath() {
        return getJSONObject("db").get("path").toString();
    }

    public String getLocations() {
        return config.get("locationsPath").toString();
    }
}
