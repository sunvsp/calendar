package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {

    public Appointment createAppointment() throws Exception{
        Appointment a = new Appointment("HelloWorld",30,8,1997,"11","54","None","1");
        return a;
    }


}