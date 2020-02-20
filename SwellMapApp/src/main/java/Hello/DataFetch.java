package Hello;

import java.io.FileNotFoundException;
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
        JSONObject file = getFileAsJSONObject();
        JSONArray regionsJsonArray = getRegions(file);
        for (int i = 0; i < regionsJsonArray.size(); i++) {
            ArrayList<String> locations = new ArrayList<String>();
            JSONObject region = (JSONObject) regionsJsonArray.get(i);
            String regionName = getRegionName(region);
            JSONArray locationsJsonArray = getLocations(region);
            for (int j = 0; j < locationsJsonArray.size(); j++) {
                String locationName = (String) locationsJsonArray.get(j);
                locations.add(locationName);
            }
            Region regionObject = new Region(regionName, locations);
            regions.add(regionObject);
        }
        return regions;

    }

    private JSONArray getLocations(JSONObject region) {
        JSONArray locationsJsonArray = (JSONArray) region.get("Locations");
        return locationsJsonArray;
    }

    private String getRegionName(JSONObject region) {
        String regionName = (String) region.get("Name");
        return regionName;
    }

    private JSONArray getRegions(JSONObject file) {
        JSONArray regionsJsonArray = (JSONArray) file.get("Regions");
        return regionsJsonArray;
    }

    private JSONObject getFileAsJSONObject() throws IOException, ParseException, FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("SwellMapApp/locations.json"));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

}
