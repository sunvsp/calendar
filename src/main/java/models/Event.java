package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String nameEvent;
    private Date myTime;
    private String priority;

    public Event(String nameEvent, int day, int month, int year, String hour, String minute, String priority) throws ParseException {
        this.nameEvent = nameEvent;
        this.priority = priority;
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        myTime = dateTimeFormat.parse(day+"/"+month+"/"+year+" "+hour+":"+minute);
    }

    public String getNameEvent() {
        return nameEvent;
    }



    public String getDate(){
        String dateToStr = DateFormat.getInstance().format(myTime);
        return dateToStr;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString(){
        return " Event :  "+ getNameEvent()+" | Date&Time :  "+getDate()+
                " | Priority :  "+getPriority();
    }
}
