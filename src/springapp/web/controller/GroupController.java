package springapp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Group;

@Controller()
@RequestMapping("/directory/group")
public class GroupController extends BaseController {
	/**
	 * Group Controller
	 * 
	 * @throws DaoException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView groupListRequest(@RequestParam(value = "page", required = false, defaultValue = "1") int page)
			throws DaoException {
		logger.info("Returning groupList view ");
		logger.info(page);
		return new ModelAndView("groupList", "groups", manager.findAll(user, page));
	}

	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public ModelAndView groupListRequestPage(@PathVariable("page") Integer page) throws DaoException {
		logger.info("Returning groupList view ");
		logger.info(page);
		return new ModelAndView("groupList", "groups", manager.findAll(user, page));
	}

	@RequestMapping(value = "/view/{page}", method = RequestMethod.GET)
	public ModelAndView groupViewRequestPage(@RequestParam(value = "id", required = true) Long groupId,
			@PathVariable("page") Integer page) throws DaoException {
		Group group = manager.findGroup(user, groupId);
		logger.info("Returning group view");
		ModelAndView mv = new ModelAndView("redirect:/actions/directory/login");
		if (group.getId() != null) {
			mv = new ModelAndView("group");
			mv.addObject("group", manager.findGroup(user, groupId));
			mv.addObject("persons", manager.findAll(user, groupId, page));
			mv.addObject("type_notify", "success");
			mv.addObject("notify", "Recherche réussite");
		} else {
			mv = new ModelAndView("index");
			mv.addObject("type_notify", "danger");
			mv.addObject("notify", "Aucun Groupe trouvé.");
		}
		return mv;
	}

}
