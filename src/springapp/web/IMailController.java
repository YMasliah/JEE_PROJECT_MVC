package springapp.web;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;

public interface IMailController {

	void init();

	String getFormEmail(HttpServletRequest request);

	ModelAndView doSendEmail(HttpServletRequest request)
			throws NumberFormatException, DaoException, InterruptedException;

	String getFormToken(HttpServletRequest request);

	ModelAndView doResetPassword(HttpServletRequest request) throws DaoException;

}