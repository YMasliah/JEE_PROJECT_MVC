package springapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/directory")
public class DirectoryController extends BaseController{

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
}

