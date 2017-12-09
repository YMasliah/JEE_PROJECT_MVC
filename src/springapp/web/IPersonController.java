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
	 * renvoi l'adresse du formulaire pour editer une personne
	 * 
	 * @param personId l'identifiant de la perssonne a modifier
	 * @return les informations de la personne a modifier avec l'adresse de la page
	 * @throws DaoException
	 */
	ModelAndView getPersonEdit(Long personId) throws DaoException;

	/**
	 * Soumet les modification de la personne qui a ete modfi�e
	 * 
	 * @param p les donn�es de la personne modifi�e
	 * @param result les tests de validit�e des variables
	 * @param groupName le groupe auquel la personne apartient
	 * @return la page conern�e suivant la reussite de la requete ou non
	 * @throws DaoException
	 */
	ModelAndView postPersonEdit(Person p, BindingResult result, String groupName) throws DaoException;

}