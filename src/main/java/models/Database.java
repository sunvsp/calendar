package models;

import java.sql.*;
import java.text.ParseException;

public class Database {
    private Connection conn ;

    public void connectDatebase() {
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:EventCalendar.db";
            conn = DriverManager.getConnection(dbURL);
            if(conn != null){
                System.out.println("Connected to Database...");
            }
            //conn.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertData(Appointment a){
        try {
            String query = "INSERT INTO eventCalendar ('Order','Title','Date','Time','Priority') " +
                    "VALUES ('"+a.getOrder()+"','"+a.getTitle()+"','"+a.getDate()+"','"+a.getTime()+"','"+a.getPriority()+"');";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeDatabase();
        }


    }


    public void closeDatabase(){
        try {
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
