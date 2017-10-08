import controllers.ControllerMain;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("calendar/MainPage.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/MainPage.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(new Scene(root, 600, 500));
        ControllerMain mainController = loader.getController();
        //mainController.setDatabase(new Database());
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainController.getDatabase().closeDatabase();
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
