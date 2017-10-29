package Client;

import Client.controllers.ControllerMain;
import Common.AppointmentService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ApplicationContext bf = new ClassPathXmlApplicationContext("/fileXML/Calendar-Client.xml");
        AppointmentService appointmentService = (AppointmentService) bf.getBean("appointmentService");
        ControllerMain mainController = new ControllerMain();
        mainController.display(primaryStage,appointmentService);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
