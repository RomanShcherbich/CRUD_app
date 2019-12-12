package edu.java.dao;

import config.Config;
import edu.java.entity.Environment;
import edu.java.exception.DaoException;

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

  protected String SELECT_CONTAINER_DATA = "SELECT * FROM containerdata ORDER BY globaltime DESC LIMIT 10";
  protected String INSERT_CONTAINER_DATA = "INSERT INTO containerdata ";
  protected String COLUMNS = "(containerid,temperature,humidity, internaltime, globaltime) ";

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        Config.getProperty(Config.DB_URL),
        Config.getProperty(Config.DB_LOGIN),
        Config.getProperty(Config.DB_PASSWORD));
  }

  @Override
  public Long postEntity(Environment environment) throws DaoException {

    long id;

    String values = getStringBuilder(environment);

    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      id = (long) stmt.executeUpdate(INSERT_CONTAINER_DATA + COLUMNS + values, new String[]{"temperature"});
      ResultSet gkRs = stmt.getGeneratedKeys();
      if(gkRs.next()) {
        id = gkRs.getLong(1);
      }

    } catch (SQLException | NullPointerException ex) {
      throw new DaoException(ex);
    }

    return id;
  }

  private String getStringBuilder(Environment environment) {
    StringBuilder values = new StringBuilder();
    values.append("VALUES (");
    values.append(environment.getContainerId());
    values.append(",");
    values.append(environment.getTemperature());
    values.append(",");
    values.append(environment.getHumidity());
    values.append(",'");
    values.append(environment.getInternalTime());
    values.append("','");
    values.append(environment.getGlobalTime());
    values.append("')");
    return values.toString();
  }

  @Override
  public List<Environment> getEntity() throws DaoException {
    List<Environment> environments = new ArrayList<>();
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      ResultSet tblContainers = stmt.executeQuery(SELECT_CONTAINER_DATA);

      while (tblContainers.next()) {
        System.out.println();
        Environment environment = new Environment();
        for (int i = 0; i < tblContainers.getMetaData().getColumnCount(); i++) {
          System.out.print(tblContainers.getString(i+1) + " : ");
        }
        environment.setContainerId(tblContainers.getInt("containerId"));
        environment.setTemperature(tblContainers.getInt(2));
        environment.setHumidity(tblContainers.getInt(3));
        environment.setInternalTime(LocalDateTime.parse(tblContainers.getString(4)
            .replace(" ","T")));
        environment.setGlobalTime(LocalDateTime.parse(tblContainers.getString(5)
            .replace(" ","T")));
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
