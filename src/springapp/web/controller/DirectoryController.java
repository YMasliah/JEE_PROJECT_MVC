package springapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.exception.DaoException;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

/**
 * jdbc:embedded-database
 * 
 * @author m21002022
 *
 */
@Controller()
@RequestMapping("/directory")
public class DirectoryController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String show() throws managerException, DaoException {
		logger.info("show user " + user);
		if (user.getId() == null) {
			user = manager.newUser(user);
		} else {
			user = manager.login(user);
		}
		logger.info("show user " + user);
		return "index";
	}

	/**
	 * plein de truc a faire ici
	 * 
	 * @return
	 * @throws managerException
	 * @throws DaoException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User u, BindingResult result) throws DaoException, managerException {
		if (result.hasErrors()) {
			return "index";
		}
		logger.info("pre-login user " + user);
		user = manager.login(u);
		logger.info("post-login user " + user);
		return "redirect:login";
	}

	@RequestMapping(value = "/passwordLost", method = RequestMethod.GET)
	public String newUserRequest() {
		return "passwordLost";
	}
}
