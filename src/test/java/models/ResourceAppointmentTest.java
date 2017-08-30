package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceAppointmentTest {




    public Appointment createAppointment() throws Exception{
        Appointment a = new Appointment("HelloWorld",30,8,1997,"11","54","None","1");
        return a;
    }

    @Test
    public void addA() throws Exception {
        ObservableList<Appointment> listAppointment = FXCollections.observableArrayList();
        Appointment appointment = createAppointment();
        listAppointment.add(appointment);
        assertEquals(appointment,listAppointment.get(0));

    }



}