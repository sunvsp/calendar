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
    private ControllerAdd controllerAdd;
    private ControllerEdit controllerEdit;




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
//        database.updateData(1,2);
//        database.readAndAddData();
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
        mi1.setOnAction(event -> editAppointment());
        mi2.setOnAction(event -> deleteAppointment());
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
                stage.setScene(new Scene(window,400,280));
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
        //System.out.println(productSelected.getTitle());
        //System.out.println(productSelected.getOrder().trim());
       if(productSelected != null) {
            resourceAppointment.deleteAp(productSelected);
            refresh();
            database.deleteData(Integer.parseInt(productSelected.getOrder().trim()));

            if(resourceAppointment.getListAppointment().size() > 0) {
                updateIdDatabase(Integer.parseInt(productSelected.getOrder().trim()), resourceAppointment.getListAppointment().size());
            }
//           database.readAndAddData();
       }
    }


    public void updateIdDatabase(int start,int begin){
        database.updateData(start,begin);
    }


    @FXML
    public void buttonAddAp(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/AddAppointment.fxml"));
            Parent window = loader.load();
            controllerAdd = loader.getController();
            controllerAdd.addObserver(this);
            controllerAdd.setCount(resourceAppointment.getListAppointment().size()+1);
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
        if(o instanceof ControllerAdd){
            database.insertData((Appointment) appointment);
        }
        if(o instanceof  ControllerEdit){
            database.updateDatabase((Appointment) appointment);
        }
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
