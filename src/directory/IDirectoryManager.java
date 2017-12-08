package directory;

import java.util.Collection;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec la Dao
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
public interface IDirectoryManager {

	/**
	 * cr�er un utilisateur anonyme
	 * 
	 * @param user utilisateur subissant la modification de droit
	 * @return utilisateur modifi�
	 * @throws managerException
	 */
	User newUser(User user) throws managerException;

	/**
	 * chercher une personne
	 * 
	 * @param user
	 * @param personId
	 * @return
	 * @throws DaoException
	 */
	Person findPerson(User user, long personId) throws DaoException;

	/**
	 * 
	 * @param user
	 * @param lastName
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	Collection<Person> findPerson(User user, String lastName, int page) throws DaoException;
	
	/**
	 * chercher un groupe
	 * 
	 * @param user
	 * @param groupId
	 * @return
	 * @throws DaoException
	 */
	Group findGroup(User user, long groupId) throws DaoException;

	/**
	 * 
	 * @param user
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	Group findGroup(User user, String name) throws DaoException;
	
	/**
	 * 
	 * @param user
	 * @param name
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	Collection<Group> findGroup(User user, String name, int page) throws DaoException;
	
	/**
	 * chercher les personnes d'un groupe
	 * 
	 * @param user
	 * @param groupId
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	Collection<Person> findAll(User user, long groupId, int page) throws DaoException;

	/**
	 * chercher tout les groupes
	 * 
	 * @param user
	 * @param page
	 * @return
	 * @throws DaoException
	 */
	Collection<Group> findAll(User user, int page) throws DaoException;

	/**
	 * identifier un utilisateur
	 * 
	 * @param user
	 * @return
	 * @throws DaoException
	 * @throws managerException
	 */
	User login(User user) throws DaoException, managerException;

	/**
	 * oublier l'utilisateur
	 * 
	 * @param user
	 * @throws managerException
	 */
	void logout(User user) throws managerException;

	/**
	 * enregistrer une personne
	 * 
	 * @param user
	 * @param p
	 * @throws DaoException
	 */
	void savePerson(User user, Person p) throws DaoException;

}