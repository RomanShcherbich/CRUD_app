package edu.java.dao;

import edu.java.entity.Environment;
import edu.java.exception.DaoException;

import static config.AppConfig.appProps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContainerDao implements ContainersDao {

  private String SELECT_CONTAINERS = "SELECT * FROM containerdata";

  private Connection getConnection() throws SQLException {
    Connection connection = DriverManager.getConnection(
        appProps.getProperty("jdbc.url"),
        appProps.getProperty("jdbc.username"),
        appProps.getProperty("jdbc.password"));
    return connection;
  }

  @Override
  public List<Environment> getEntity() throws DaoException {
    List<Environment> environments = new ArrayList<>();
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      ResultSet tblContainers = stmt.executeQuery(SELECT_CONTAINERS);

      while (tblContainers.next()) {
        System.out.println();
        Environment environment = new Environment();
        for (int i = 0; i < tblContainers.getMetaData().getColumnCount(); i++) {
          System.out.print(tblContainers.getString(i+1) + " : ");
        }
        environment.setContainerId(tblContainers.getInt(1));
        environment.setTemperature(tblContainers.getInt(2));
        environment.setHumidity(tblContainers.getInt(3));
        environment.setInternalTime(LocalDateTime.parse(LocalDate.now() + "T" + tblContainers.getString(4)));
        environment.setGlobalTime(LocalDateTime.parse(LocalDate.now() + "T" + tblContainers.getString(5)));
        return environments;
      }
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
    return environments;
  }

/*
  private Environment resultToEntity(ResultSet resultSet) throws SQLException, InvocationTargetException, IllegalAccessException {

    Environment environment = new Environment();
    resultSet.getMetaData().getColumnName(1)
    environment.getClass().getMethods();
    for (Method method :
        environment.getClass().getMethods()
    ) {
      String methodName = method.getName();
      if (method.getName().contains("set")) {
        String parameter = methodName.substring(3);

        Class<?>[] types = method.getParameterTypes();
        String type = types[0].getName();
        type = type.substring(0,1).toUpperCase() + type.substring(1);

        method.invoke(environment, resultSet.getString(parameter));
        method.getParameterTypes();
        System.out.println(parameter);
      }
    }
    return environment;
  }*/

}
