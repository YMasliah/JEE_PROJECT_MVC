package springapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.manager.beans.User;
import directory.manager.exception.managerException;
import directory.manager.imp.Manager;

public abstract class BaseController {
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

	@RequestMapping(value = "/logout")
	public String logout() throws managerException {
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
		types.put("Group", "Group");
		types.put("Person", "Person");
		return types;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(value = "key") String key, @RequestParam(value = "type") String type)
			throws DaoException {
		logger.info("clé recherchée" + key);
		ModelAndView mv = new ModelAndView("index");
		Long id = null;
		if (type.equals("Group")) {
			mv = new ModelAndView("redirect:group/view");
			try {
				id = Long.parseLong(key);
				mv.addObject("id", id);
			} catch (NumberFormatException e) {
				if ((id = manager.findGroup(user, key).getId()) != null) {
					mv.addObject("id", id);
				} else {
					mv = new ModelAndView("index");
					mv.addObject("error", "No result");
				}
			}
		} else if (type.equals("Person")) {
			mv = new ModelAndView("redirect:person/view");
			try {
				id = Long.parseLong(key);
				mv.addObject("id", id);
			} catch (NumberFormatException e) {
				if ((id = manager.findPerson(user, key).getId()) != null) {
					mv.addObject("id", id);
				} else {
					mv = new ModelAndView("index");
					mv.addObject("error", "No result");
				}
			}
			mv.addObject("id", id);
		}
		return mv;
	}

}
