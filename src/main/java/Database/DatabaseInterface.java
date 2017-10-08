package Database;

import models.Appointment;

import java.util.ArrayList;

public interface DatabaseInterface {

    public void openDatabase();
    public ArrayList<Appointment> readData();
    public void deleteData(int id);
    public void closeDatabase();
    public void updateData(Appointment appointment);
    public void insertData(Appointment a);





}
