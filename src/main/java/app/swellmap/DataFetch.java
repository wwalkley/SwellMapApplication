package app.swellmap;

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
        JSONObject locationsFileJson = getFileAsJSONObject();
        JSONArray regionsJsonArray = getRegions(locationsFileJson);
        regionsJsonArray.forEach(r -> {
            String regionName = getRegionName((JSONObject) r);
            JSONArray regionLocationsArray = getLocations((JSONObject) r);
            final ArrayList<String> regionLocations = new ArrayList<>();
            regionLocationsArray.forEach(l -> {
                regionLocations.add(l.toString());
            });
            Region regionObject = new Region(regionName, regionLocations);
            regions.add(regionObject);
        });
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
        Object obj = jsonParser.parse(new FileReader(ConfigHandler.getInstance().getLocations()));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

}
