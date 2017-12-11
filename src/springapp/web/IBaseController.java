package springapp.web;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.exception.DaoException;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

public interface IBaseController {

	/**
	 * renvoi les informations de sessions de l'utilisateur
	 * @return
	 */
	public User newUser();

	/**
	 * affiche la page d'acceuil et genere le contexte
	 * login automatiquement si l'utilisateur avais deja une session
	 * 
	 * @return la page d'index
	 * @throws managerException
	 * @throws DaoException
	 */
	public String show() throws managerException, DaoException;

	/**
	 * authentifie l'utilisateur
	 * 
	 * @return la page d'index avec les informations de connexion
	 * @throws managerException
	 * @throws DaoException
	 */
	public String login(User u, BindingResult result) throws DaoException, managerException;

	/**
	 * ouvre la page de recuperation de mot de passe
	 * 
	 * @return l'adresse de la page recuperation de mot de passe
	 */
	public String passwordLost();

	/**
	 * supprime les informations d'authentification et deconnecte l'utilisateur
	 * 
	 * @param redirectAttributes
	 * @return la page d'index
	 * @throws managerException
	 */
	public String logout(RedirectAttributes redirectAttributes) throws managerException;

	/**
	 * fournis la liste des types de recherche disponible
	 */
	public Map<String, String> productTypes();

	/**
	 * Fonction de recherche suivant les types d'objects disponible
	 * la recherche se fait avec l'identifiant, le nom et une portion du nom.
	 * si le resultat renvoi plus de 1 objet, une page fournis une liste permettant de choisir l'object a afficher. 
	 * 
	 * @param key la valeur a rechercher
	 * @param type la classe de l'object a rechercher
	 * @return la page correspodante a la recherche
	 * @throws DaoException
	 */
	public ModelAndView search(String key, String type, Integer page, RedirectAttributes redirectAttributes) throws DaoException;

}