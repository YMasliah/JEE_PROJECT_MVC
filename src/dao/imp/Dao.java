package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * Creation des tables
	 */
	@PostConstruct
	public void init() {
		logger.info("Create Table");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Group`(" + //
				"Id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
				"Name VARCHAR(64) NOT NULL" + //
				")ENGINE=INNODB");
		jdbcTemplate.execute("ALTER TABLE `Group` AUTO_INCREMENT = 100");
		this.jdbcTemplate.update("REPLACE INTO `Group` (`Id`,`Name`) VALUES (?,?)", 1, "No group");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Person`(" + //
				"Id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
				"LastName VARCHAR(36)," + //
				"FirstName VARCHAR(32)," + //
				"Email VARCHAR(250)," + //
				"Website VARCHAR(250)," + //
				"BirthDate DATE," + //
				"Password VARCHAR(30)," + //
				"GroupId BIGINT DEFAULT 1," + //
				"FOREIGN KEY (GroupId) REFERENCES `Group`(Id) ON DELETE CASCADE" + // on ne peut pas mettre default avec
																					// innodb
				")ENGINE=INNODB");
	}

	/**
	 * Connection a la base de donnée informations dans le fichier .xml
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
	static private Person resultSetToPerson(ResultSet rs, int rank) throws SQLException {
		return new Person(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getLong(8));
	}

	/**
	 * Renvoi une collection de Group
	 * 
	 * @return
	 */
	@Override
	public Collection<Group> findAll() {
		return this.jdbcTemplate.query("SELECT * FROM `Group`", Dao::resultSetToGroup);
	}

	/**
	 * Renvoi une collection de Person appartenant a un groupe
	 * 
	 * @param groupId
	 * @return
	 */
	@Override
	public Collection<Person> findAll(long groupId) {
		return this.jdbcTemplate.query("SELECT * FROM `Person` WHERE id = ?", Dao::resultSetToPerson, groupId);
	}

	@Override
	public void saveBean(Person p) throws DaoException {
		this.jdbcTemplate.update("REPLACE INTO `Person` VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", p.getId(), p.getLastName(),
				p.getFirstName(), p.getEmail(), p.getWebSite(), p.getBirthDate(), p.getPassword(), p.getGroupId());
	}

	@Override
	public void saveBean(Group g) throws DaoException {
		this.jdbcTemplate.update("REPLACE INTO `Group` VALUES ( ?, ?)", g.getId(), g.getName());
	}

	@Override
	public Person findPerson(long id) throws DaoException {
		return this.jdbcTemplate.queryForObject("Select * FROM `Person` WHERE id = ?", Person.class, id);
	}

	@Override
	public Group findGroup(long id) throws DaoException {
		return this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE id = ?", Group.class, id);
	}

	@Override
	public void removePerson(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
	}

	@Override
	public void removeGroup(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM `Group` WHERE id = ?", id);
	}
}
