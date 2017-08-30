package models;

import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private Date myTime;

    private SimpleStringProperty title;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty priority;
    private SimpleStringProperty order;



    public Appointment(String nameEvent, int day, int month, int year, String hour, String minute, String priority,String order) throws ParseException {

        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        myTime = dateTimeFormat.parse(day+"/"+month+"/"+year+" "+hour+":"+minute);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

        title = new SimpleStringProperty(nameEvent);
        date = new SimpleStringProperty(formatDate.format(myTime));
        time = new SimpleStringProperty(formatTime.format(myTime));
        this.priority = new SimpleStringProperty(priority);
        this.order = new SimpleStringProperty(order);
    }

    public String getTitle(){
        return "   "+title.get();
    }

    public String getDate(){
        return "     "+date.get();
    }

    public String getTime(){
        return "        "+time.get();
    }
    public String getPriority(){
        return "       "+priority.get();
    }
    public String getOrder(){
        return "      "+order.get();
    }









}
