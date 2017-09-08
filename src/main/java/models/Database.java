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
            String query = "INSERT INTO eventCalendar (Id,'Title','Date','Time','Priority','Repeat','Notes') " +
                    "VALUES ( "+a.getId()+",'"+a.getTitle()+"','"+a.getDateA()+
                    "','"+a.getTime()+"','"+a.getPriority()+"','"+a.getRepeat()+"','"+a.getNotes()+"');";
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
                String id = resultSet.getString(1);
                String title = resultSet.getString(2);
                String date  = resultSet.getString(3);
                String time  = resultSet.getString(4);
                String priority = resultSet.getString(5);
                String repeat = resultSet.getString(6);
                String notes = resultSet.getString(7);
//                System.out.println(id+" "+title+" "+date+" "+time+" "+priority+" "+repeat+" "+notes);
                //String order,String nameEvent, int day, int month, int year, String hour, String minute, String priority
//                System.out.println(date.substring(0,2)+"/"+date.substring(3,5)+"/"+
//                        date.substring(6,10)+" "+time.substring(0,2)+":"+time.substring(3,5));
                Appointment appointment = new Appointment(Integer.parseInt(id),title,Integer.parseInt(date.substring(0,2)),
                        Integer.parseInt(date.substring(3,5)), Integer.parseInt(date.substring(6,10)),
                        time.substring(0,2),time.substring(3,5),priority,repeat,notes);
                listAppointments.add(appointment);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return listAppointments;
    }

    public void deleteData(int id){
        try{
            String query = "Delete from eventCalendar where Id = "+id;
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public void updateData(Appointment appointment){
        String sql = "UPDATE eventCalendar SET 'Title' = ? , "
                + "'Date' = ? , "
                +"'Time' = ? ,"
                +"'Priority' = ? , "
                +"'Repeat' = ? ,"
                +"'Notes' = ? "
                + "WHERE Id = ?";
        //System.out.println(appointment.getTitle().trim()+" "+appointment.getDate().trim()+" "+
        //appointment.getTime().trim()+" "+appointment.getPriority().trim());

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, appointment.getTitle());
            pstmt.setString(2, appointment.getDateA());
            pstmt.setString(3, appointment.getTime());
            pstmt.setString(4, appointment.getPriority());
            pstmt.setString(5, appointment.getRepeat());
            pstmt.setString(6, appointment.getNotes());
            pstmt.setInt(7, appointment.getId());
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
