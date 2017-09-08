package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;
import models.ResourceAppointment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

public class ControllerEdit extends Observable {



    private Appointment appointment;
    private ResourceAppointment resourceAppointment = ResourceAppointment.getInstance();
    private ObservableList<String> itemP = FXCollections.observableArrayList("None","!!!","!","!");
    private ObservableList<String> itemH = FXCollections.observableArrayList("00","01","02","03","04",
            "05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    private ObservableList<String> itemM = FXCollections.observableArrayList("00","05","10","15","20",
            "25","30","35","40","45","50","55","50","55");


    @FXML private Button submit;
    @FXML private ChoiceBox priority;
    @FXML private ComboBox hour;
    @FXML private ComboBox minute;
    @FXML private TextField field;
    @FXML private DatePicker datePicker;
    @FXML private MenuItem exit,about;
    @FXML private Label repeat;
    @FXML private TextArea notes;


    //set
    @FXML
    private void initialize(){
        priority.setItems(itemP);
        hour.setItems(itemH);
        minute.setItems(itemM);
    }

    @FXML
    public void submitButton(ActionEvent event){
//        Appointment appointment = new Appointment(count +"",field.getText(),time.getDayOfMonth(),time.getMonthValue(),
//                time.getYear(),hour.getValue().toString(),minute.getValue().toString(),priority.getValue().toString() );
        try {
            LocalDate time = datePicker.getValue();
            appointment.setTitle(new SimpleStringProperty(field.getText()));
            DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date myTime = dateTimeFormat.parse(time.getDayOfMonth() + "/" + time.getMonthValue() +
                    "/" + time.getYear() + " " + hour.getValue().toString() + ":" + minute.getValue().toString());
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            appointment.setDate(new SimpleStringProperty(formatDate.format(myTime)));
            appointment.setTime(new SimpleStringProperty(formatTime.format(myTime)));
            appointment.setPriority(new SimpleStringProperty(priority.getValue().toString()));
            appointment.setNotes(notes.getText());
        }catch(ParseException e){
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(appointment);
        closePage();

    }

    public void setAp(Appointment appointment){
        this.appointment =appointment;
        field.setText(appointment.getTitle());
        String time = appointment.getTime();
        hour.setValue(time.substring(0,2));

        minute.setValue(time.substring(3,5));
        priority.setValue(appointment.getPriority());
        String date = appointment.getDateA();
        //System.out.println(date);
        LocalDate d = LocalDate.parse(date.substring(6,10)+"-"+date.substring(3,5)+"-"+date.substring(0,2));
        //System.out.println(d);
        datePicker.setValue(d);
        repeat.setText(appointment.getRepeat());
        notes.setText(appointment.getNotes());
    }

    @FXML
    public  void exitMenu(ActionEvent e){
        closePage();
    }

    public void closePage(){
        // get a handle to the stage
        Stage stage = (Stage) submit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
