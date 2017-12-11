package directory;

import java.util.Collection;

import dao.exception.DaoException;
import dao.imp.Dao;
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
	 * @param user
	 *            utilisateur subissant la modification de droit
	 * @return utilisateur modifiee
	 * @throws managerException
	 */
	User newUser(User user) throws managerException;

	/**
	 * chercher une personne
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param personId
	 *            identifiant de la personne a rechercher
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Person findPerson(User user, long personId) throws DaoException;

	/**
	 * chercher une liste de personnes contenant une chaine de caractere dans leur
	 * nom
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param lastName
	 *            la portion de texte devant se trouver dans le nom de famille
	 * @param page
	 *            le numero de la page si le resultat est trop grand
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Collection<Person> findPerson(User user, String lastName, int page) throws DaoException;

	/**
	 * chercher un groupe
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param groupId
	 *            identifiant du groupe a chercher
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Group findGroup(User user, long groupId) throws DaoException;

	/**
	 * chercher un groupe
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param name
	 *            nom du groupe a chercher
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Group findGroup(User user, String name) throws DaoException;

	/**
	 * chercher des groupes
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param name
	 *            portion du nom des groupe chercher
	 * @param page
	 *            le numero de la page si le resultat est trop grand
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Collection<Group> findGroup(User user, String name, int page) throws DaoException;

	/**
	 * chercher les personnes d'un groupe
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param groupId
	 *            identifiant du groupe a chercher
	 * @param page
	 *            le numero de la page si le resultat est trop grand
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Collection<Person> findAll(User user, long groupId, int page) throws DaoException;

	/**
	 * chercher tous les groupes
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param page
	 *            le numero de la page si le resultat est trop grand
	 * @return le resultat de la requete
	 * @see Dao
	 * @throws DaoException
	 */
	Collection<Group> findAll(User user, int page) throws DaoException;

	/**
	 * identifier un utilisateur
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @return les informations de l'utilisateur modifiée, Nom = "No user" si
	 *         echec, Nom = "<nom de l'utilisateur>" si reussite
	 * @throws DaoException
	 * @throws managerException
	 */
	User login(User user) throws DaoException, managerException;

	/**
	 * oublier l'utilisateur remise des parametres par default
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @throws managerException
	 */
	void logout(User user) throws managerException;

	/**
	 * enregistrer une personne
	 * 
	 * @param user
	 *            les informations de l'utilisateur lancant la requete
	 * @param p
	 *            les informations de la personne a modifier
	 * @throws DaoException
	 */
	void savePerson(User user, Person p) throws DaoException;

}