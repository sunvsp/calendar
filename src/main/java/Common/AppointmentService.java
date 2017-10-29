package Common;

import Common.models.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AppointmentService  {

    void addAppointment(Appointment appointment);
    ArrayList<Appointment> getListAppointment();
    void deleteAppointment(Appointment appointment);
    int countSet();
    ArrayList<Appointment> searchAppointment(LocalDate localDate);
    //void setListAppointments(ArrayList<Appointment> list);
    void editAppointment(Appointment appointment);



}
