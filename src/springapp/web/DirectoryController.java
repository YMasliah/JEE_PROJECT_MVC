package springapp.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.User;
import directory.manager.imp.Manager;

@Controller()
@RequestMapping("/directory")
public class DirectoryController {
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    Manager manager;
    
    @ModelAttribute
    public Group newGroup( @RequestParam(value = "id", required = false) Integer groupId) throws DaoException {
    	Group returnValue = new Group();
    	if (groupId != null) {
    		returnValue = manager.findGroup(new User(), groupId);
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
    
    @ModelAttribute
    public Person newPerson( @RequestParam(value = "id", required = false) Integer personId) throws DaoException {
    	Person returnValue = new Person(10,"Masliah", "yann", "22/03/1991", "toto@gmail.com", "", "tata", 1);
    	if (personId != null) {
    		//pour que sa compile
    		//returnValue = manager.findPerson(new User(), personId);
    		returnValue = new Person();
        }
        logger.info("new product = " + returnValue);
        return returnValue;
    }
    
    @ModelAttribute
    public User newUser() {
        User user = new User();
        user.setId(2);
        user.setPassword("toto");
        logger.info("new product = " + user);
        return user;
    }
    
    @ModelAttribute("groups")
    public Collection<Group> newGroup() throws DaoException{
    	Map<Integer, Group> returnValue = new HashMap<Integer,Group>();
    	HashMap<Integer,Person> persons = new HashMap<>();
    	Person person = new Person();
    	person.setLastName("toto");
    	persons.put(1,new Person());
    	persons.put(2,person);
    	Group g1 = new Group(1, "isl", null);
    	g1.setPersons(persons.values());
    	returnValue.put(g1.getId(), g1);
    	Group g2 = new Group(2, "fsi", null);
    	returnValue.put(g2.getId(), g2);
    	//return manager.findAllGroup(new User());
        return returnValue.values();
    }
    
    @RequestMapping(value = "/groupList", method = RequestMethod.GET)
    public String groupListRequest( ){
        return "groupList";
    }
    
    @RequestMapping(value = "/group/edit", method = RequestMethod.GET)
    public String groupEditRequest(){
    	
        logger.info("Returning groupList view " );

        return "groupEdit";
    }
    
    @RequestMapping(value = "/group/edit", method = RequestMethod.POST)
    public String groupEditReply(@ModelAttribute @Valid Group p, BindingResult result){
        if (result.hasErrors()) {
            return "groupEdit";
        }
        //pas fini
        manager.saveGroup(new User(), p);
        return "groupList";
    }
    
    @RequestMapping(value = "/group/view", method = RequestMethod.GET)
    public String groupViewRequest(){
    	
        logger.info("Returning group view " );

        return "group";
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
    public String personEditRequest(@ModelAttribute @Valid Group p, BindingResult result){
        return "personEdit";
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public ModelAndView personEditReply(@ModelAttribute @Valid Person p, BindingResult result) throws DaoException{
        if (result.hasErrors()) {
        	return new ModelAndView("personEdit");
        }
        //pas fini
        manager.savePerson(new User(), p);
        return new ModelAndView("group", "group", newGroup((int) p.getGroupId()));
    }
}

