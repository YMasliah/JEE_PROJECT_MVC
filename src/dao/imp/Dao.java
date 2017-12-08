package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dao.IDao;
import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;

/**
 * Master 2 ISL 2017/2018
 * 
 * Couche qui interargie avec la base de donnée
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
 */
@Service
public class Dao implements IDao {

	private JdbcTemplate jdbcTemplate;

	protected final Log logger = LogFactory.getLog(getClass());

	private int itemPerPage = 50;

	/**
	 * Creation des tables
	 */
	@PostConstruct
	public void init() {
		logger.info("Create Table");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Group`(" + //
				"Id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
				"Name VARCHAR(64) NOT NULL UNIQUE" + //
				")ENGINE=INNODB");
		jdbcTemplate.execute("ALTER TABLE `Group` AUTO_INCREMENT = 100");

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Person`(" + //
				"Id BIGINT AUTO_INCREMENT UNIQUE," + //
				"LastName VARCHAR(36)," + //
				"FirstName VARCHAR(32)," + //
				"Email VARCHAR(250)," + //
				"Website VARCHAR(250)," + //
				"BirthDate DATE," + //
				"Password VARCHAR(40)," + //
				"GroupId BIGINT DEFAULT 1," + //
				"PRIMARY KEY (Id, LastName)," + "FOREIGN KEY (GroupId) REFERENCES `Group`(Id) ON DELETE CASCADE"
				+ ")ENGINE=INNODB");
		jdbcTemplate.execute("ALTER TABLE `Person` AUTO_INCREMENT = 100");

		jdbcTemplate.update("REPLACE INTO `Group` (`Id`,`Name`) VALUES (?,?)", 1, "No group");
		jdbcTemplate.update("REPLACE INTO `Group` (`Id`,`Name`) VALUES (?,?)", 0, "Bonjour");
		jdbcTemplate.update("REPLACE INTO `Person` (`Id`,`LastName`,`Password`,`Email`) VALUES (?,?,?,?)", 10, "toto",
				"1d2f4cd378a95534effdfc51acfc48a5", "y.masliah@gmail.com");
		jdbcTemplate.update("REPLACE INTO `Person` (`Id`,`LastName`,`Password`) VALUES (?,?,?)", 11, "tota",
				"1d2f4cd378a95534effdfc51acfc48a5");
	}

	/**
	 * Connection a la base de donn�e, informations dans le fichier .xml
	 * 
	 * @param dataSource
	 *            configurations
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Transforme un ResultSet en un bean Group
	 * 
	 * @param rs
	 * @param rank
	 * @return
	 * @throws SQLException
	 */
	static private Group resultSetToGroup(ResultSet rs, int rank) throws SQLException {
		return new Group(rs.getLong(1), rs.getString(2));
	}

	/**
	 * Transforme un ResultSet en un bean Person
	 * 
	 * @param rs
	 * @param rank
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	static private Person resultSetToPerson(ResultSet rs, int rank) throws SQLException {
		return new Person(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getDate(6), rs.getString(7), rs.getLong(8));
	}

	/**
	 * 
	 * @see IDao
	 * @param page
	 *            page a afficher
	 * @return la collection recu par la requete
	 */
	@Override
	public Collection<Group> findAll(int page) {
		Collection<Group> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				logger.info("Requete executer");
				returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` limit ?,?", Dao::resultSetToGroup,
						(page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	/**
	 * 
	 * @see IDao
	 * @return la collection recu par la requete
	 */
	@Override
	public Collection<Group> findAll() {
		Collection<Group> returnValue = Collections.emptyList();
		try {
			logger.info("Requete executer");
			returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` limit ?,?", Dao::resultSetToGroup);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * @see IDao
	 * @param groupId
	 *            id du groupe a chercher
	 * @return collection recu par la requete
	 */
	@Override
	public Collection<Person> findAll(long groupId, int page) {
		Collection<Person> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				logger.info("Requete executer");
				returnValue = this.jdbcTemplate.query("SELECT Id,LastName FROM `Person` WHERE GroupId = ? limit ?,?",
						new BeanPropertyRowMapper<Person>(Person.class), groupId, (page - 1) * itemPerPage,
						itemPerPage);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	/**
	 * @see dao.IDao#saveBean(directory.beans.Person)
	 * @param p
	 *            personne editer a sauvegarder
	 */
	@Override
	public void saveBean(Person p) throws DaoException {
		if (p.getId() != 0) {
			logger.info("Requete executer");
			this.jdbcTemplate.update("REPLACE INTO `Person` VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", p.getId(),
					p.getLastName(), p.getFirstName(), p.getEmail(), p.getWebSite(), p.getBirthDate(), p.getPassword(),
					p.getGroupId());
		}
	}

	/**
	 * @see dao.IDao#saveBean(directory.beans.Group)
	 * @param g
	 *            group editer a sauvegarder
	 */
	@Override
	public void saveBean(Group g) throws DaoException {
		if (g.getId() != 0) {
			logger.info("Requete executer");
			this.jdbcTemplate.update("REPLACE INTO `Group` VALUES ( ?, ?)", g.getId(), g.getName());
		}
	}

	/**
	 * @see dao.IDao#findPerson(long)
	 * @param id
	 *            identifiant de la personne a chercher
	 */
	@Override
	public Person findPerson(long id) throws DaoException {
		Person returnValue = new Person();
		try {
			logger.info("Requete executer");
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Person` WHERE id = ?",
					new BeanPropertyRowMapper<Person>(Person.class), id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * @see dao.IDao#findGroup(long)
	 * @param id
	 *            identifiant du groupe a chercher
	 */
	@Override
	public Group findGroup(long id) throws DaoException {
		Group returnValue = new Group();
		try {
			logger.info("Requete executer");
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE id = ?",
					new BeanPropertyRowMapper<Group>(Group.class), id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * @see dao.IDao#findGroup(java.lang.String)
	 * @param name
	 *            nom du groupe a chercher
	 */
	public Group findGroup(String name) throws DaoException {
		Group returnValue = new Group();
		try {
			logger.info("Requete executer");
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE Name = ?",
					new BeanPropertyRowMapper<Group>(Group.class), name);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * @see dao.IDao#findGroup(java.lang.String, int)
	 * @param name
	 *            une partie du nom du groupe a chercher
	 * @param page
	 *            le numero de la page de la recherche
	 */
	@Override
	public Collection<Group> findGroup(String name, int page) throws DaoException {
		Collection<Group> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				logger.info("Requete executer");
				returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` WHERE Name LIKE ? limit ?,?",
						Dao::resultSetToGroup, "%" + name + "%", (page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	/**
	 * @see dao.IDao#findPerson(java.lang.String, int)
	 * @param lastName
	 *            une partie du nom de famille a chercher
	 * @param page
	 *            le numero de la page de la recherche
	 */
	@Override
	public Collection<Person> findPerson(String lastName, int page) throws DaoException {
		Collection<Person> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				logger.info("Requete executer");
				returnValue = this.jdbcTemplate.query(
						"SELECT Id,LastName FROM `Person` WHERE LastName like ? limit ?,?",
						new BeanPropertyRowMapper<Person>(Person.class), "%" + lastName + "%", (page - 1) * itemPerPage,
						itemPerPage);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	@Override
	public void removePerson(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
	}
	
	@Override
	public void removeGroup(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM `Group` WHERE id = ?", id);
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}
}
