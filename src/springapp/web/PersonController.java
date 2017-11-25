package springapp.web;

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
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;
import directory.manager.imp.Manager;

@Controller
@RequestMapping("/directory/person")
public class PersonController extends BaseController{
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    Manager manager;
    
    @ModelAttribute
    public Person newPerson( @RequestParam(value = "id", required = false) Long personId) throws DaoException {
    	Person returnValue = new Person();
    	if (personId != null) {
    		returnValue = manager.findPerson(user, personId.longValue());
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String personViewRequest(){
        return "person";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String personEditRequest(){
        return "personEdit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView personEditReply(@ModelAttribute @Valid Person p, BindingResult result) throws DaoException{
        ModelAndView returnValue = new ModelAndView("groupList");
    	if (result.hasErrors()) {
        	return new ModelAndView("personEdit");
        }
    	if(p.getGroupId()!=null) {
    		returnValue = new ModelAndView("group", "group", manager.findGroup(user, p.getGroupId()));
    	}
        //pas fini
        System.out.println(p);
        manager.savePerson(user, p);
        return returnValue;
    }
}
