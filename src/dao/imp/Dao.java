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
 *         bon bon bon il faut deplacer l'appel du logger dans une autre classe.
 *         et cr�e une classe Caller qui appelle la Dao et le logger en meme
 *         temps. j'ai un exemple sur le tp1 mais j'ai pas push sur git, le truc
 *         que j'ai fait sa marche mais ... tellement moche que y'as que moi qui
 *         peut le lire xD
 *
 * 
 *         nouvelle etape : j'ai fait toute les methodes. faut les tester pour
 *         voir si elles marchent. si elles marchent toute. faut voir qu'elle
 *         font bien ce qu'il faut
 * 
 *         et pour finir virer le logger autre part.
 *
 */
@Service
public class Dao implements IDao {

	private JdbcTemplate jdbcTemplate;

	protected final Log logger = LogFactory.getLog(getClass());
	
    @PostConstruct
    public void init() {
        logger.info("Create Table");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Group`(" + //
                "Id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
                "Name VARCHAR(64) NOT NULL" + //
                ")ENGINE=INNODB");
        jdbcTemplate.execute("ALTER TABLE `Group` AUTO_INCREMENT = 100");
        this.jdbcTemplate.update("REPLACE INTO `Group` (`Id`,`Name`) VALUES (?,?)",1 , "No group");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS `Person`(" + //
                "Id BIGINT AUTO_INCREMENT PRIMARY KEY," + //
                "LastName VARCHAR(36)," + //
                "FirstName VARCHAR(32)," + //
                "Email VARCHAR(250)," + //
                "Website VARCHAR(250)," + //
                "BirthDate DATE," + //
                "Password VARCHAR(30)," + //
                "GroupId BIGINT DEFAULT 1," + //
                "FOREIGN KEY (GroupId) REFERENCES `Group`(Id) ON DELETE CASCADE" + // on ne peut pas mettre default avec innodb
                ")ENGINE=INNODB");
    }
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// il faut reunir les 4 methodes en 1 seule

	static private Group groupToBean(ResultSet rs, int rank) throws SQLException {
		return new Group(rs.getLong(1), rs.getString(2));
	}

	public Collection<Group> findAllGroups() {
		return this.jdbcTemplate.query("SELECT * FROM `Group`", Dao::groupToBean);
	}

	static private Person personToBean(ResultSet rs, int rank) throws SQLException {
		return new Person(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getLong(8));
	}

	public Collection<Person> findAllPersons(long groupId) {
		return this.jdbcTemplate.query("SELECT * FROM `Person` WHERE id = ?", Dao::personToBean, groupId);
	}

	/**
	 * j'ai fait en mysql. de toute facon innodb c du mysql
	 * pour l'ajout d'une perssonne il faut mettre une id de 0
	 * 
	 * @throws DaoException
	 */
	@Override
	public void savePerson(Person p) throws DaoException {
		this.jdbcTemplate.update("REPLACE INTO `Person` VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", p.getId(), p.getLastName(),
				p.getFirstName(), p.getEmail(), p.getWebSite(), p.getBirthDate(), p.getPassword(), p.getGroupId());
	}

	/**
	 * 
	 */
	@Override
	public void saveGroup(Group g) throws DaoException {
		this.jdbcTemplate.update("REPLACE INTO `Group` VALUES ( ?, ?)", g.getId(), g.getName());
	}
	
	/**
	 * @throws DaoException
	 */
	@Override
	public Person findPerson(long id) throws DaoException {
		return this.jdbcTemplate.queryForObject("Select * FROM `Person` WHERE id = ?", Person.class, id);
	}
	
	/**
	 * 
	 */
	@Override
	public Group findGroup(long id) throws DaoException {
		return this.jdbcTemplate.queryForObject("Select * FROM `Group` WHERE id = ?", Group.class, id);
	}

	/**
	 * 
	 * @throws DaoException
	 */
	@Override
	public void removePerson(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
	}

	/**
	 * 
	 */
	@Override
	public void removeGroup(long id) throws DaoException {
		this.jdbcTemplate.update("DELETE FROM `Group` WHERE id = ?", id);
	}
}
