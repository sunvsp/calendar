package Database;

import models.Appointment;

import java.util.ArrayList;

public interface DatabaseInterface {

     void openDatabase();
     ArrayList<Appointment> readData();
     void deleteData(int id);
     void closeDatabase();
     void updateData(Appointment appointment);
     void insertData(Appointment a);





}
