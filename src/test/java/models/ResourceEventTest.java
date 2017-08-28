package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceEventTest {



    public Event createEvent() throws Exception{
        Event e = new Event("HelloWorld",30,8,1997,"11","54","None");
        return e;
    }

    @Test
    public void addA() throws Exception {
        ObservableList<Event> listEvents = FXCollections.observableArrayList();
        Event e = createEvent();
        listEvents.add(e);
        assertEquals(e,listEvents.get(0));

    }



}