package mail.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class MailTest {
 
    public static void main(String[] args)
    {

        ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/springapp-servlet.xml");
        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailService");
        String to = "redouane.tigrara@gmail.com";
        String subject = "Récupération de votre mot de passe";
        String body = "dtc";
        mailer.sendMail(to, subject, body);

    }
}