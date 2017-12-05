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
 * 
 * @author masliah yann
 *
 *         Il serrais judicieu mais pas necessaire de deplacer le logger dans
 *         une autre classe
 * 
 *         nouvelle etape : j'ai fait toute les methodes. faut les tester pour
 *         voir si elles marchent. si elles marchent toute. faut voir qu'elle
 *         font bien ce qu'il faut
 * 
 *         finir la javadoc aussi, sa genere plus tout seul a la fin donc sa
 *         m'enerve.
 * 
 *         Faut reunir les findAll en 1 seul methode.
 * 
 *         Faut decouper puis reunir les saveBean un peu comme le findAll .. ou
 *         pas
 */
@Service
public class Dao implements IDao {

	private JdbcTemplate jdbcTemplate;

	protected final Log logger = LogFactory.getLog(getClass());

	private static int itemPerPage = 50;

	// CREATE TABLE IF NOT EXISTS `Person`(Id BIGINT AUTO_INCREMENT PRIMARY KEY,
	// LastName VARCHAR(36),FirstName VARCHAR(32), Email VARCHAR(250), website
	// VARCHAR(250), birthDate DATE, password VARCHAR(30), groupId BIGINT
	// DEFAULT 1,FOREIGN KEY (groupId) REFERENCES `Group`(Id) ON DELETE CASCADE
	// )ENGINE=INNODB;

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
		this.jdbcTemplate.update("REPLACE INTO `Group` (`Id`,`Name`) VALUES (?,?)", 1, "No group");
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

		jdbcTemplate.update("REPLACE INTO `Person` (`Id`,`LastName`,`Password`) VALUES (?,?,?)", 10, "toto",
				"1d2f4cd378a95534effdfc51acfc48a5");
		jdbcTemplate.update("REPLACE INTO `Person` (`Id`,`LastName`,`Password`) VALUES (?,?,?)", 11, "tota",
				"1d2f4cd378a95534effdfc51acfc48a5");
	}

	/**
	 * Connection a la base de donn�e informations dans le fichier .xml
	 * 
	 * @param dataSource
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
	 * Renvoi une collection de Group
	 * 
	 * @return
	 */
	@Override
	public Collection<Group> findAll(int page) {
		Collection<Group> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` limit ?,?", Dao::resultSetToGroup,
						(page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {

			}
		}
		return returnValue;
	}

	/**
	 * Renvoi une collection de Group
	 * 
	 * @return
	 */
	@Override
	public Collection<Group> findAll() {
		Collection<Group> returnValue = Collections.emptyList();
		try {
			returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` limit ?,?", Dao::resultSetToGroup);
		} catch (EmptyResultDataAccessException e) {

		}

		return returnValue;
	}

	/**
	 * Renvoi une collection de Person appartenant a un groupe
	 * 
	 * @param groupId
	 * @return
	 */
	@Override
	public Collection<Person> findAll(long groupId, int page) {
		Collection<Person> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				returnValue = this.jdbcTemplate.query("SELECT Id,LastName FROM `Person` WHERE GroupId = ? limit ?,?",
						new BeanPropertyRowMapper<Person>(Person.class), groupId, (page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {

			}
		}
		return returnValue;
	}

	@Override
	public void saveBean(Person p) throws DaoException {
		if (p.getId() != 0) {
			this.jdbcTemplate.update("REPLACE INTO `Person` VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", p.getId(),
					p.getLastName(), p.getFirstName(), p.getEmail(), p.getWebSite(), p.getBirthDate(), p.getPassword(),
					p.getGroupId());
		}
	}

	@Override
	public void saveBean(Group g) throws DaoException {
		if (g.getId() != 0) {
			this.jdbcTemplate.update("REPLACE INTO `Group` VALUES ( ?, ?)", g.getId(), g.getName());
		}
	}

	/**
	 * faut retrouver le nom de la 2eme erreur c'est surcharge de reponce
	 * 
	 */
	@Override
	public Person findPerson(long id) throws DaoException {
		Person returnValue = new Person();
		try {
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Person` WHERE id = ?",
					new BeanPropertyRowMapper<Person>(Person.class), id);
		} catch (EmptyResultDataAccessException e) {

		}
		return returnValue;
	}

	@Override
	public Group findGroup(long id) throws DaoException {
		Group returnValue = new Group();
		try {
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE id = ?",
					new BeanPropertyRowMapper<Group>(Group.class), id);
		} catch (EmptyResultDataAccessException e) {

		}
		return returnValue;
	}

	public Group findGroup(String name) throws DaoException {
		Group returnValue = new Group();
		try {
			returnValue = this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE Name = ?",
					new BeanPropertyRowMapper<Group>(Group.class), name);
		} catch (EmptyResultDataAccessException e) {

		}
		return returnValue;
	}

	@Override
	public Collection<Group> findGroup(String name, int page) throws DaoException {
		Collection<Group> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				returnValue = this.jdbcTemplate.query("SELECT * FROM `Group` WHERE Name LIKE ? limit ?,?",
						Dao::resultSetToGroup, "%" + name + "%", (page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {

			}
		}
		return returnValue;
	}

	@Override
	public Collection<Person> findPerson(String lastName, int page) throws DaoException {
		Collection<Person> returnValue = Collections.emptyList();
		if (page > 0) {
			try {
				returnValue = this.jdbcTemplate.query("SELECT Id,LastName FROM `Person` WHERE LastName like ? limit ?,?",
						new BeanPropertyRowMapper<Person>(Person.class), "%" + lastName + "%", (page - 1) * itemPerPage, itemPerPage);
			} catch (EmptyResultDataAccessException e) {

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

	public static int getItemPerPage() {
		return itemPerPage;
	}

	public static void setItemPerPage(int itemPerPage) {
		Dao.itemPerPage = itemPerPage;
	}
}
