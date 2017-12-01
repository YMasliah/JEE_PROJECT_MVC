package springapp.web.controller;

import org.springframework.stereotype.Controller;
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
	public ModelAndView groupListRequest(@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws DaoException {
		logger.info("Returning groupList view ");
		return new ModelAndView("groupList", "groups", manager.findAll(user,page));
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView groupViewRequest(@RequestParam(value = "id", required = true) Long groupId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws DaoException {
		Group group = manager.findGroup(user, groupId);
		logger.info("Returning group view");
		ModelAndView mv = new ModelAndView("redirect:/actions/directory/login");
		if (group.getId() != null) {
			mv = new ModelAndView("group");
			mv.addObject("group", manager.findGroup(user, groupId));
			mv.addObject("persons", manager.findAll(user, groupId,page));
		} else {
			mv.addObject("error", "aucun groupe trouver");
		}
		return mv;
	}

}