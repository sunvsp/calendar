package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
        listAppointments.remove(appointment);
    }

    public int countSet(){
        if(listAppointments.isEmpty()){
            return 1;
        }
        return (listAppointments.get(listAppointments.size()-1).getId()+1);
    }

    public ObservableList<Appointment> searchAppointment(LocalDate dates){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        for (int a = 0; a  < listAppointments.size();a++) {
            String date = listAppointments.get(a).getDateA();
            LocalDate d = LocalDate.parse(date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2));
            if(listAppointments.get(a).getRepeat().equals("Weekly" ) || listAppointments.get(a).getRepeat().equals("Daily")){
                //System.out.println(date);
                //System.out.println(d.getDayOfWeek().getValue());
                if((d.getDayOfWeek().getValue() == dates.getDayOfWeek().getValue()&& listAppointments.get(a).getRepeat().equals("Weekly" )) ||
                        (d.getDayOfMonth() != dates.getDayOfMonth() &&listAppointments.get(a).getRepeat().equals("Daily") ) ){
                    //System.out.println("Weekly: "+a.getTitle()+" "+a.getDate());
                    if(dates.getDayOfMonth() != d.getDayOfMonth()){
                        Appointment ap = createAp(listAppointments.get(a),dates);
                        //database.insertData(ap);
                        if(ap != null){
                            list.add(ap);
                        }
                    }else{
                        list.add(listAppointments.get(a));}
                }else if(d.getDayOfMonth() == dates.getDayOfMonth() &&listAppointments.get(a).getRepeat().equals("Daily") ){
                    list.add(listAppointments.get(a));}
            }else if(listAppointments.get(a).getRepeat().equals("Monthly")){
                if(d.getDayOfMonth() == dates.getDayOfMonth()){
                    //System.out.println("Monthly: "+a.getTitle()+" "+a.getDate());
                    if(d.getMonthValue() != dates.getMonthValue()){
                        Appointment ap = createAp(listAppointments.get(a),dates);
                        if(ap != null ){
                            list.add(ap);
                        }
                    }else{
                        list.add(listAppointments.get(a));
                    }
                }
            }else if(d.getDayOfMonth() == dates.getDayOfMonth() && d.getMonthValue() == dates.getMonthValue()){
                list.add(listAppointments.get(a));
            }
        }
        return list;
    }

//    public ObservableList<Appointment> monthAppointment(){
//        LocalDate date = LocalDate.now();
//        ObservableList<Appointment> list = FXCollections.observableArrayList();
//        for (Appointment a: listAppointments) {
//            //System.out.println(date.getMonth().toString().toLowerCase().substring(0,3));
//            //System.out.println(a.getDate().toLowerCase().substring(3,6));
//            if(date.getMonth().toString().toLowerCase().substring(0,3).equals(a.getDate().toLowerCase().substring(3,6))){
//                list.add(a);
//            }
//        }
//        return list;
//    }


    private Appointment createAp(Appointment a,LocalDate dates){
        try{
            Appointment ap = (Appointment) a.clone();
            DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            ap.setMyTime(dateTimeFormat.parse(dates.getDayOfMonth()+"/"+
                    dates.getMonthValue()+"/"+dates.getYear()+" "+a.getTime()));
            ap.setParent(ap.getId());
            ap.setId(countSet());
            return ap;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

//    private boolean check(Appointment a) {
//        for (Appointment ap : listAppointments) {
//            if (a.getDate().equals(ap.getDate())) {
//                return false;
//                }
//
//        }
//        return true;
//    }

}
