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



    public Appointment(String order,String nameEvent, int day, int month, int year, String hour, String minute, String priority) throws ParseException {

        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        setMyTime(dateTimeFormat.parse(day+"/"+month+"/"+year+" "+hour+":"+minute));
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

        setTitle(new SimpleStringProperty(nameEvent));
        setDate(new SimpleStringProperty(formatDate.format(getMyTime())));
        time = new SimpleStringProperty(formatTime.format(myTime));
        this.setPriority(new SimpleStringProperty(priority));
        this.setOrder(new SimpleStringProperty(order));
    }

    public String getTitle(){
        return "   "+ getTitles().get();
    }

    public String getDate(){
        return "     "+ getDates().get();
    }
    public String getTime(){
        return "        "+ getTimes().get();
    }
    public String getPriority(){
        return "       "+ getPrioritys().get();
    }
    public String getOrder(){
        return "      "+ getOrders().get();
    }


    public Date getMyTime() {
        return myTime;
    }

    public void setMyTime(Date myTime) {
        this.myTime = myTime;
    }

    public SimpleStringProperty getTitles() {
        return title;
    }

    public void setTitle(SimpleStringProperty title) {
        this.title = title;
    }

    public SimpleStringProperty getDates() {
        return date;
    }

    public void setDate(SimpleStringProperty date) {
        this.date = date;
    }

    public SimpleStringProperty getTimes() {
        return time;
    }

    public void setTime(SimpleStringProperty time) {
        this.time = time;
    }

    public SimpleStringProperty getPrioritys() {
        return priority;
    }

    public void setPriority(SimpleStringProperty priority) {
        this.priority = priority;
    }

    public SimpleStringProperty getOrders() {
        return order;
    }

    public void setOrder(SimpleStringProperty order) {
        this.order = order;
    }
}
