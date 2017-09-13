package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import models.Appointment;
import Database.Database;
import models.ResourceAppointment;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;


public class MainController implements Observer{

    private Database database;
    private ResourceAppointment resourceAppointment ;
    private ControllerAdd controllerAdd;
    private ControllerEdit controllerEdit;
    private ControllerNotes controllerNotes;

    @FXML private TableView<Appointment> tableView;
    @FXML private Button addAppointment,ap;
    @FXML private Stage stage;
    @FXML private MenuItem exit,about,news;
    @FXML private TableColumn<Appointment, SimpleStringProperty> repeat;
    @FXML private TableColumn<Appointment, SimpleStringProperty> title;
    @FXML private TableColumn<Appointment, SimpleStringProperty> date;
    @FXML private TableColumn<Appointment, SimpleStringProperty> time;
    @FXML private TableColumn<Appointment, SimpleStringProperty> priority;
    @FXML private DatePicker datePicker;


    public MainController(){
        resourceAppointment = ResourceAppointment.getInstance();
        database = new Database();
        resourceAppointment.setListAppointments(database.readAndAddData());
        LocalDate date = LocalDate.now();
        resourceAppointment.searchAppointment(date);
//        database.updateData(1,2);
//        database.readAndAddData();
    }

    //setting
    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        repeat.setCellValueFactory(new PropertyValueFactory<>("repeat"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        setDateView();
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
        Appointment a = productSelected;
        if(productSelected.getId() != 0){
            for ( Appointment ap:resourceAppointment.getListAppointment()) {
                if(ap.getId() == productSelected.getParent()){
                    a = ap;
                }
            }}
        if(productSelected != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/EditAppointment.fxml"));
                Parent window = loader.load();
                controllerEdit = loader.getController();
                controllerEdit.addObserver(this);
                controllerEdit.setAp(a);
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
        Appointment a = productSelected;
        if(productSelected.getId() != 0){
            for ( Appointment ap:resourceAppointment.getListAppointment()) {
                if(ap.getId() == productSelected.getParent()){
                    a = ap;
                }
            }}
        //System.out.println(productSelected.getTitle());
        //System.out.println(productSelected.getOrder().trim());
       if(productSelected != null) {
            resourceAppointment.deleteAp(a);
           setDateView();
            database.deleteData(a.getId());
       }
    }

    @FXML
    public void buttonAddAp(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/AddAppointment.fxml"));
            Parent window = loader.load();
            controllerAdd = loader.getController();
            controllerAdd.addObserver(this);
            controllerAdd.setCount(resourceAppointment.countSet());
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
        database.closeDatabase();
        System.exit(0);

    }

    @Override
    public void update(Observable o, Object appointment) {
        if(o instanceof ControllerAdd){
            database.insertData((Appointment) appointment);
            resourceAppointment.addApointment((Appointment) appointment);
        }
        if(o instanceof  ControllerEdit){
            database.updateData((Appointment) appointment);
        }
        setDateView();
    }

    public Database getDatabase() {
        return database;
    }

    public void refresh(ActionEvent event){
        setDateView();
        datePicker.getEditor().clear();
        datePicker.setPromptText("--- Search Appointment");

    }

    @FXML
    public void searchAppointment(){
        LocalDate date = datePicker.getValue();
//        System.out.println(date.getDayOfWeek().toString());
        //System.out.println(date.getDayOfWeek().getValue());
        tableView.setItems(resourceAppointment.searchAppointment(date));
        tableView.refresh();
    }

    public void setDateView(){
        tableView.setItems(resourceAppointment.getListAppointment());

        tableView.refresh();
    }
}
