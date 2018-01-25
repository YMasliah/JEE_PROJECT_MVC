package springapp.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;
import springapp.web.IPersonController;

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec l'utilisateur
 * 
 * Controlleur spring qui fournis toute les fonctionnalitée disponibles qui
 * concernent l'object Person
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
@Controller
@RequestMapping("/directory/person")
public class PersonController extends BaseController implements IPersonController {

	/**
	 * @see springapp.web.controller.IPersonController#getPersonView(java.lang.Long)
	 */
	@Override
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView getPersonView(@RequestParam(value = "id", required = true) Long personId) throws DaoException {
		Person person = manager.findPerson(user, personId);
		logger.info("Returning group view");
		ModelAndView mv = new ModelAndView("index");
		if (person.getId() != null) {
			mv = new ModelAndView("person");
			mv.addObject("person", person);
			mv.addObject("group", manager.findGroup(user, person.getGroupId()).getName());
		} else {
			mv = new ModelAndView("index");
		}
		return mv;
	}

	/**
	 * @see springapp.web.controller.IPersonController#getPersonEdit(java.lang.Long)
	 */
	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getPersonEdit(@RequestParam(value = "id", required = true) Long personId) throws DaoException {
		ModelAndView mv = new ModelAndView("index");
		Person person = manager.findPerson(user, personId);
		if (person.getLastName().equals(user.getName())) {
			mv = new ModelAndView("personEdit");
			if (personId != null) {
				mv.addObject("person", person);
				mv.addObject("group", manager.findGroup(user, person.getGroupId()).getName());
			}
		}
		return mv;
	}

	/**
	 * @see springapp.web.controller.IPersonController#postPersonEdit(directory.beans.Person,
	 *      org.springframework.validation.BindingResult, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView postPersonEdit(@ModelAttribute @Valid Person p, BindingResult result,
			@RequestParam(value = "group", required = true) String groupName) throws DaoException {
		ModelAndView returnValue = new ModelAndView("groupList");
		Long groupId = manager.findGroup(user, groupName).getId();
		if (result.hasErrors()) {
			returnValue = new ModelAndView("personEdit");
			returnValue.addObject("group", groupName);
			if (groupId == null) {
				ObjectError error = new ObjectError("group", "** Le nom du groupe n'est pas valide.");
				result.addError(error);
			}
		} else if (groupId == null) {
			returnValue = new ModelAndView("personEdit");
			returnValue.addObject("group", groupName);
			ObjectError error = new ObjectError("group", "** Le nom du groupe n'est pas valide.");
			result.addError(error);
		} else {
			p.setGroupId(groupId);
			manager.savePerson(user, p);
			returnValue = new ModelAndView("redirect:/actions/directory/group/view/1", "id", p.getGroupId());
		}
		return returnValue;
	}
}
