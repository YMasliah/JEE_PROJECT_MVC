package dao;

import dao.exception.DaoException;

public interface BeanToResultSet<T> {
	String toResultSet(T bean) throws DaoException;
}
