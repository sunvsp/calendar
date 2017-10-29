package Client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Common.models.Appointment;
import Common.models.Dates;
import Common.models.Time;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

public class ControllerAdd extends Observable{


    private int count;
    @FXML private MenuItem exit,about;


    private ArrayList<String> itemPrority = new ArrayList<String>(){{ add("None");add("!!!");add("!");add("!"); }};
    private ArrayList<String> itemRepeat = new ArrayList<String>(){{add("None");add("Daily");add("Weekly");add("Monthly");}};
    private ArrayList<String> itemHour = new  ArrayList<String>(){{add("00");add("01");add("02");add("03");add("04");add("05");add("06");add("07");
        add("08");add("09");add("10");add("11");add("12");add("13");add("14");add("15");add("16");add("17");add("18");add("19");add("20");add("21");add("22");add("23"); }};
    private ArrayList<String> itemMinute = new ArrayList<String>(){{add("00");add("05");add("10");add("15");add("20");add("25");add("30");add("35");
        add("40");add("45");add("50");add("55");}};


    @FXML private Button add;
    @FXML private ChoiceBox priority,repeat;
    @FXML private ComboBox hour;
    @FXML private ComboBox minute;
    @FXML private TextField field;
    @FXML private DatePicker datePicker;
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

        priority.setValue("None");
        priority.setItems(itemP);
        datePicker.setValue(LocalDate.now());
        hour.setValue("00");
        hour.setItems(itemH);
        minute.setValue("00");
        minute.setItems(itemM);
        repeat.setValue("None");
        repeat.setItems(itemR);

    }
    //method
    @FXML
    public  void exitMenu(ActionEvent e){
        closePage();
    }

    @FXML
    public void handleButton(ActionEvent e){

        LocalDate date = datePicker.getValue();
        Dates d = new Dates();
        d.setDate(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
        Time t = new Time(hour.getValue().toString(),minute.getValue().toString());
        Appointment appointment = new Appointment(count,field.getText(),d,t,priority.getValue().toString(),
                repeat.getValue().toString(),notes.getText());
        closePage();
       // System.out.println(resourceAppointment.getListAppointment());
        setChanged();
        notifyObservers(appointment);
    }

    public void closePage(){
        // get a handle to the stage
        Stage stage = (Stage) add.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setCount(int c){
        count = c;

    }




}
