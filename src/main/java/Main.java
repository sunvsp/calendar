import controllers.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.ParseException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("calendar/MainPage.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendar/MainPage.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Calenders");
        primaryStage.setScene(new Scene(root, 600, 500));
        MainController mainController = loader.getController();
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
