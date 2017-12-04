package springapp.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;

@Controller
@RequestMapping("/directory/person")
public class PersonController extends BaseController {
		
	/**
	 * Person Controller
	 * 
	 * @throws DaoException
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView personViewRequest(@RequestParam(value = "id", required = true) Long personId)
			throws DaoException {
		Person person = manager.findPerson(user, personId);
		logger.info("Returning group view");
		ModelAndView mv = new ModelAndView("index");
		if (person.getId() != null) {
			mv = new ModelAndView("person");
			mv.addObject("person", person);
			mv.addObject("group", manager.findGroup(user, person.getGroupId()).getName());
		} else {
			mv.addObject("error", "aucune personne trouver");
		}
		return mv;
	}

	/**
	 * faut modifier et lire la bd pour redirect si c pas lui
	 * 
	 * @param personId
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView personEditRequest(@RequestParam(value = "id", required = true) Long personId)
			throws DaoException {
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

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView personEditReply(@ModelAttribute @Valid Person p, BindingResult result,@RequestParam(value = "group", required = true) String groupName,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws DaoException {
		ModelAndView returnValue = new ModelAndView("groupList");
		Long groupId = manager.findGroup(user, groupName).getId();
		if (result.hasErrors() || groupId == null) {
			returnValue = new ModelAndView("personEdit", "error", "groupe inexistant");
			returnValue.addObject("group",groupName);
		}
		else if (groupId != null) {
			p.setGroupId(groupId);
			manager.savePerson(user, p);
			returnValue = new ModelAndView("group", "group", manager.findGroup(user, p.getGroupId()));
			returnValue.addObject("persons", manager.findAll(user, p.getGroupId(), page));
		}
		return returnValue;
	}
}
