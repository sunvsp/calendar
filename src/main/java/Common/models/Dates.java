package Common.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Dates implements Serializable {
    private Date date;
    DateFormat dateTimeFormat;

    public Date getDate(){
        return date;
    }

    public void setDate(int day,int month,int year){
        try {
            dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = (dateTimeFormat.parse(day+"/"+month+"/"+year));
        }
        catch (ParseException e){
            e.printStackTrace();
        }


    }
}
