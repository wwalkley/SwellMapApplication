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
    
    private JSONObject getDb() {
        return getJSONObject("db");
    }

    public String getDbPath() {
        return getDb().get("path").toString();
    }

    public String getLocations() {
        return config.get("locationsPath").toString();
    }
    
    private JSONObject getHttp() {
        return getJSONObject("http");
    }
    
    private JSONObject getProxy() {
        return (JSONObject) getHttp().get("proxy");
    }
    
    public boolean getProxyEnabled() {
        return Boolean.parseBoolean(getProxy().get("enabled").toString());
    }
    
    public String getProxyHost() {
        return getProxy().get("host").toString();
    }
    
    public int getProxyPort() {
        return Integer.parseInt(getProxy().get("port").toString());
    }
}
