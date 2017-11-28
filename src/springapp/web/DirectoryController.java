package springapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;
import directory.manager.imp.Manager;

@Controller()
@RequestMapping("/directory")
public class DirectoryController{
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    Manager manager;
    
    @InitBinder
    public void initBinder(final WebDataBinder binder){
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    /**
     * Group Controller
     * @throws DaoException 
     */
    
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public ModelAndView groupListRequest( ) throws DaoException{
    	logger.info("Returning groupList view " );
        return new ModelAndView("groupList","groups",manager.findAll(user));
    }
    
    @RequestMapping(value = "/group/view", method = RequestMethod.GET)
    public ModelAndView groupViewRequest(@RequestParam(value = "id", required = true) Long groupId) throws DaoException{
        logger.info("Returning group view " );
        ModelAndView mv  = new ModelAndView("group");
        mv.addObject("group", manager.findGroup(user, groupId));
        mv.addObject("persons", manager.findAll(user, groupId));
        return mv;
    }
    
    /**
     * Person Controller
     * @throws DaoException 
     */  
    
    @RequestMapping(value = "/person/view", method = RequestMethod.GET)
    public ModelAndView personViewRequest(@RequestParam(value = "id", required = true) Long personId) throws DaoException{
    	ModelAndView mv  = new ModelAndView("person");
        mv.addObject("person", manager.findPerson(user, personId));
        return mv;
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
    public ModelAndView personEditRequest(@RequestParam(value = "id", required = true) Long personId) throws DaoException{
    	ModelAndView mv  = new ModelAndView("personEdit");
    	if(personId != null){
    		mv.addObject("person", manager.findPerson(user, personId));
    	}else{
    		mv.addObject("person", new Person());
    	}
        return mv;
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public ModelAndView personEditReply(@ModelAttribute @Valid Person p, BindingResult result) throws DaoException{
        ModelAndView returnValue = new ModelAndView("groupList");
    	if (result.hasErrors()) {
        	return new ModelAndView("personEdit");
        }
    	if(p.getGroupId()!=null) {
    		manager.savePerson(user, p);
    		returnValue = new ModelAndView("group", "group", manager.findGroup(user, p.getGroupId()));
    		returnValue.addObject("persons", manager.findAll(user, p.getGroupId()));
    	}        
        return returnValue;
    }
    
/**
 * User Controller
 */
    
    @Autowired()
    User user;

    @ModelAttribute("user")
    public User newUser() {
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String show() throws managerException, DaoException {
        logger.info("show user " + user);
        if(user.getId()==null) {
        	user = manager.newUser();
        }else{
        	manager.login(user);
        }
        logger.info("show user " + user);
        return "index";
    }

    /**
     * plein de truc a faire ici
     * @return
     * @throws managerException 
     * @throws DaoException 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid User u, BindingResult result) throws DaoException, managerException {
    	if (result.hasErrors()) {
        	return "index";
        }
        logger.info("pre-login user " + user);
        user = manager.login(u);
        System.out.println(user);
        System.out.println(u);
        logger.info("post-login user " + user);
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout() throws managerException {
        logger.info("logout user " + user);
        user = manager.newUser();
        return "redirect:login";
    }
   
    @RequestMapping(value = "/passwordLost", method = RequestMethod.GET)
    public String newUserRequest() {
        return "passwordLost";
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
    public ModelAndView search(@RequestParam(value = "id") Long id, @RequestParam(value = "type") String type) throws DaoException{
    	ModelAndView mv  = new ModelAndView("index");
    	if(type.equals("Group")){
    		mv  = new ModelAndView("redirect:group/view");
    		mv.addObject("id", id);
    	}else if(type.equals("Person")){
    		mv  = new ModelAndView("redirect:person/view");
    		mv.addObject("id", id);
    	}
        return mv;
    }
    
}

