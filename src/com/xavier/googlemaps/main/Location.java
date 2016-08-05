/**
 * Created by Xavier on 6/7/16.
 */

package com.xavier.googlemaps.main;
public class Location {

    String locationName;
    int distanceInMinutes;

    public Location(String locationName){
        this.locationName = locationName;
        this.distanceInMinutes = 0;
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
