
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
        try {
            URL url = new URL(generateURL());
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
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
                //Log.d("error", "error1");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public String generateURL(){
        String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=" + APIkey;
        return URL;
    }

    public String getOrigin(){
        return defaultLocation;
    }
        }

