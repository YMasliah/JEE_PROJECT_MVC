package dao;

import java.util.Collection;

import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;

public interface IDao {

	// r�cup�rer les groupes
	public Collection<Group> findAll(int page) throws DaoException;
	
	public Collection<Group> findAll() throws DaoException;

	// r�cup�rer les personnes d'un groupe
	public Collection<Person> findAll(long groupId, int page) throws DaoException;

	// lire une personne
	public Person findPerson(long id) throws DaoException;

	public Collection<Person> findPerson(String Name, int page) throws DaoException;
	
	// lire une personne
	public Group findGroup(long id) throws DaoException;
	
	public Group findGroup(String name) throws DaoException;
	
	public Collection<Group> findGroup(String Name, int page) throws DaoException;

	// modification ou ajout d'une nouvelle personne
	public void saveBean(Person p) throws DaoException;

	// modification ou ajout d'un nouveau groupe
	public void saveBean(Group g) throws DaoException;

	// supprime une personne
	public void removePerson(long id) throws DaoException;

	// supprime une personne
	public void removeGroup(long id) throws DaoException;
}
