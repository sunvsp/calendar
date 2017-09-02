package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import models.Database;
import models.ResourceAppointment;

import java.util.Observable;
import java.util.Observer;


public class MainController implements Observer{



    private Database database;
    private ResourceAppointment resourceAppointment ;
    private Controller controller;



    @FXML private TableView<Appointment> tableView;
    @FXML private Button addAppointment;
    @FXML private Stage stage;
    @FXML private MenuItem exit,about,news;
    @FXML private TableColumn<Appointment, SimpleStringProperty> order;
    @FXML private TableColumn<Appointment, SimpleStringProperty> title;
    @FXML private TableColumn<Appointment, SimpleStringProperty> date;
    @FXML private TableColumn<Appointment, SimpleStringProperty> time;
    @FXML private TableColumn<Appointment, SimpleStringProperty> priority;
    @FXML private ObservableList<String> comboTable = FXCollections.observableArrayList("Edit","Delete");

    public MainController(){
        resourceAppointment = ResourceAppointment.getInstance();
        database = new Database();
        resourceAppointment.setListAppointments(database.readAndAddData());
    }

    //setting
    @FXML
    public void initialize(){
        order.setCellValueFactory(new PropertyValueFactory<>("order"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tableView.setItems(resourceAppointment.getListAppointment());
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Edit");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Delete");
        cm.getItems().add(mi2);
        mi1.setOnAction(this::editAppointment);
        mi2.setOnAction(this::deleteAppointment);
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY)
                {
                    cm.show(tableView , event.getScreenX() , event.getScreenY());
                    System.out.println("Right Click!!!");
                }else{
                    cm.hide();
                    System.out.println("Left Click!!!!");
                }
            }
        });
    }


    @FXML
    public void editAppointment(ActionEvent event){
        System.out.println("Edit!!!");
    }

    @FXML
    public void deleteAppointment(ActionEvent event){
        System.out.println("Delete!!!");
    }



    @FXML
    public void buttonAddAp(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/AddAppointment.fxml"));
            Parent window = loader.load();
            controller = loader.getController();
            controller.addObserver(this);
            controller.setCount(resourceAppointment.getListAppointment().size()+1);
            stage = new Stage();
            stage.setTitle("New Appointment");
            stage.setScene(new Scene(window,400,280));
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
        database.insertData((Appointment) appointment);
        refresh();
    }

    public Database getDatabase() {
        return database;
    }

    public void refresh(){
        tableView.setItems(resourceAppointment.getListAppointment());
        tableView.refresh();
    }
}
