package edu.java.dao;

import edu.java.entity.Environment;
import edu.java.exception.DaoException;

import java.util.List;

public interface  ContainersDao <T>{
  List<T> getEntity() throws DaoException;
  Long postEntity(Environment environment) throws DaoException;
}
