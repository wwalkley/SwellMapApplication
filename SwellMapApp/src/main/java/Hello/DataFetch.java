package Hello;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataFetch {

    public ArrayList<Region> fetchLocations() throws IOException, ParseException {
        ArrayList<Region> regions = new ArrayList<Region>();
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("SwellMapApp/locations.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray regionsJsonArray = (JSONArray) jsonObject.get("Regions");
        for (int i = 0; i < regionsJsonArray.size(); i++) {
            ArrayList<String> locations = new ArrayList<String>();
            JSONObject region = (JSONObject) regionsJsonArray.get(i);
            String regionName = (String) region.get("Name");
            JSONArray locationsJsonArray = (JSONArray) region.get("Locations");
            for (int j = 0; j < locationsJsonArray.size(); j++) {
                String locationName = (String) locationsJsonArray.get(j);
                locations.add(locationName);
            }
            Region regionObject = new Region(regionName, locations);
            regions.add(regionObject);
        }
        return regions;

    }

}
