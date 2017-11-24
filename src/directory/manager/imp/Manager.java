package directory.manager.imp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDao;
import dao.exception.DaoException;
//import dao.imp.Dao;
import directory.IDirectoryManager;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

@Service
public class Manager implements IDirectoryManager {

	/**
	 * a tout les coups faut sortir les 2 variables
	 */
	private LinkedHashMap<User, Boolean> userList = new LinkedHashMap<>();

	@Autowired
	private IDao dao;

	public Manager() {

	}

	@Override
	public User newUser() throws managerException {
		return new User();
	}

	@Override
	public Person findPerson(User user, long personId) throws DaoException {
		Person returnValue = new Person();
		if (userList.get(user)) {
			returnValue = dao.findPerson(personId);
		}
		return returnValue;
	}

	@Override
	public Group findGroup(User user, long groupId) throws DaoException {
		Group returnValue = new Group();
		if (userList.get(user)) {
			returnValue = dao.findGroup(groupId);
		}
		return returnValue;
	}

	@Override
	public Collection<Person> findAllPersons(User user, long groupId) throws DaoException {
		Collection<Person> returnValue = Collections.emptyList();
		if (userList.get(user)) {
			returnValue = dao.findAllPersons(groupId);
		}
		return returnValue;
	}

	@Override
	public Collection<Group> findAllGroup(User user) throws DaoException {
		Collection<Group> returnValue = Collections.emptyList();
		if (userList.get(user)) {
			returnValue = dao.findAllGroups();
		}
		return returnValue;
	}

	/**
	 * faut check la base de donnée si la perssonne existe
	 * 
	 * @throws DaoException
	 */
	@Override
	public boolean login(User user) throws DaoException {
		boolean returnValue = false;
		Person toTest = dao.findPerson(user.getId());
		if (toTest.getPassword() == user.getPassword()) {
			if (userList.get(user) != true) {
				userList.put(user, true);
				returnValue = true;
			}
		}
		return returnValue;
	}

	/**
	 * faire une petite trace quand meme
	 */
	@Override
	public void logout(User user) {
		if (userList.get(user)) {
			userList.put(user, false);
			user = new User();
		}
	}

	@Override
	public void savePerson(User user, Person p) throws DaoException {
		// if(userList.get(user)){
		// dao.savePerson(p);
		// }
	}

	public void saveGroup(User user, Group p) {
		// TODO Auto-generated method stub

	}
}

/*
 * Date date = null; try { date = sdf.parse("22/03/1991"); } catch
 * (ParseException e) { e.printStackTrace(); } returnValue = new Person(10,
 * "MASLIAH", "Yann", date, "toto@gmail.com", "apas", "tata", 0L);
 */

/**
 * faut check la base de donnée si la perssonne existe
 */
/*
 * @Override public User newUser(long personId, String lastName, String
 * password) throws managerException { User newUser = new
 * User(personId,password); newUser.setName(lastName);
 * if(userList.get(newUser)!=null){ throw new managerException(); }
 * userList.put(newUser, false); return newUser; }
 */

/*
 * Date date = null; try { date = sdf.parse("22/03/1991"); } catch
 * (ParseException e) { e.printStackTrace(); }
 * 
 * userList.put(new User(1, "vtff"),true); HashMap<Integer,Person> persons = new
 * HashMap<>();
 * 
 * Person person = new Person(10, "MASLIAH", "Yann", date, "toto@gmail.com",
 * "apas", "tata", 0L); persons.put(1,new Person()); persons.put(2,person);
 * Group g1 = new Group(1, "isl"); toto.put(g1.getId(), g1); Group g2 = new
 * Group(2, "fsi"); toto.put(g2.getId(), g2); groups = toto.values();
 */

// mumuse sans dao
/*
 * Map<Long, Group> toto = new HashMap<>(); Collection<Group> groups;
 * SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
 */
