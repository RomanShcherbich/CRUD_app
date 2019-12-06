package edu.java.dao;

import edu.java.entity.Environment;
import edu.java.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface  ContainersDao <T>{
  List<T> getEntity() throws DaoException, SQLException;
}
