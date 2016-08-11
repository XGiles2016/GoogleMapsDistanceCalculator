package com.xavier.googlemaps.main;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        GoogleMap map;
        Database db = Database.createInstance();
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        String originCity,originState,destinationCity, destinationState, location, distance;

        //Begin program. Get Origin City
        System.out.println("Please enter your starting city");
        originCity = scan.nextLine();
        System.out.println("Please enter your origin state");
        originState = scan.nextLine();
        map = new GoogleMap(originCity + "," + originState);

        //loops until user says no more locations to be entered
        while (choice == 1) {
            System.out.println("Please enter your city");
            destinationCity = scan.nextLine();
            System.out.println("Please enter the state abbreviation for your destination");
            destinationState = scan.nextLine();
            //database validation
            location = db.getQuery(destinationCity,destinationState);
            if(db.validateInDatabase(destinationCity,destinationState)) {
                map.addLocation(location);
            }
            System.out.println("Do you have any more cities you want the distance too?");
            String myChoice = scan.nextLine();
            if(myChoice.equalsIgnoreCase("no")){
                choice = 0;
                db.closeConnection();
                }
            else{
                    choice = 1;
                }
            }
        ArrayList<Location> places = map.getDestinations();
        for(Location x: places){
            System.out.println(map.getOrigin() + " to " + x.getLocationName());
        }
        map.print(places);
        db.closeConnection();
    }


}
