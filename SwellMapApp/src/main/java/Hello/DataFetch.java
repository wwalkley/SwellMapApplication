package Hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFetch {

    public ArrayList<String> fetchLocations() throws FileNotFoundException {
        ArrayList<String> locations = new ArrayList<String>();
        File file = new File("SwellMapApp/locations.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String location = scanner.nextLine();
            locations.add(location);
        }
        scanner.close();
        return locations;
    }
    
}