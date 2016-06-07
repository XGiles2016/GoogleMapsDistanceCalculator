/**
 * Created by Xavier on 6/7/16.
 */
public class Location {

    String locationName;
    int distanceInMinutes;

    public Location(String locationName, int distance){
        this.locationName = locationName;
        distance = distanceInMinutes;
    }

    public void setDistanceInMinutes(int distance){
        this.distanceInMinutes = distance;
    }

    public int getDistanceInMinutes(){
        return distanceInMinutes;
    }

    public String getLocationName(){
        return locationName;
    }
}
