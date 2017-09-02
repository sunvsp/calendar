package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;

public class Database {
    private Connection conn ;

    public Database(){
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
            String query = "INSERT INTO eventCalendar (Id,'Title','Date','Time','Priority') " +
                    "VALUES ("+Integer.parseInt(a.getOrder().trim())+",'"+a.getTitle().trim()+"','"+a.getDate().trim()+
                    "','"+a.getTime().trim()+"','"+a.getPriority().trim()+"');";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<Appointment> readAndAddData(){
         ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
        try{
            String query = "Select * from eventCalendar";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String order = resultSet.getString(1);
                String title = resultSet.getString(2);
                String date  = resultSet.getString(3);
                String time  = resultSet.getString(4);
                String priority = resultSet.getString(5);
//                System.out.println(order+" "+title+" "+date+" "+time+" "+priority);
                //String order,String nameEvent, int day, int month, int year, String hour, String minute, String priority
//                System.out.println(date.substring(0,2)+"/"+date.substring(3,5)+"/"+
//                        date.substring(6,10)+" "+time.substring(0,2)+":"+time.substring(3,5));
                Appointment appointment = new Appointment(order,title,Integer.parseInt(date.substring(0,2)),
                        Integer.parseInt(date.substring(3,5)), Integer.parseInt(date.substring(6,10)),
                        time.substring(0,2),time.substring(3,5),priority);
                listAppointments.add(appointment);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return listAppointments;
    }

    public void deleteData(int order){
        try{
            String query = "Delete from eventCalendar where Id = "+order;
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateData(int start,int begin){
        try{
            for(int i = start;i<=begin;i++){
                String query = "UPDATE eventCalendar SET Id = "+i+" WHERE Id = "+(i+1);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateDatabase(Appointment appointment){
        String sql = "UPDATE eventCalendar SET 'Title' = ? , "
                + "'Date' = ? , "
                +"'Time' = ? ,"
                +"'Priority' = ? "
                + "WHERE Id = ?";
        //System.out.println(appointment.getTitle().trim()+" "+appointment.getDate().trim()+" "+
        //appointment.getTime().trim()+" "+appointment.getPriority().trim());

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, appointment.getTitle().trim());
            pstmt.setString(2, appointment.getDate().trim());
            pstmt.setString(3, appointment.getTime().trim());
            pstmt.setString(4, appointment.getPriority().trim());
            pstmt.setInt(5,Integer.parseInt(appointment.getOrder().trim()));
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void closeDatabase(){
        try {
            conn.close();
            System.out.println("Close Database...");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
