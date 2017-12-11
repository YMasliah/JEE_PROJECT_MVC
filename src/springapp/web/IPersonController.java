package springapp.web;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;
import directory.beans.Person;

public interface IPersonController {

	/**
	 * renvoi l'adresse de la page qui affiche les informations d'une personne
	 * 
	 * @param personId l'identifiant de la personne a afficher
	 * @return l'adresse de la page avec les informations de la personne
	 * @throws DaoException
	 */
	ModelAndView getPersonView(Long personId) throws DaoException;

	/**
	 * renvoi l'adresse du formulaire
	 * 
	 * @param personId
	 * @return
	 * @throws DaoException
	 */
	ModelAndView getPersonEdit(Long personId) throws DaoException;

	/**
	 * 
	 * @param p
	 * @param result
	 * @param groupName
	 * @return
	 * @throws DaoException
	 */
	ModelAndView postPersonEdit(Person p, BindingResult result, String groupName) throws DaoException;

}