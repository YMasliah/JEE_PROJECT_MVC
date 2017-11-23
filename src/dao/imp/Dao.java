package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

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
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
    //pounis, je defini bien comme il faut le cahier des charges avant.
    
    static private Group toBean(ResultSet rs, int rank) throws SQLException {
    	return new Group(rs.getLong(1), rs.getString(2), new ArrayList<Person>());
    }

    @Override
    public Collection<T> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM MESSAGE",
            JdbcMessageManager::messageMapper);
    }
    
	/**
	 * @throws DaoException
	 */
	@Override
	public Collection<Group> findAllGroups() throws DaoException {
		ArrayList<Group> groups = new ArrayList<Group>();
		try (Connection conn = newConnection()) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `GROUP`");

			while (rs.next()) {
				// faire une petit logger un jour. faut voir si sa spam pas trop, on fait du big
				// data apres
				groups.add(new Group(rs.getInt(1), rs.getString(2), new ArrayList<Person>()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return groups;
	}

	/**
	 * @throws DaoException
	 */
	@Override
	public Collection<Person> findAllPersons(long groupId) throws DaoException {
		ArrayList<Person> persons = new ArrayList<Person>();
		try (Connection conn = newConnection()) {
			// create new connection and statement
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM person");

			while (rs.next()) {
				// faire une petit logger un jour. faut voir si sa spam pas trop, on fait du big
				// data apres
				persons.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return persons;
	}

	/**
	 * j'ai fait en mysql. de toute facon innodb c du mysql
	 * 
	 * @throws DaoException
	 */
	@Override
	public void savePerson(Person p) throws DaoException {		
		String sql = "REPLACE INTO person VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)"; 

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			//Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setLong(1,p.getId());
			preparedStatement.setString(2, p.getLastName());
			preparedStatement.setString(3, p.getFirstName());
			preparedStatement.setString(4, p.getBirthDate());
			preparedStatement.setString(5, p.getMail());
			preparedStatement.setString(6, p.getWebSite());
			preparedStatement.setString(7, p.getPassword());
			preparedStatement.setLong(8,p.getGroupId());
			preparedStatement.setNull(8, Types.INTEGER);
			preparedStatement.executeUpdate();
			Thread.sleep(100);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void saveGroup(Group g) throws DaoException {		
		String sql = "REPLACE INTO `GROUP` VALUES ( ?, ?)"; 

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setInt(1,g.getId());
			preparedStatement.setString(2, g.getName());
			preparedStatement.executeUpdate();
			Thread.sleep(100);
		} catch (SQLException e) {
			throw new DaoException();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @throws DaoException
	 */
	@Override
	public Person findPerson(long id) throws DaoException {
		String sql = "Select * FROM person WHERE id = ?";
		Person returnValue = null;

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				returnValue = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		return returnValue;
	}

	/**
	 * 
	 */
	@Override
	public Group findGroup(long id) throws DaoException {
		String sql = "Select * FROM `GROUP` WHERE id = ?";
		Group returnValue = null;

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				returnValue = new Group(rs.getInt(1), rs.getString(2), new ArrayList<Person>());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return returnValue;
	}

	/**
	 * 
	 * @throws DaoException
	 */
	@Override
	public void removePerson(long id) throws DaoException {
		String sql = "DELETE FROM person WHERE id = ?";

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setLong(1,id);
			preparedStatement.executeUpdate();
			Thread.sleep(100);
		} catch (SQLException e) {
			throw new DaoException();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	@Override
	public void removeGroup(long id) throws DaoException {
		String sql = "DELETE FROM `GROUP` WHERE id = ?";

		try (Connection conn = newConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setLong(1,id);
			preparedStatement.executeUpdate();
			Thread.sleep(100);
		} catch (SQLException e) {
			throw new DaoException();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
