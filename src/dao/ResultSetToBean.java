package dao;

import java.sql.SQLException;

//construire un bean de type T � partir d'un ResultSet
public interface ResultSetToBean<T> {
	T toBean(java.sql.ResultSet rs, int rank) throws SQLException;
}