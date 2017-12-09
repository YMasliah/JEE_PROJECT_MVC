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
import springapp.web.IMailController;

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec l'utilisateur
 * 
 * Controlleur spring qui fournis toute les fonctionnalitée disponibles qui
 * concernent l'object la recuperation de mot de passe
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
@Controller
@RequestMapping("/directory/password")
public class MailController extends BaseController implements IMailController {

	private final String subject = "JEE : Reset password token";
	private final User mailWorker = new User();
	
	// minutes before token to be deleted
	private final int time = 5;

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * @see springapp.web.controller.IMailController#init()
	 */
	@Override
	@PostConstruct
	public void init() {
		mailWorker.setName("mailWorker");
	}

	/**
	 * @see springapp.web.controller.IMailController#getFormEmail(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public String getFormEmail() {
		return "passwordLost";
	}

	/**
	 * @see springapp.web.controller.IMailController#doSendEmail(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView doSendEmail(HttpServletRequest request)
			throws NumberFormatException, DaoException, InterruptedException {
		ModelAndView mv = new ModelAndView("tokenForm");
		String mailAddress = request.getParameter("email");
		String id = request.getParameter("id");

		if (!id.isEmpty() && !id.equals("")) {
			if (!mailAddress.isEmpty() && !mailAddress.equals("")) {
				String databaseMail = manager.findPerson(mailWorker, Long.parseLong(id)).getEmail();
				if (databaseMail == null || !databaseMail.equals(mailAddress)) {
					mv = new ModelAndView("passwordLost");
					mv.addObject("error_pwd", "yes");
					mv.addObject("notify_pwd", "ID et/ou EMAIL non valid ou bien ne correspendent pas à un utilisateur.");
					return mv;
				}
			} else {
				mv = new ModelAndView("passwordLost");
				mv.addObject("error_pwd", "yes");
				mv.addObject("notify_pwd", "le champ ADRESSE MAIL est obligatoir.");
				return mv;
			}
		} else {
			mv = new ModelAndView("passwordLost");
			mv.addObject("error_pwd", "yes");
			mv.addObject("notify_pwd", "le champ ID est obligatoir.");
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
		return mv;
	}

	/**
	 * @see springapp.web.controller.IMailController#getFormToken(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String getFormToken(HttpServletRequest request) {
		return "tokenForm";
	}

	/**
	 * @see springapp.web.controller.IMailController#doResetPassword(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ModelAndView doResetPassword(HttpServletRequest request) throws DaoException {
		ModelAndView mv = new ModelAndView("index");
		String token = request.getParameter("token");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		logger.info(user.getToken());
		logger.info(token);
		if(password1.isEmpty() || password2.isEmpty()){
			mv = new ModelAndView("tokenForm");
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "Les mots de passe sont obligatoires");
		} else if (!password1.equals(password2)) {
			mv = new ModelAndView("tokenForm");
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "Les mots de passe sont differents");
		} else if ((System.currentTimeMillis() - user.getTokenTime()) > 60000 * time) {
			user.setToken(null);
			user.setTokenTime(0);
			mv = new ModelAndView("index");
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "Code expiré");
		} else if ((user.getToken() == null)) {
			mv = new ModelAndView("index");
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "Code expiré");
		} else if (!(user.getToken().equals(token))) {
			mv = new ModelAndView("tokenForm");
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "Mauvais Code");
		} else {
			user.setToken(null);
			user.setTokenTime(0);
			Person personToEdit = manager.findPerson(mailWorker, user.getId());
			mailWorker.setPassword(password1);
			manager.savePerson(mailWorker, personToEdit);
			mv.addObject("error_token", "yes");
			mv.addObject("notify_token", "mot de passe modifier");
		}
		return mv;
	}
}