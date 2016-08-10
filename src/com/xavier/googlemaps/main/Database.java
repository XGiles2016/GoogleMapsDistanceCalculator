package com.xavier.googlemaps.main;

import java.sql.*;

/**
 * Created by Xavier on 8/9/16.
 */
public class Database {
    static Database db;
    String URL,username, pass;
    Connection conn;
    private Database() throws SQLException{
        URL = "jdbc:mysql://localhost:3306/cities";
        username = "XGiles";
        pass = "Dope1129";
        establishConnection();
    }

    public static Database createInstance() throws SQLException{
        if(db == null) {
            db = new Database();
            return db;
        }
        else{
            System.out.println("Instance already exists");
            return null;
        }
    }

    private void establishConnection() throws SQLException{
        conn = DriverManager.getConnection(URL, username,pass);
    }

    public void closeConnection() throws SQLException{
        conn.close();
    }

    public boolean validateInDatabase(String city, String state) throws SQLException{
        String location = getQuery(city,state);
        if(location.equals("")){
            System.out.println("Not a valid city, TRY AGAIN");
            return false;
            }
        else {
            return true;
            }
        }

    public String getQuery(String city, String state)throws SQLException{
        String location;
        PreparedStatement pst;
        ResultSet rs;
        ResultSetMetaData rsmd;

        pst = conn.prepareStatement("SELECT * FROM cities WHERE city = ? AND state_code = ?");
        pst.setString(1,city);
        pst.setString(2,state);
        rs = pst.executeQuery();
        rsmd = rs.getMetaData();
        location = "";
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) location += ",";
                String columnValue = rs.getString(i);
                location += columnValue;

            }
        }
        return location;
    }
}
