import controllers.ControllerMain;
import controllers.ControllerNotes;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ApplicationContext bf = new ClassPathXmlApplicationContext("/fileXML/MainController.xml");
        ControllerMain mainController = (ControllerMain) bf.getBean("mainController");
        mainController.display(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
