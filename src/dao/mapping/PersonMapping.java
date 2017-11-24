package dao.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BeanToResultSet;
import dao.ResultSetToBean;
import dao.exception.DaoException;
import directory.beans.Person;

public class PersonMapping implements BeanToResultSet<Person>, ResultSetToBean<Person> {

	/**
	 * 
	 */
	@Override
	public Person toBean(ResultSet rs, int rank) throws SQLException {
		return new Person(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getLong(8));
	}

	/**
	 * a refaire
	 */
	@Override
	public String toResultSet(Person bean) throws DaoException {
		String returnValue = "REPLACE INTO person VALUES (";
		returnValue += bean.getId()  + "\", \"";
		returnValue += bean.getLastName()  + "\", \"";
		returnValue += bean.getFirstName()  + "\", \"";
		returnValue += bean.getBirthDate()  + "\", \"";
		returnValue += bean.getEmail()  + "\", \"";
		returnValue += bean.getWebSite() + "\", \"";
		returnValue += bean.getPassword() + "\", \"";
		returnValue += bean.getGroupId()  + "\");";
		return returnValue;
	}

}
