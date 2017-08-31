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
        ResourceAppointment r = new ResourceAppointment();
        Appointment appointment = createAppointment();
        r.addApointment(appointment);
        assertEquals(appointment,r.getListAppointment().get(0));

    }



}