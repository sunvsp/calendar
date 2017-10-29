package Common.models;

import java.io.Serializable;

public class Time implements Serializable {

    private String hour ;
    private String minute;

    public Time( String hour, String minute){
        this.setHour(hour);
        this.setMinute(minute);
    }


    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }


}
