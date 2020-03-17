package app.swellmap.handlers;

import app.swellmap.models.Region;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocationsHandler {

    public ArrayList<Region> getRegions() throws IOException, ParseException {
        ArrayList<Region> regions = new ArrayList<Region>();
        JSONObject locationsFileJson = getFileAsJSONObject();
        JSONArray regionsJsonArray = getRegionsArray(locationsFileJson);
        regionsJsonArray.forEach(r -> {
            String regionName = getRegionName((JSONObject) r);
            JSONArray regionLocationsArray = getLocationsArray((JSONObject) r);
            final ArrayList<String> regionLocations = new ArrayList<>();
            regionLocationsArray.forEach(l -> {
                regionLocations.add(l.toString());
            });
            Region regionObject = new Region(regionName, regionLocations);
            regions.add(regionObject);
        });
        return regions;
    }

    private JSONArray getLocationsArray(JSONObject region) {
        JSONArray locationsJsonArray = (JSONArray) region.get("Locations");
        return locationsJsonArray;
    }

    private String getRegionName(JSONObject region) {
        String regionName = (String) region.get("Name");
        return regionName;
    }

    private JSONArray getRegionsArray(JSONObject file) {
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
