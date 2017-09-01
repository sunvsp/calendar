package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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



    @FXML private TableView<Appointment> tableView;
    @FXML private Button addAppointment;
    @FXML private Stage stage;
    @FXML private MenuItem exit,about,news;
    @FXML private TableColumn<Appointment, SimpleStringProperty> order;
    @FXML private TableColumn<Appointment, SimpleStringProperty> title;
    @FXML private TableColumn<Appointment, SimpleStringProperty> date;
    @FXML private TableColumn<Appointment, SimpleStringProperty> time;
    @FXML private TableColumn<Appointment, SimpleStringProperty> priority;

    public MainController(){
        resourceAppointment = ResourceAppointment.getInstance();
        database = new Database();
    }

    @FXML
    public void initialize(){
        order.setCellValueFactory(new PropertyValueFactory<>("order"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
    }


    @FXML
    public void buttonAddAp(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/AddAppointment.fxml"));
            Parent window = loader.load();
            Controller controller = loader.getController();
            controller.addObserver(this);
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
        System.exit(0);

    }


    @Override
    public void update(Observable o, Object appointment) {
        database.connectDatebase();
        database.insertData((Appointment) appointment);
        tableView.setItems(resourceAppointment.getListAppointment());
        tableView.refresh();
    }
}
