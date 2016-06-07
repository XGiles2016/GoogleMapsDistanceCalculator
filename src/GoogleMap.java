import java.util.ArrayList;

/**
 * Created by Xavier on 6/7/16.
 */
public class GoogleMap {

    static String APIkey = "AIzaSyAaGxpB0qJmuNH-UVemiVMxfHP2nVqTTV8";
    String defaultLocation;
    ArrayList<Location> destinations = new ArrayList<>();

    public GoogleMap(String defaultLocation){
        this.defaultLocation = defaultLocation;
    }

    public void addLocation(String location){
        Location city = new Location(location, 0);
        destinations.add(city);
    }

    public ArrayList getDestinations(){
        return destinations;
    }
}
