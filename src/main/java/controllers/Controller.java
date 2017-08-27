package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Event;
import models.ResourceEvent;

import java.text.ParseException;
import java.time.LocalDate;

public class Controller {

    ResourceEvent resourceEvent ;
    public Controller(){
        resourceEvent = new ResourceEvent();
    }

    ObservableList<String> itemP = FXCollections.observableArrayList("None","!","!!","!!!");
    ObservableList<String> itemH = FXCollections.observableArrayList("00","01","02","03","04",
            "05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    ObservableList<String> itemM = FXCollections.observableArrayList("00","01","02","03","04",
            "05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
            "24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41",
            "42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
    @FXML
    private Button add;
    @FXML
    private TextArea textArea;
    @FXML
    private ChoiceBox priority;
    @FXML
    private TextField field;
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;
    @FXML
    private DatePicker datePicker;



    //set
    @FXML
    private void initialize(){
        priority.setValue("None");
        priority.setItems(itemP);
        textArea.setEditable(false);
        datePicker.setValue(LocalDate.now());
    }


    //method
    @FXML
    public void handleButton(ActionEvent e){
        try {
            addAppointment();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        textArea.setText(resourceEvent.getEvent());
        resetButton();

    }

    public void addAppointment() throws ParseException{
        LocalDate time = datePicker.getValue();
        //debug getValue
//        System.out.println(time.toString());
//        System.out.println(time.getYear());
//        System.out.println(time.getMonthValue());
//        System.out.println(time.getDayOfMonth());
//        System.out.println(field.getText());
//        System.out.println(hour.getText());
//        System.out.println(minute.getText());
//        System.out.println(priority.getValue().toString());
        Event event = new Event(field.getText(),time.getDayOfMonth(),time.getMonthValue(),
                time.getYear(),hour.getText(),minute.getText(),priority.getValue().toString());
        //debug event
//        System.out.println(event.getNameEvent());
//        System.out.println(event.getPriority());
//        System.out.println(event.getDate());

        resourceEvent.addA(event);
    }


    private void resetButton(){
        field.clear();
        hour.clear();
        minute.clear();
        datePicker.setValue(LocalDate.now());

    }







}
