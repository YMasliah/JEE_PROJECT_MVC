package directory.manager.imp;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDao;
import dao.exception.DaoException;
import dao.imp.Dao;
import directory.IDirectoryManager;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

@Service
public class Manager implements IDirectoryManager {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IDao dao;

	public Manager() {

	}

	@Override
	public User newUser(User user) throws managerException {
		User returnValue = user;
		returnValue.setName("No User");
		returnValue.setId(null);
		returnValue.setPassword(null);
		return returnValue;
	}

	@Override
	public Person findPerson(User user, long personId) throws DaoException {
		Person returnValue = new Person();
		if (user.getName() != "No User") {
			returnValue = dao.findPerson(personId);
		}
		return returnValue;
	}

	@Override
	public Group findGroup(User user, long groupId) throws DaoException {
		Group returnValue = new Group();
		logger.info(user);
		if (user.getName() != "No User") {
			returnValue = dao.findGroup(groupId);
		}
		return returnValue;
	}

	@Override
	public Person findPerson(User user, String lastName) throws DaoException {
		Person returnValue = new Person();
		if (user.getName() != "No User") {
			returnValue = dao.findPerson(lastName);
		}
		return returnValue;
	}

	@Override
	public Group findGroup(User user, String name) throws DaoException {
		Group returnValue = new Group();
		if (user.getName() != "No User") {
			returnValue = dao.findGroup(name);
		}
		return returnValue;
	}

	@Override
	public Collection<Person> findAll(User user, long groupId, int page) throws DaoException {
		Collection<Person> returnValue = Collections.emptyList();
		if (user.getName() != "No User") {
			returnValue = dao.findAll(groupId, page);
		}
		return returnValue;
	}

	@Override
	public Collection<Group> findAll(User user, int page) throws DaoException {
		Collection<Group> returnValue = Collections.emptyList();
		if (user.getName() != "No User") {
			returnValue = dao.findAll(page);
		}
		return returnValue;
	}

	/**
	 * faut check la base de donnée si la perssonne existe
	 * 
	 * @throws DaoException
	 * @throws managerException
	 */
	@Override
	public User login(User user) throws DaoException, managerException {
		User returnValue = user;
		if (user.getId() != null && !user.getPassword().isEmpty()) {
			Person person = dao.findPerson(user.getId());
			logger.info(user);
			if (person.getPassword().equals(user.getPassword())) {
				returnValue.setName(person.getLastName());
				if (user.getName() != "No User") {
					logger.info("succes login");
					user.setName(person.getLastName());
				}
			}
		}
		return returnValue;
	}

	/**
	 * faire une petite trace quand meme
	 * 
	 * @throws managerException
	 */
	@Override
	public void logout(User user) throws managerException {
		if (user.getName() != "No User") {
			user = newUser(user);
		}
	}

	@Override
	public void savePerson(User user, Person p) throws DaoException {
		if (user.getName() != "No User") {
			dao.saveBean(p);
		}
	}

	@Override
	public void saveGroup(User user, Group p) throws DaoException {
		if (user.getName() != "No User") {
			dao.saveBean(p);
		}
	}
	
	public void itemPerPageEdit(int number){
		Dao.setItemPerPage(number);
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
