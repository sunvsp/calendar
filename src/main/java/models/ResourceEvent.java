package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResourceEvent {


    private ObservableList<Event> listEvents = FXCollections.observableArrayList();

    public  void addA(Event e){
        getListEvents().add(e);
    }
    public ObservableList<Event> getListEvents() {
        return listEvents;
    }
}
