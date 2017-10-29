package Server.model;

import Common.models.Appointment;
import Common.AppointmentService;
import Server.Database.DatabaseInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResourceAppointment implements AppointmentService {

    private DatabaseInterface database;
    private ArrayList<Appointment> listAppointments ;


    public  ResourceAppointment(DatabaseInterface database) {
        this.database = database;
        listAppointments = new ArrayList<>();
        this.database.openDatabase();
        setListAppointments(this.database.readData());
        this.database.closeDatabase();
    }

    public  void addAppointment(Appointment appointment){
        database.openDatabase();
        getListAppointment().add(appointment);
        database.insertData(appointment);
        database.closeDatabase();
    }

    public ArrayList<Appointment> getListAppointment() {
        return listAppointments;
    }

    public void setListAppointments(ArrayList<Appointment> list){
        listAppointments = list;
    }

    public void deleteAppointment(Appointment appointment){

        database.openDatabase();
        listAppointments.remove(findAppointment(appointment));

        database.deleteData(appointment.getId());
        database.closeDatabase();
    }

    public int countSet(){
        if(listAppointments.isEmpty()){
            return 1;
        }
        return (listAppointments.get(listAppointments.size()-1).getId()+1);
    }


    public ArrayList<Appointment> searchAppointment(LocalDate localDate) {
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

    public void editAppointment(Appointment appointment){
        listAppointments.set(findAppointment(appointment),appointment);
        database.openDatabase();
        database.updateData(appointment);
        database.closeDatabase();
    }

    public int findAppointment(Appointment appointment){
        for(int i = 0; i < listAppointments.size();i++){
            if(listAppointments.get(i).getId() == appointment.getId()){
                return i;
            }
        }return -1;
    }

}

