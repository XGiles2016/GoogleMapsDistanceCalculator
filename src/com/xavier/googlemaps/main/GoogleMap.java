package com.xavier.googlemaps.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        Location city = new Location(location);
        destinations.add(city);
    }

    public ArrayList getDestinations(){
        return destinations;
    }


    protected String getDistances(ArrayList<Location> destinations){
        String json = "";
        for(Location x : destinations) {
            try {
                URL url = new URL(generateURL(x.getLocationName()));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int statuscode = connection.getResponseCode();
                if (statuscode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = br.readLine();
                    }
                    json = sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public String getDistanceString(ArrayList<Location > destinations){
        String json = getDistances(destinations);
        String distance =json.substring(json.indexOf("text") + 9, json.indexOf("text") + 21).trim();
        return "Total Distance: " + distance.substring(0, distance.length() - 2);
    }

    /**
     * Could have used .split method to put into String array and find "text" values
     * Could put getTimeString method and getDistanceString method all into one method for simplicity
     * Coming in a future iteration when I have the time/patience to do it.
     *
     * The getDistanceString and getTimeString need to be tested, will test when
     * I refactor these two methods into one.
     */

    public String getTimeString(ArrayList<Location > destinations){
        String json = getDistances(destinations);
        String time = json.substring(json.indexOf("duration"), json.indexOf("status")).trim();
        String time2 = time.substring(time.indexOf("text") + 9, time.indexOf("value") - 2).trim();
        return "Total Time: " + time2.substring(0,time2.length() - 2);
    }

    public void print(ArrayList<Location> destinations){
        System.out.println(getDistances(destinations));
        System.out.println(getDistanceString(destinations));
        System.out.println(getTimeString(destinations));
    }


    public static boolean needsFormatting(String location){
        return (location.contains(" "));
    }

    public static String formattedString(String location){
        StringBuilder sb = new StringBuilder(location);
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == ' '){
                sb.setCharAt(i, '+');
            }
        }
        return sb.toString();

    }

    public String generateURL(String destination){
        //have to add input default location and destination into the URL string;
        if(needsFormatting(destination)){
            destination = formattedString(destination);
        }

        String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + defaultLocation + "&destinations=" + destination + "&key=" + APIkey;
        return URL;
    }

    public String getOrigin(){
        return defaultLocation;
    }
        }

