import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cities";
        Connection conn = DriverManager.getConnection(url,"XGiles", "Dope1129");
        GoogleMap map;
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        String location, origin, city;
        ArrayList<String> matchingNames= new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        //Begin program
        System.out.println("Please enter your starting location");
        origin = scan.nextLine();
        map = new GoogleMap(origin);

        //loops until user says no more locations to be entered
        while (choice == 1) {
            System.out.println("Please enter your destination");
            location = scan.nextLine();
            System.out.println("Please enter your city");
            city = scan.nextLine();
            //database validation
            pst = conn.prepareStatement("SELECT * FROM cities WHERE city = ? AND state_code = ?");
            pst.setString(1,location);
            pst.setString(2,city);
            rs = pst.executeQuery();
            //can be deleted after
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            //end of delete

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
            System.out.println(map.getOrigin() + " to " + x.getLocationName());
        }

        map.getDistances(places);
    }


}
