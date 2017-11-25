package springapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import directory.manager.beans.User;
import directory.manager.imp.Manager;

public abstract class BaseController {
	protected final Log logger = LogFactory.getLog(getClass());
	
    @Autowired
    Manager manager;
	
    @Autowired()
    User user;

    @ModelAttribute("user")
    public User newUser() {
        return user;
    }
    
    @InitBinder
    public void initBinder(final WebDataBinder binder){
      final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/logout")
    public String logout() {
        logger.info("logout user " + user);
        user = new User();
        return "redirect:login";
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
