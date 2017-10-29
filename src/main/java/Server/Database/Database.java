package Server.Database;

import Common.models.Appointment;
import Common.models.Dates;
import Common.models.Time;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Database implements DatabaseInterface{
    private Connection conn ;

    @Override
    public void insertData(Appointment a){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatDate.format(a.getDate().getDate());
        String time = a.getTime().getHour()+":"+a.getTime().getMinute();
        try {
            String query = "INSERT INTO eventCalendar (Id,'Title','Date','Time','Priority','Repeat','Notes') " +
                    "VALUES ( "+a.getId()+",'"+a.getNameEvent()+"','"+date+
                    "','"+time+"','"+a.getPriority()+"','"+a.getRepeat()+"','"+a.getNotes()+"');";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Appointment> readData(){
        ArrayList<Appointment> listAppointments = new ArrayList<>();
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
                Dates d = new Dates();
                d.setDate(Integer.parseInt(date.substring(0,2)),
                        Integer.parseInt(date.substring(3,5)), Integer.parseInt(date.substring(6,10)));
                Time t = new Time(time.substring(0,2),time.substring(3,5));
                Appointment appointment = new Appointment(Integer.parseInt(id),title,d,t,priority,
                        repeat,notes);
                listAppointments.add(appointment);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listAppointments;
    }

    @Override
    public void deleteData(int id){
        try{
            String query = "Delete from eventCalendar where Id = "+id;
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
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
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatDate.format(appointment.getDate().getDate());
        String time = appointment.getTime().getHour()+":"+appointment.getTime().getMinute();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, appointment.getNameEvent());
            pstmt.setString(2, date);
            pstmt.setString(3, time);
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

    @Override
    public void closeDatabase(){
        try {
            conn.close();
            System.out.println("Close Database...");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void openDatabase(){
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
}
