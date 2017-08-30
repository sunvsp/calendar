package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResourceAppointment {
    private static ResourceAppointment instance = null;
    protected ResourceAppointment() {
        // Exists only to defeat instantiation.
    }
    public static ResourceAppointment getInstance() {
        if (instance == null) {
            instance = new ResourceAppointment();
        }
        return instance;
    }

    private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();

    public  void addApointment(Appointment appointment){
        getListAppointment().add(appointment);
    }

    public ObservableList<Appointment> getListAppointment() {
        return listAppointments;
    }


}