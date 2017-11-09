package springapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import directory.manager.User;
import directory.manager.imp.Manager;

/**
 * Servlet implementation class LoginController
 */

@Controller()
@RequestMapping("/header")
public class HeaderController{
	
    @Autowired
    Manager manager;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @ModelAttribute
    public User newUser() {
        User user = new User();
        user.setId(2);
        user.setPassword("toto");
        logger.info("new product = " + user);
        return user;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginRequest(@ModelAttribute User user) throws ServletException, IOException {   	
    	ModelAndView returnValue = new ModelAndView("index");
    	
    	if(manager.login(user)) {
    		logger.info("Returning index view with login in " + user );
    		returnValue = new ModelAndView("index","user",user);
    	} else {
    		logger.info("Returning index without login in" );
    	}
        
        return returnValue;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutRequest(@ModelAttribute User user) throws ServletException, IOException {
    	
    	//manager.logout(user);
    	user = new User();
        logger.info("Returning index view without user logged " );
        
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView passwordRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
        logger.info("Returning password view " );

        return new ModelAndView("password");
    }
    
    /**
     * elle est un peu compliquer celle la, on la fait a la fin. ca cerra du recyclage de ce qui existe normalement
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
        logger.info("Returning edition view " );

        return new ModelAndView("loginMenu");
    }
    
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView newUserRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
        logger.info("Returning newUser view " );

        return new ModelAndView("newUser");
    }
}
