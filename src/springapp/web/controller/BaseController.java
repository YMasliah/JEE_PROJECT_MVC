package springapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;
import directory.manager.imp.Manager;

@Controller()
@RequestMapping("/directory")
public class BaseController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	Manager manager;

	@Autowired()
	User user;

	@ModelAttribute("user")
	public User newUser() {
		return user;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String show() throws managerException, DaoException {
		logger.info("show user " + user);
		if(user.getName() != "No User"){
			
		}
		else if (user.getId() == null || user.getPassword() == null) {
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
		if (u.getId() == null || u.getPassword() == null) {
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
	
	/**
	 * <c:out value="${error }"></c:out>
	 * 
	 * @param redirectAttributes
	 * @return
	 * @throws managerException
	 */
	@RequestMapping(value = "/logout")
	public String logout(RedirectAttributes redirectAttributes) throws managerException {
		redirectAttributes.addFlashAttribute("error", "vous vous etes deco");
		logger.info("logout user " + user);
		user = manager.newUser(user);
		return "redirect:login";
	}

	/**
	 * recherche Controller
	 */
	@ModelAttribute("dataTypes")
	public Map<String, String> productTypes() {
		Map<String, String> types = new LinkedHashMap<>();
		types.put("Person", "Person");
		types.put("Group", "Group");
		return types;
	}

	/**
	 * Recherche par nom une perssonne non implementer, Pour implementer la
	 * recherche il faut crée une page liste de personne. page non requise dans
	 * le cahier des charges.
	 * 
	 * @param key
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(value = "/search/{page}", method = {RequestMethod.POST, RequestMethod.GET} )
	public ModelAndView search(@RequestParam(value = "key") String key, @RequestParam(value = "type") String type, @PathVariable("page") Integer page)
			throws DaoException {
		logger.info("clé recherchée :" + key);
		ModelAndView mv = new ModelAndView("index");
		Long id = null;
		if (type.equals("Group")) {
			mv = new ModelAndView("redirect:/actions/directory/group/view/1");
			try {
				id = Long.parseLong(key);
				mv.addObject("id", id);
			} catch (NumberFormatException e) {
				List<Group> groupList = (List<Group>) manager.findGroup(user, key, page);
				if (groupList.size() == 1) {
					mv.addObject("id", groupList.get(0).getId());
				} else if (groupList.isEmpty()) {
					mv = new ModelAndView("index");
					mv.addObject("error", "No result");
				} else {
					mv = new ModelAndView("searchResultList");
					mv.addObject("groups", groupList);
				}
			}
		} else if (type.equals("Person")) {
			try {
				mv = new ModelAndView("redirect:/actions/directory/person/view");
				id = Long.parseLong(key);
				mv.addObject("id", id);
			} catch (NumberFormatException e) {
				List<Person> personList = (List<Person>) manager.findPerson(user, key, page);
				if (personList.size() == 1) {
					mv.addObject("id", personList.get(0).getId());
				} else if (personList.isEmpty()) {
					mv = new ModelAndView("index");
					mv.addObject("error", "No result");
				} else {
					mv = new ModelAndView("searchResultList");
					mv.addObject("persons", personList);
				}
			}
		}
		return mv;
	}
}
