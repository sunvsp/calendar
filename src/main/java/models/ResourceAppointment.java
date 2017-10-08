package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.security.krb5.internal.APOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResourceAppointment {
    private static ResourceAppointment instance = null;
    private ResourceAppointment() {
        // Exists only to defeat instantiation.
    }
    public static ResourceAppointment getInstance() {
        if (instance == null) {
            instance = new ResourceAppointment();
        }
        return instance;
    }

    private ArrayList<Appointment> listAppointments = new ArrayList<>();

    public  void addAppointment(Appointment appointment){
        getListAppointment().add(appointment);
    }

    public ArrayList<Appointment> getListAppointment() {
        return listAppointments;
    }

    public void setListAppointments(ArrayList<Appointment> list){
        listAppointments = list;
    }

    public void deleteAppointment(Appointment appointment){
        listAppointments.remove(appointment);
    }

    public int countSet(){
        if(listAppointments.isEmpty()){
            return 1;
        }
        return (listAppointments.get(listAppointments.size()-1).getId()+1);
    }


    public ArrayList<Appointment> searchAppoiment(LocalDate localDate) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment a : listAppointments) {
            String date = formatDate.format(a.getDate().getDate());
            LocalDate d = LocalDate.parse(date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2));
            if (a.getRepeat().equals("Daily")) {
                appointments.add(a);
            } else if (a.getRepeat().equals("Weekly")) {
                if (localDate.getDayOfWeek().getValue() == d.getDayOfWeek().getValue()) {
                    appointments.add(a);
                }
            } else if (a.getRepeat().equals("Monthly")) {
                if (localDate.getDayOfMonth() == d.getDayOfMonth()) {
                    appointments.add(a);
                }
            }


        }
        return appointments;
    }

}
