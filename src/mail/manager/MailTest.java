package mail.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class MailTest {
 
    public static void main(String[] args)
    {
        //Create the application context
    	System.out.print("Ok1");
        ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/springapp-servlet.xml");
        System.out.print("Ok2");
        //Get the mailer instance
        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailService");
 
        //Send a composed mail
//        mailer.sendMail("somebody@gmail.com", "Test Subject", "Testing body");
        System.out.print("Ok3");
        //Send a pre-configured mail
        mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
        System.out.print("OOOOK");
    }
}