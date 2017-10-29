package Server;

import Server.Database.DatabaseInterface;
import Server.model.ResourceAppointment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainServer {


    public static void main(String[] args){
        ApplicationContext bf = new ClassPathXmlApplicationContext("fileXML/Calendar-Server.xml");


       // ResourceAppointment resourceAppointment = (ResourceAppointment) bf.getBean("resourceAppointment");

        System.out.println("Server is ready");
    }
}
