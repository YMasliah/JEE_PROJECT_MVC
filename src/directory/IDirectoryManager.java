package directory;

import java.util.Collection;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

public interface IDirectoryManager {

    // créer un utilisateur anonyme
	User newUser() throws managerException;

    // chercher une personne
    Person findPerson(User user, long personId) throws DaoException;

    // chercher un groupe
    Group findGroup(User user, long groupId) throws DaoException;

    // chercher les personnes d'un groupe
    Collection<Person> findAllPersons(User user, long groupId)throws DaoException;

    // identifier un utilisateur
	boolean login(User user) throws DaoException;

    // oublier l'utilisateur
    void logout(User user);

    // enregistrer une personne
    void savePerson(User user, Person p) throws DaoException;

	Collection<Group> findAllGroup(User user) throws DaoException;
}