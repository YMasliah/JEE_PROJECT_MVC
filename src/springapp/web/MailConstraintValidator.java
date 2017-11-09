package springapp.web;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MailConstraintValidator implements ConstraintValidator<Mail, String> {

    @Override
    public void initialize(Mail arg0) {
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (isValidEmailAddress(arg0))
            return true;
        return false;
    }

    public static boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    		  ex.getStackTrace();
    	      result = false;
    	   }
    	   return result;
    	}
}