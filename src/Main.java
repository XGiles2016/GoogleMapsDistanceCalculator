import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GoogleMap map = new GoogleMap("Boston");
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        String location;

        //loops until user says no more locations to be entered
        while (choice == 1) {
            System.out.println("Please enter the cities you want the distance to");
            location = scan.nextLine();
            map.addLocation(location);
            System.out.println("Do you have any more cities you want the distance too?");
            String myChoice = scan.nextLine();
            if(myChoice.equalsIgnoreCase("no")){
                choice = 0;
            }
            else{
                choice = 1;
            }
        }
        ArrayList<Location> places = map.getDestinations();
        for(Location x: places){
            System.out.println(x.getLocationName());
        }
    }
}
