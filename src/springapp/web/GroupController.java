package springapp.web;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.manager.imp.Manager;

@Controller()
@RequestMapping("/directory/group")
public class GroupController extends BaseController{

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	Manager manager;

	@ModelAttribute("groups")
	public Collection<Group> newGroup() throws DaoException {
		return manager.findAll(user);
		// return returnValue.values();
	}

    @ModelAttribute
    public Group newGroup( @RequestParam(value = "id", required = false) Long groupId) throws DaoException {
    	Group returnValue = new Group();
    	if (groupId != null) {
    		returnValue = manager.findGroup(user, groupId.longValue());
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String groupListRequest() {
		logger.info("Returning groupList view ");
		return "groupList";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String groupEditRequest(@ModelAttribute Group p) {
		logger.info("Returning groupEdit view");
		return "groupEdit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String groupEditReply(@ModelAttribute @Valid Group g, BindingResult result) throws DaoException {
		if (result.hasErrors()) {
			return "groupEdit";
		}
		logger.info("Returning groupEdit view ");
		// pas fini
		manager.saveGroup(user, g);
		return "groupList";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String groupViewRequest() {
		logger.info("Returning group view ");
		return "group";
	}

}
