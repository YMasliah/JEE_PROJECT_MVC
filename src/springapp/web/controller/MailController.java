package springapp.web.controller;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;
import directory.manager.beans.User;

/**
 * 
 * @author m21002022
 *
 *         comment c ignoble le code de cette methode a coder
 *
 */
@Controller
@RequestMapping("/directory/password")
public class MailController extends BaseController {
	
	private final String subject = "JEE : Reset password token";
	private final User mailWorker = new User();
	// minutes before token to be deleted
	private final int time = 5;
	
	@Autowired
	private JavaMailSender mailSender;

	@PostConstruct
	public void init() {
		mailWorker.setName("mailWorker");
	}
	
	@RequestMapping(value = "/sendMail",method = RequestMethod.GET)
	public String getFormEmail(HttpServletRequest request) {
		return "passwordLost";
	}

	@RequestMapping(value = "/sendMail",method = RequestMethod.POST)
	public ModelAndView doSendEmail(HttpServletRequest request) throws NumberFormatException, DaoException, InterruptedException {
		ModelAndView mv = new ModelAndView("tokenForm");
		String mailAddress = request.getParameter("email");
		String id = request.getParameter("id");
		String lastName = request.getParameter("lastName");
		
		String databaseMail = manager.findPerson(mailWorker, Long.parseLong(id)).getEmail();
		
		if(databaseMail != null && databaseMail.equals(mailAddress)){
			
		}else{
			mv = new ModelAndView("passwordLost");
			mv.addObject("error", "l'utilisateur ne dispose pas de mail ou le mail ne correspond pas");
			return mv;
		}
		
		Integer temp = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
		String token = temp.toString();
		for (int i = token.length(); i < 6; i++) {
			token = token + "0";
		}

		String message = "the token is : " + token.substring(0, 3) + " " + token.substring(3) + "\r\n you have " + time
				+ "min to enter it before he became invalid";

		// prints debug info
		System.out.println("To: " + mailAddress);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(mailAddress);
		email.setSubject(subject);
		email.setText(message);

		// sends the e-mail
		mailSender.send(email);

		user.setId(Long.parseLong(id));
		user.setToken(token);
		user.setTokenTime(System.currentTimeMillis());
		
		//thread who delete token - dont work
//		Runnable execution = () -> {
//			try {
//				Thread.sleep(60000 * time);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			cleanToken();
//		};
//
//		new Thread(execution).start();

		// forwards to the view named "Result"
		return mv;
	}
	
	@RequestMapping(value = "/token",method = RequestMethod.GET)
	public String getFormToken(HttpServletRequest request) {
		return "tokenForm";
	}
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ModelAndView doResetPassword(HttpServletRequest request) throws DaoException {
		ModelAndView mv = new ModelAndView("index");
		String token = request.getParameter("token");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		logger.info(user.getToken());
		logger.info(token);
		if(!password1.equals(password2)){
			mv = new ModelAndView("tokenForm");
			mv.addObject("error", "les mots de passe sont differents");
		}
		else if((System.currentTimeMillis() - user.getTokenTime()) > 60000 * time){
			user.setToken(null);
			user.setTokenTime(0);
			mv = new ModelAndView("index");
			mv.addObject("error", "token expiré");
		}
		else if((user.getToken() == null)){
			mv = new ModelAndView("index");
			mv.addObject("error", "token expiré");
		}
		else if(!(user.getToken().equals(token))){
			mv = new ModelAndView("tokenForm");
			mv.addObject("error", "mauvais token");
		} else {
			user.setToken(null);
			user.setTokenTime(0);
			Person personToEdit = manager.findPerson(mailWorker, user.getId());
			mailWorker.setPassword(password1);
			manager.savePerson(mailWorker, personToEdit);
			mv.addObject("error", "mot de passe modifier");
		}
		return mv;
	}	
	
	private void cleanToken(){
		user.setToken(null);
	}
}