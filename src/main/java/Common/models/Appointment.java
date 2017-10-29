package Common.models;


import java.io.Serializable;

public class Appointment implements Serializable {

    private int id;
    private String nameEvent;
    private String repeat;
    private String notes;
    private Dates date;
    private Time time;
    private String priority;



    //Constructor
    public Appointment(int id,String nameEvent,Dates date,Time time,String priority,String repeat,String notes)  {
        this.id = id;
        this.setNameEvent(nameEvent);
        this.date = date;
        this.time = time;
        this.setRepeat(repeat);
        this.setNotes(notes);
        this.setPriority(priority);
    }


    public int getId() {
        return id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Dates getDate() {
        return date;
    }


    public Time getTime() {
        return time;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
