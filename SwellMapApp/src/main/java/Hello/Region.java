package Hello;

import java.util.ArrayList;

public class Region {

    private String name;
    private ArrayList<String> locations;

    public Region(String name, ArrayList<String> locations) {
        this.locations = locations;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<String> getLocations() {
        return this.locations;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName() + this.getLocations();
    }
}