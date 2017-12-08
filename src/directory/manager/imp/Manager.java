package directory.manager.imp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec la Dao et qui sert de liaison aux controllers
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
@Service
public class Manager implements IDirectoryManager {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IDao dao;

	public Manager() {

	}

	/**
	 * @see directory.IDirectoryManager#newUser(directory.manager.beans.User)
	 */
	@Override
	public User newUser(User user) throws managerException {
		User returnValue = user;
		returnValue.setName("No User");
		returnValue.setId(null);
		returnValue.setPassword(null);
		return returnValue;
	}

	/**
	 * @see directory.IDirectoryManager#findPerson(directory.manager.beans.User, long)
	 */
	@Override
	public Person findPerson(User user, long personId) throws DaoException {
		Person returnValue = dao.findPerson(personId);
		returnValue.setPassword(null);
		if (user.getName() == "No User") {
			returnValue.setEmail(null);
			returnValue.setBirthDate(null);
		}
		return returnValue;
	}

	
	/**
	 * @see directory.IDirectoryManager#findGroup(directory.manager.beans.User, long)
	 */
	@Override
	public Group findGroup(User user, long groupId) throws DaoException {
		return dao.findGroup(groupId);
	}

	/**
	 * @see directory.IDirectoryManager#findPerson(directory.manager.beans.User, java.lang.String, int)
	 */
	@Override
	public Collection<Person> findPerson(User user, String lastName, int page) throws DaoException {
		return dao.findPerson(lastName, page);
	}

	/**
	 * @see directory.IDirectoryManager#findGroup(directory.manager.beans.User, java.lang.String, int)
	 */
	@Override
	public Collection<Group> findGroup(User user, String name, int page) throws DaoException {
		return dao.findGroup(name, page);
	}

	/**
	 * @see directory.IDirectoryManager#findAll(directory.manager.beans.User, long, int)
	 */
	@Override
	public Collection<Person> findAll(User user, long groupId, int page) throws DaoException {
		return dao.findAll(groupId, page);
	}

	/**
	 * @see directory.IDirectoryManager#findAll(directory.manager.beans.User, int)
	 */
	@Override
	public Collection<Group> findAll(User user, int page) throws DaoException {
		return dao.findAll(page);
	}

	/**
	 * @see directory.IDirectoryManager#login(directory.manager.beans.User)
	 */
	@Override
	public User login(User user) throws DaoException, managerException{
		User returnValue = user;
		if (!user.getPassword().isEmpty()) {
			Person person = dao.findPerson(user.getId());
			logger.info(user);
			if (person.getId() != null && person.getPassword().equals(crypt(user.getPassword()))) {
				returnValue.setName(person.getLastName());
				logger.info("succes login");
			}
		}
		return returnValue;
	}


	/**
	 * @see directory.IDirectoryManager#logout(directory.manager.beans.User)
	 */
	@Override
	public void logout(User user) throws managerException {
			user = newUser(user);
	}

	/**
	 * @see directory.IDirectoryManager#savePerson(directory.manager.beans.User, directory.beans.Person)
	 */
	@Override
	public void savePerson(User user, Person p) throws DaoException {
		logger.info(user);
		if (user.getName().equals(p.getLastName()) && user.getId().equals(p.getId())) {
			p.setPassword(crypt(user.getPassword()));
			dao.saveBean(p);
		}else if(user.getName().equals("mailWorker")){
			p.setPassword(crypt(user.getPassword()));
			dao.saveBean(p);
		}
	}
	
	/**
	 * @see directory.IDirectoryManager#findGroup(directory.manager.beans.User, java.lang.String)
	 */
	public Group findGroup(User user, String groupName) throws DaoException {
		return dao.findGroup(groupName);
	}
	
	
	/**
	 * fonction de cryptage des mot de passe
	 * 
	 * @param password mot de passe a encrypter
	 * @return resultat de l'encryption
	 */
	private String crypt(String password)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
