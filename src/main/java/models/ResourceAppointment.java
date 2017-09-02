package models;

import javafx.beans.property.SimpleStringProperty;
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

    public void setListAppointments(ObservableList<Appointment> observableList){
        listAppointments = observableList;
    }

    public void deleteAp(Appointment appointment){

        if(listAppointments.size() > 0) {

            for (int i = Integer.parseInt(appointment.getOrder().trim()) - 1; i < listAppointments.size(); i++) {


                int order = Integer.parseInt(listAppointments.get(i).getOrder().trim());
                if(order > 1) {
                    order--;
                    listAppointments.get(i).setOrder(new SimpleStringProperty(order + ""));
                }

            }
        }
        listAppointments.remove(appointment);


//        System.out.println(listAppointments);
//        System.out.println(listAppointments.get(0).getOrder());
    }


}
