package dao;

import entity.Environment;
import exception.DaoException;

import java.util.List;

public interface  ContainersDao <T>{
  List<T> getEntity() throws DaoException;
  Long postEntity(Environment environment) throws DaoException;
  Long deleteAllRows() throws DaoException;
}
