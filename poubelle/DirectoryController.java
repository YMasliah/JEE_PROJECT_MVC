package springapp.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
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
import directory.beans.Group;
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
    
    @ModelAttribute
    public Group newGroup( @RequestParam(value = "id", required = false) Long groupId) throws DaoException {
    	Group returnValue = new Group();
    	if (groupId != null) {
    		returnValue = manager.findGroup(new User(), groupId.longValue());
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
    
    @ModelAttribute
    public Person newPerson( @RequestParam(value = "id", required = false) Long personId) throws DaoException {
    	Person returnValue = new Person();
    	if (personId != null) {
    		returnValue = manager.findPerson(new User(), personId.longValue());
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
    
    @InitBinder
    public void initBinder(final WebDataBinder binder){
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
//    @ModelAttribute
//    public User newUser() {
//        User user = new User();
//        user.setId(2);
//        user.setPassword("toto");
//        logger.info("new product = " + user);
//        return user;
//    }
    
    /**
     * Group Controller
     */
    
    @ModelAttribute("groups")
    public Collection<Group> newGroup() throws DaoException{
    	return manager.findAll(new User());
        //return returnValue.values();
    }
    
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public String groupListRequest( ){
    	logger.info("Returning groupList view " );
        return "groupList";
    }
    
    @RequestMapping(value = "/group/edit", method = RequestMethod.GET)
    public String groupEditRequest(@ModelAttribute Group p){
    	
        logger.info("Returning groupEdit view" );

        return "groupEdit";
    }
    
    @RequestMapping(value = "/group/edit", method = RequestMethod.POST)
    public String groupEditReply(@ModelAttribute @Valid Group g, BindingResult result) throws DaoException{
        if (result.hasErrors()) {
            return "groupEdit";
        }
        logger.info("Returning groupEdit view " );
        //pas fini
        manager.saveGroup(new User(), g);
        return "groupList";
    }
    
    @RequestMapping(value = "/group/view", method = RequestMethod.GET)
    public String groupViewRequest(){
    	
        logger.info("Returning group view " );

        return "group";
    }
    
    /**
     * Person Controller
     */  
    
    @RequestMapping(value = "/person/view", method = RequestMethod.GET)
    public String personViewRequest(){
        return "person";
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
    public String personEditRequest(){
        return "personEdit";
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public ModelAndView personEditReply(@ModelAttribute @Valid Person p, BindingResult result) throws DaoException{
        ModelAndView returnValue = new ModelAndView("groupList");
    	if (result.hasErrors()) {
        	return new ModelAndView("personEdit");
        }
    	if(p.getGroupId()!=null) {
    		returnValue = new ModelAndView("group", "group", newGroup(p.getGroupId()));
    	}
        //pas fini
        System.out.println(p);
        manager.savePerson(new User(), p);
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
    public String show() {
        logger.info("show user " + user);
        if(user.getId()==null) {
        	user.setName("No User");
        }
        return "index";
    }

    /**
     * plein de truc a faire ici
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        logger.info("login user " + user);
        user.setId(10L);
        user.setName("Masliah");
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        logger.info("logout user " + user);
        user = new User();
        return "redirect:login";
    }
    
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUserRequest() {
        return "newUser";
    }
    
    /**
     * appel du manager etc
     * faire plein de verif aussi
     * @return
     * @throws managerException 
     */
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ModelAndView newUserResponse(@ModelAttribute @Valid User u, BindingResult result) throws managerException {
        ModelAndView returnValue = new ModelAndView("index");
    	if (result.hasErrors()) {
        	return new ModelAndView("newUser");
        }
        //pas fini
    	//manager.newUser(u.getId(), u.getName(), u.getPassword());
        System.out.println(u);
        return returnValue;
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
    
    /*@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute @Valid Group g, BindingResult result){
        if (result.hasErrors()) {
            return "groupEdit";
        }
        logger.info("Returning groupEdit view " );
        //pas fini
        manager.saveGroup(new User(), g);
        return "groupList";
    }*/
    
}

