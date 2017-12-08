package springapp.web;

import org.springframework.web.servlet.ModelAndView;

import dao.exception.DaoException;

public interface IGroupController {

	/**
	 * recupere la page affichant la liste des groupes disponible.
	 * il peut y avoir plusieurs pages
	 * 
	 * @param page numero de la page
	 * @return la page a afficher, avec les informations necessaires.
	 * @throws DaoException
	 */
	ModelAndView getGroupList(Integer page) throws DaoException;

	/**
	 * recupere la page affichant la liste des personnes disponibles dans un groupe
	 * il peut y avoir plusieurs pages.
	 * 
	 * @param groupId identifiant du groupe a afficher
	 * @param page numero de la page
	 * @return la page a afficher, avec les informations necessaires.
	 * @throws DaoException
	 */
	ModelAndView getGroupView(Long groupId, Integer page) throws DaoException;

}