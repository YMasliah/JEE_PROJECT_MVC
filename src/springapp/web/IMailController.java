package springapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;

public interface IMailController {

	/**
	 * crée un utilisateur factice pour verifier les données entrer par l'utilisateur
	 */
	void init();

	/**
	 * renvoi la page de formulaire a remplir pour recuperer le mot de passe
	 * 
	 * @return la page concernee
	 */
	String getFormEmail();

	/**
	 * envoi un email pour reinitialiser le mot de passe si les informations sont correcte
	 * 
	 * @param request informations de session necessaire
	 * @return la page concernee
	 * @throws NumberFormatException
	 * @throws DaoException
	 * @throws InterruptedException
	 */
	ModelAndView doSendEmail(HttpServletRequest request)
			throws NumberFormatException, DaoException, InterruptedException;

	/**
	 * recupere le token recu par mail et le nouveau mot de passe du compte puis envoi l'adresse 
	 * 
	 * @param request informations de session necessaire
	 * @return la page concernee
	 */
	String getFormToken(HttpServletRequest request);

	ModelAndView doResetPassword(HttpServletRequest request) throws DaoException;

}