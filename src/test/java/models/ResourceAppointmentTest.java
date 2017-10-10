package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ResourceAppointmentTest {
    private Appointment appointment;
    private ResourceAppointment resourceAppointment;


    @Before
    public void setUp(){
        Dates d = new Dates();
        d.setDate(30,8,1997);
        Time t = new Time("10","20");
        appointment = new Appointment(1,"Sunny",d,t,"None","Daily","");
        resourceAppointment = ResourceAppointment.getInstance();
        resourceAppointment.setListAppointments(new ArrayList<>());
    }


    @Test
    public void testAddAppointment(){
        resourceAppointment.addAppointment(appointment);
        assertEquals(resourceAppointment.getListAppointment().get(0).getNameEvent(),appointment.getNameEvent());
    }




}