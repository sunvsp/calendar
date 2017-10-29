package Client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;



public class ControllerNotes{

    @FXML
    private TextArea notes;

    public void viewNotes(String message){
        notes.setText(message);
    }
}
