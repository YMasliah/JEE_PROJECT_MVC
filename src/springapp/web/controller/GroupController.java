package springapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Group;
import springapp.web.IGroupController;

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec l'utilisateur
 * 
 * Controlleur spring qui fournis toute les fonctionnalitée disponibles qui
 * concernent l'object Group
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
@Controller()
@RequestMapping("/directory/group")
public class GroupController extends BaseController implements IGroupController {

	/**
	 * @see springapp.web.controller.IGroupController#groupListRequestPage(java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public ModelAndView getGroupList(@PathVariable("page") Integer page) throws DaoException {
		logger.info("Returning groupList view ");
		logger.info(page);
		return new ModelAndView("groupList", "groups", manager.findAll(user, page));
	}

	/**
	 * @see springapp.web.controller.IGroupController#groupViewRequestPage(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	@RequestMapping(value = "/view/{page}", method = RequestMethod.GET)
	public ModelAndView getGroupView(@RequestParam(value = "id", required = true) Long groupId,
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
 