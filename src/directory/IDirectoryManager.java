package directory;

import java.util.Collection;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

public interface IDirectoryManager {

    // créer un utilisateur anonyme
	User newUser(User user) throws managerException;

    // chercher une personne
    Person findPerson(User user, long personId) throws DaoException;

    // chercher un groupe
    Group findGroup(User user, long groupId) throws DaoException;

    // chercher les personnes d'un groupe
    Collection<Person> findAll(User user, long groupId, int page)throws DaoException;

    // chercher tout les groupes
	Collection<Group> findAll(User user, int page) throws DaoException;
    
    // identifier un utilisateur
	User login(User user) throws DaoException, managerException;

    // oublier l'utilisateur
    void logout(User user) throws managerException;

    // enregistrer une personne
    void savePerson(User user, Person p) throws DaoException;

    // enregistrer un groupe
	void saveGroup(User user, Group p) throws DaoException;
	
	Group findGroup(User user, String name) throws DaoException;

	Collection<Group> findGroup(User user, String name, int page) throws DaoException;

	Collection<Person> findPerson(User user, String lastName, int page) throws DaoException;

	
}