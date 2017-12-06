package dao;

import java.util.Collection;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;

/**
 * Master 2 ISL 2017/2018
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
public interface IDao {

	/**
	 * récuperer les groupes par page de X groupes
	 * 
	 * @param page
	 *            numero de la page de la recherche
	 * @return le resultat de la recherche
	 * 
	 */
	public Collection<Group> findAll(int page) throws DaoException;

	/**
	 * récuperer tout les groupes
	 * 
	 * @return le resultat de la recherche
	 * 
	 */
	public Collection<Group> findAll() throws DaoException;

	/**
	 * récuperer les personnes d'un groupe par page de X personnes
	 * 
	 * @param groupId
	 *            identifiant des personnes du groupe a chercher
	 * @param page
	 *            la page de la recherche
	 * @return le resultat de la recherche
	 * 
	 */
	public Collection<Person> findAll(long groupId, int page) throws DaoException;

	/**
	 * récuperer une personne par id
	 * 
	 * @param id
	 *            l'identifiant de la personne a chercher
	 * @return le resultat de la recherche
	 * 
	 */
	public Person findPerson(long id) throws DaoException;

	/**
	 * récuperer une liste personne contenant un String dans leur nom
	 * 
	 * @param Name
	 *            une partie du nom de famille de la personne a chercher
	 * @param page
	 *            le numero de la page de la recherche
	 * @return le resultat de la recherche
	 * 
	 */
	public Collection<Person> findPerson(String Name, int page) throws DaoException;

	/**
	 * récuperer un groupe par id
	 * 
	 * @param id
	 *            identifiant du groupe a chercher
	 * @return le resultat de la recherche
	 * 
	 */
	public Group findGroup(long id) throws DaoException;

	/**
	 * récuperer un groupe par nom
	 * 
	 * @param name
	 *            le nom du groupe a chercher
	 * @return resultat de la recherche
	 * 
	 */
	public Group findGroup(String name) throws DaoException;

	/**
	 * récuperer une liste de groupe contenant le string dans le nom, par page de X
	 * groupes
	 * 
	 * @param Name
	 *            une partie du nom du groupe a chercher
	 * @param page
	 *            page de la recherche
	 * @return resultat de la recherche
	 */
	public Collection<Group> findGroup(String Name, int page) throws DaoException;

	/**
	 * modification ou ajout d'une nouvelle personne
	 * 
	 * @param p
	 *            personne qui a ete editer a sauvegarder
	 */
	public void saveBean(Person p) throws DaoException;

	/**
	 * modification ou ajout d'un nouveau groupe
	 * 
	 * @param g
	 *            groupe qui a ete editer a sauvegarder
	 */
	public void saveBean(Group g) throws DaoException;

	public void removePerson(long id) throws DaoException;

	public void removeGroup(long id) throws DaoException;
}
