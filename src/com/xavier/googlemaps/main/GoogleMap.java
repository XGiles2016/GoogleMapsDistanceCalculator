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
                    String json = sb.toString();
                    System.out.println(json);


                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
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

