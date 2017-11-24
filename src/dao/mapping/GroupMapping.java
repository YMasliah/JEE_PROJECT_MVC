package dao.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BeanToResultSet;
import dao.ResultSetToBean;
import dao.exception.DaoException;
import directory.beans.Group;

public class GroupMapping implements BeanToResultSet<Group>, ResultSetToBean<Group>{

	/**
	 * a refaire
	 */
	@Override 
	public String toResultSet(Group bean) throws DaoException {
		String returnValue = "REPLACE INTO `GROUP` VALUES (";
		returnValue += bean.getId()  + "\", \"";
		returnValue += bean.getName()  + "\");";
		return returnValue;
	}

	/**
	 * 
	 */
	@Override
	public Group toBean(ResultSet rs, int rank) throws SQLException {
		return new Group(rs.getLong(1),rs.getString(2));
	}

}
