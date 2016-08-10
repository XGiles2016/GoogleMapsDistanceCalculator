package com.xavier.googlemaps.test;
import com.mysql.fabric.xmlrpc.base.Data;
import com.xavier.googlemaps.main.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Xavier on 8/5/16.
 */

public class GoogleMapTest extends TestCase{

    Database db;

    public void setup() throws SQLException{
        db = Database.createInstance();
    }

    public void tearDown(){
        db = null;
    }

    @Test
    public void testNeedsFormatting() throws Exception {
        String noFormatting = "Worcester";
        String needsFormatting = "New York";
        GoogleMap mapNF = new GoogleMap(noFormatting);

        assertFalse(mapNF.needsFormatting(noFormatting));
        assertTrue(mapNF.needsFormatting(needsFormatting));

    }
    @Test
    public void testFormattedString(){
        String unformatted = "New York City";
        String formatted = "New+York+City";
        GoogleMap map = new GoogleMap("Boston");
        assertEquals(map.formattedString(unformatted), formatted);
    }
    @Test
    public void testGenerateURL(){
        String destination1 = "Boston,MA";
        String destination2 = "New York City,NY";
        String Url1 = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Worcester,MA&destinations=" + destination1 + "&key=AIzaSyAaGxpB0qJmuNH-UVemiVMxfHP2nVqTTV8";
        String Url2 = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Worcester,MA&destinations=New+York+City,NY&key=AIzaSyAaGxpB0qJmuNH-UVemiVMxfHP2nVqTTV8";
        GoogleMap map = new GoogleMap("Worcester,MA");

        assertEquals(map.generateURL(destination1), Url1);
        assertEquals(map.generateURL(destination2), Url2);
    }
    @Test
    public void testSingletonDBcreator()throws SQLException{
        Database db1 = Database.createInstance();
        assertEquals(null, Database.createInstance());
        db1 = null;
    }
    @Test
    public void testDatabaseCreation() throws SQLException{
        assertNotNull(Database.createInstance());
    }
    
}