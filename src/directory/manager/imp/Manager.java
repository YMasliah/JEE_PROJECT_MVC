package directory.manager.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IDao;
import dao.exception.DaoException;
import directory.IDirectoryManager;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

@Service
public class Manager implements IDirectoryManager {

	private ArrayList<User> userList = new ArrayList<>();

	@Autowired
	private IDao dao;

	public Manager() {

	}

	@Override
	public User newUser() throws managerException {
		User returnValue = new User();
		returnValue.setName("No User");
		return returnValue;
	}

	@Override
	public Person findPerson(User user, long personId) throws DaoException {
		Person returnValue = new Person();
		if (userList.indexOf(user) !=-1) {
			returnValue = dao.findPerson(personId);
		}
		return returnValue;
	}

	@Override
	public Group findGroup(User user, long groupId) throws DaoException {
		Group returnValue = new Group();
		if (userList.indexOf(user) !=-1) {
			returnValue = dao.findGroup(groupId);
		}
		return returnValue;
	}

	@Override
	public Collection<Person> findAll(User user, long groupId) throws DaoException {
		Collection<Person> returnValue = Collections.emptyList();
		if (userList.indexOf(user) !=-1) {
			returnValue = dao.findAll(groupId);
		}
		return returnValue;
	}

	@Override
	public Collection<Group> findAll(User user) throws DaoException {
		Collection<Group> returnValue = Collections.emptyList();
		System.out.println("ici");
		System.out.println(user);
		System.out.println(userList.indexOf(user));
		if (userList.indexOf(user) !=-1) {
			System.out.println("la bas");
			returnValue = dao.findAll();
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
		User returnValue = newUser();
		Person person = dao.findPerson(user.getId());
		if (person.getPassword().equals(user.getPassword())) {
			if (userList.indexOf(user) ==-1) {
				returnValue.setId(person.getId());
				returnValue.setPassword(person.getPassword());
				returnValue.setName(person.getLastName());
				user.setName(returnValue.getName());
				userList.add(returnValue);
			}
		}
		return returnValue;
	}

	/**
	 * faire une petite trace quand meme
	 * @throws managerException 
	 */
	@Override
	public void logout(User user) throws managerException {
		if (userList.indexOf(user) !=-1) {
			userList.remove(user);
			user = newUser();
		}
	}

	@Override
	public void savePerson(User user, Person p) throws DaoException {
		 if(userList.indexOf(user) !=-1){
			 dao.saveBean(p);
		 }
	}

	@Override
	public void saveGroup(User user, Group p) throws DaoException {
		 if(userList.indexOf(user) !=-1){
			 dao.saveBean(p);
		 }
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
