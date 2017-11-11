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
import org.springframework.web.servlet.ModelAndView;

import directory.beans.Person;
import directory.manager.beans.User;

public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());
//
//    @Autowired()
//    User user;
//
//    @ModelAttribute("user")
//    public User newUser() {
//        return user;
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String show() {
//        logger.info("show user " + user);
//        if(user.getId()==null) {
//        	user.setName("No User");
//        }
//        return "index";
//    }
//
//    /**
//     * plein de truc a faire ici
//     * @return
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login() {
//        logger.info("login user " + user);
//        user.setId(10L);
//        user.setName("Masliah");
//        return "index";
//    }
//
//    @RequestMapping(value = "/logout")
//    public String logout() {
//        logger.info("logout user " + user);
//        user = new User();
//        return "redirect:login";
//    }
//    
//    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
//    public String newUserRequest() {
//        return "newUser";
//    }
//    
//    /**
//     * appel du manager etc
//     * @return
//     */
//    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
//    public ModelAndView newUserResponse(@ModelAttribute @Valid User u, BindingResult result) {
//        ModelAndView returnValue = new ModelAndView("index");
//    	if (result.hasErrors()) {
//        	return new ModelAndView("newUser");
//        }
//        //pas fini
//        System.out.println(u);
//        return returnValue;
//    }
}