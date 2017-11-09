package directory.manager.imp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import dao.exception.DaoException;
//import dao.imp.Dao;
import directory.IDirectoryManager;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.User;
import directory.manager.exception.managerException;

@Service
public class Manager implements IDirectoryManager {

	
	/**
	 * a tout les coups faut sortir les 2 variables
	 */
	private LinkedHashMap<User,Boolean> userList = new LinkedHashMap<>();
	
	//mumuse sans dao
	Map<Long, Group> toto = new HashMap<>();
	Collection<Group> groups;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//	@Autowired
//	private Dao dao;
	
	public Manager() {
		
		Date date = null;
		try {
			date = sdf.parse("22/03/1991");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userList.put(new User(1, "vtff"),true);
    	HashMap<Integer,Person> persons = new HashMap<>();
    	Person person = new Person(10,"Masliah", "yann", date, "toto@gmail.com", "", "tata", 1);
    	person.setLastName("toto");
    	persons.put(1,new Person());
    	persons.put(2,person);
    	Group g1 = new Group(1, "isl", persons.values());
    	toto.put(g1.getId(), g1);
    	Group g2 = new Group(2, "fsi", null);
    	toto.put(g2.getId(), g2);
    	groups = toto.values();
	}
	
	/**
	 * faut check la base de donnée si la perssonne existe
	 */
	@Override
	public User newUser(long personId, String lastName, String password) throws managerException {
		User newUser = new User(personId,password);
		newUser.setName(lastName);
		if(userList.get(newUser)!=null){
			throw new managerException();
		}
		userList.put(newUser, false);
		return newUser;
	}

	@Override
	public Person findPerson(User user, long personId) throws DaoException {
		Person returnValue = null;
		Date date = null;
		try {
			date = sdf.parse("22/03/1991");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		returnValue = new Person(10,"Masliah", "yann", date, "toto@gmail.com", "", "tata", 1);
//		if(userList.get(user)){
//			returnValue = dao.findPerson(personId);
//		}
		return returnValue;
	}

	@Override
	public Group findGroup(User user, long groupId) throws DaoException {
		Group returnValue = null;
//		if(userList.get(user)){
//			returnValue = dao.findGroup(groupId);
			returnValue = toto.get(groupId);
//		}
		return returnValue;
	}

	@Override
	public Collection<Person> findAllPersons(User user, long groupId) throws DaoException {
		Collection<Person> returnValue = null;
		if(userList.get(user)){
//			returnValue = dao.findAllPersons(groupId);
		}
		return returnValue;
	}

	@Override
	public Collection<Group> findAllGroup(User user) throws DaoException {
		Collection<Group> returnValue = null;
//		if(userList.get(user)){
//			returnValue = dao.findAllGroups();
	    	returnValue = groups;
//		}
		return returnValue;
	}
	
	@Override
	public boolean login(User user) {
		boolean returnValue = false;
		if(userList.get(user)==false){
			userList.put(user, true);
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * faire une petite trace quand meme
	 */
	@Override
	public void logout(User user) {
		if(userList.get(user)){
			userList.put(user, false);
		}
	}

	@Override
	public void savePerson(User user, Person p) throws DaoException {
//		if(userList.get(user)){
//			dao.savePerson(p);
//		}
	}

	public void saveGroup(User user, Group p) {
		// TODO Auto-generated method stub
		
	}

}
