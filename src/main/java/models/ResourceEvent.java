package models;

import java.util.ArrayList;

public class ResourceEvent {

    private ArrayList<Event> events = new ArrayList<Event>();

    public  void addA(Event e){
        events.add(e);
    }

    public ArrayList<Event> getArrayList(){
        return events;
    }

    public String getEvent(){
        String result = "";
        for (int i =0; i < events.size();i++) {
            String text = (i+1)+"."+" Event :  "+ events.get(i).getNameEvent()+" | Date&Time :  "+events.get(i).getDate()+
                    " | Priority :  "+events.get(i).getPriority()+" \n";
            result += text;
        }

        return  result;
    }
}
