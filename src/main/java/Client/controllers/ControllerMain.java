package Client.controllers;

import Common.AppointmentService;
import Server.Database.DatabaseInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import Common.models.Appointment;

import Common.models.Dates;
import Server.model.ResourceAppointment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class ControllerMain implements Observer {

    private DatabaseInterface database;
    private ResourceAppointment resourceAppointment ;
    private ControllerAdd controllerAdd;
    private ControllerEdit controllerEdit;
    private ControllerNotes controllerNotes;
    private  SimpleDateFormat formatDate;
    @FXML private TableView<Appointment> tableView = new TableView<>();
    @FXML private Button addAppointment,ap;
    @FXML private Stage stage;
    @FXML private MenuItem exit,about,news;
    @FXML private TableColumn<Appointment, String> repeat;
    @FXML private TableColumn<Appointment, String> title;
    @FXML private TableColumn<Appointment, String> date;
    @FXML private TableColumn<Appointment, String> time;
    @FXML private TableColumn<Appointment, String> priority;
    @FXML private DatePicker datePicker;
    private AppointmentService appointmentService;



    public ControllerMain(){
        formatDate = new SimpleDateFormat("dd/MMM/yyyy");
    }




    //setting
    @FXML
    public void initialize(){
        //set tableview
        title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameEvent()));
        date.setCellValueFactory(cellData -> new SimpleStringProperty(formatDate.format(cellData.getValue().getDate().getDate())));
        time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime().getHour()+":"+cellData.getValue().getTime().getMinute()));
        repeat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepeat()));
        priority.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Notes");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Edit");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Delete");
        cm.getItems().add(mi3);
        mi1.setOnAction(event -> notesAppointment());
        mi2.setOnAction(event -> editAppointment());
        mi3.setOnAction(event -> deleteAppointment());
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY)
                {
                    cm.show(tableView , event.getScreenX() , event.getScreenY());
                   // System.out.println("Right Click!!!");
                }else{
                    cm.hide();
                   // System.out.println("Left Click!!!!");
                }
            }
        });
    }

    public void display(Stage primaryStage,AppointmentService appointmentService){

        try{
            //Parent root = FXMLLoader.load(getClass().getResource("calendar/MainPage.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/MainPage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Calendar");
            primaryStage.setScene(new Scene(root, 600, 500));
           ControllerMain controllerMain = loader.getController();
            controllerMain.appointmentService = appointmentService;
            controllerMain.setTable(controllerMain.appointmentService.getListAppointment());
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void notesAppointment(){
        Appointment productSelected = tableView.getSelectionModel().getSelectedItem();
        if(productSelected != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/Notes.fxml"));
                Parent window = loader.load();
                controllerNotes = loader.getController();
                controllerNotes.viewNotes(productSelected.getNotes());
                stage = new Stage();
                stage.setTitle("Notes");
                stage.setScene(new Scene(window,450,300));
                stage.setResizable(false);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void editAppointment(){
        //System.out.println("Edit!!!");
        Appointment productSelected = tableView.getSelectionModel().getSelectedItem();
        if(productSelected != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/EditAppointment.fxml"));
                Parent window = loader.load();
                controllerEdit = loader.getController();
                controllerEdit.addObserver(this);
                controllerEdit.setAp(productSelected);
                stage = new Stage();
                stage.setTitle("EditAppointment");
                stage.setScene(new Scene(window,400,475));
                stage.setResizable(false);
                stage.show();
           }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void deleteAppointment(){

        //System.out.println("Delete!!!");
        Appointment productSelected = tableView.getSelectionModel().getSelectedItem();
       if(productSelected != null) {
           appointmentService.deleteAppointment(productSelected);
           setTable(appointmentService.getListAppointment());
            //database.deleteData(productSelected.getId());
       }
    }

    @FXML
    public void addAppointment(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/AddAppointment.fxml"));
            Parent window = loader.load();
            controllerAdd = loader.getController();
            controllerAdd.addObserver(this);
            //System.out.println(appointmentService);
            controllerAdd.setCount(appointmentService.countSet());
            stage = new Stage();
            stage.setTitle("New Appointment");
            stage.setScene(new Scene(window,400,475));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void exitProgram(){
        //database.closeDatabase();
        System.exit(0);

    }

    @Override
    public void update(Observable o, Object appointment) {
        if(o instanceof ControllerAdd){
            //database.insertData((Appointment) appointment);
            appointmentService.addAppointment((Appointment) appointment);

        }
        if(o instanceof  ControllerEdit){
            //database.updateData((Appointment) appointment);
            appointmentService.editAppointment((Appointment) appointment);
        }
        setTable(appointmentService.getListAppointment());
    }



    public void refresh(ActionEvent event){
        setTable(appointmentService.getListAppointment());
        date.setCellValueFactory(cellData -> new SimpleStringProperty(formatDate.format(cellData.getValue().getDate().getDate())));
        datePicker.getEditor().clear();
        datePicker.setPromptText("--- Search Appointment");

    }

    @FXML
    public void searchAppointment(){
        LocalDate date = datePicker.getValue();
        Dates dates = new Dates();
        dates.setDate(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
        String d = formatDate.format(dates.getDate());
        this.date.setCellValueFactory(data -> new SimpleStringProperty(d));
        setTable(appointmentService.searchAppointment(date));

        
    }

    public void setTable(ArrayList<Appointment> appointments){
        ObservableList<Appointment> l = FXCollections.observableArrayList();
        l.addAll(appointments);
        tableView.setItems(l);
        tableView.refresh();
    }
}
