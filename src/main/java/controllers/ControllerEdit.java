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
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class ControllerEdit extends Observable {


    private Appointment appointment;
    private ResourceAppointment resourceAppointment = ResourceAppointment.getInstance();
    private ArrayList<String> itemPrority = new ArrayList<String>(){{ add("None");add("!!!");add("!");add("!"); }};
    private ArrayList<String> itemRepeat = new ArrayList<String>(){{add("None");add("Daily");add("Weekly");add("Monthly");}};
    private ArrayList<String> itemHour = new  ArrayList<String>(){{add("00");add("01");add("02");add("03");add("04");add("05");add("06");add("07");
        add("08");add("09");add("10");add("11");add("12");add("13");add("14");add("15");add("16");add("17");add("18");add("19");add("20");add("21");add("22");add("23"); }};
    private ArrayList<String> itemMinute = new ArrayList<String>(){{add("00");add("05");add("10");add("15");add("20");add("25");add("30");add("35");
        add("40");add("45");add("50");add("55");}};

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

        ObservableList<String> itemP = FXCollections.observableArrayList();
        itemP.addAll(itemPrority);
        ObservableList<String> itemR = FXCollections.observableArrayList();
        itemR.addAll(itemRepeat);
        ObservableList<String> itemH = FXCollections.observableArrayList();
        itemH.addAll(itemHour);
        ObservableList<String> itemM = FXCollections.observableArrayList();
        itemM.addAll(itemMinute);

        priority.setItems(itemP);
        hour.setItems(itemH);
        minute.setItems(itemM);
    }

    @FXML
    public void submitButton(ActionEvent event){

        LocalDate date = datePicker.getValue();
        appointment.setNameEvent(field.getText());
        appointment.getDate().setDate(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
        appointment.getTime().setHour(hour.getValue().toString());
        appointment.getTime().setMinute(minute.getValue().toString());
        appointment.setPriority(priority.getValue().toString());
        appointment.setNotes(notes.getText());

        setChanged();
        notifyObservers(appointment);
        closePage();

    }



    public void setAp(Appointment appointment){
        this.appointment =appointment;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        field.setText(appointment.getNameEvent());

        hour.setValue(appointment.getTime().getHour());

        minute.setValue(appointment.getTime().getMinute());
        priority.setValue(appointment.getPriority());
        String date = formatDate.format(appointment.getDate().getDate());
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
