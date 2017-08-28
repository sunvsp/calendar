package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {

    public Event createEvent() throws Exception{
        Event e = new Event("HelloWorld",30,8,1997,"11","54","None");
        return e;
    }

    @Test
    public void getNameEvent() throws Exception {
        Event e = createEvent();
        assertEquals("HelloWorld", e.getNameEvent());
    }

    @Test
    public void getDate() throws Exception {
        Event e = createEvent();
        assertEquals("30/8/1997, 11:54 น.", e.getDate());
    }

    @Test
    public void getPriority() throws Exception {
        Event e = createEvent();
        assertEquals("None", e.getPriority());
    }


}