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
    private SimpleStringProperty repeat;
    private String notes;
    private int id;

    //Constructor
    public Appointment(int id,String nameEvent, int day, int month, int year, String hour, String minute, String priority,String repeat,String notes) throws ParseException {
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        setMyTime(dateTimeFormat.parse(day+"/"+month+"/"+year+" "+hour+":"+minute));
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

        setTitle(new SimpleStringProperty(nameEvent));
        setDate(new SimpleStringProperty(formatDate.format(getMyTime())));
        time = new SimpleStringProperty(formatTime.format(myTime));
        this.setPriority(new SimpleStringProperty(priority));
        this.repeat = new SimpleStringProperty(repeat);
        this.setNotes(notes);
        this.id = id;

    }

    public String getTitle(){
        return getTitles().get();
    }
    public String getDate(){

        return getDates().get().substring(0,3)+myTime.toString().substring(4,7)+getDates().get().substring(5,10);
    }
    public String getTime(){
        return getTimes().get();
    }
    public String getPriority(){
        return getPrioritys().get();
    }
    public String getRepeat(){
        return getRepeats().get();
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
    public int getId() {
        return id;
    }
    public SimpleStringProperty getRepeats() {
        return repeat;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getDateA(){
        return getDates().get();
    }
}
