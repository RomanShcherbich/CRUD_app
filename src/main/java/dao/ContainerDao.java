package dao;

import static utils.DaoUtils.booleanToBit;

import config.Config;
import entity.Environment;
import exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContainerDao implements ContainersDao {

  private String SELECT_CONTAINER_DATA = "SELECT * FROM containerdata ORDER BY globaltime DESC";
  private String DELETE_CONTAINER_DATA = "DELETE FROM containerdata";
  private String INSERT_CONTAINER_DATA = "INSERT INTO containerdata ";
  private String COLUMNS = "(containerId, airTemp, airHumidity, airCo2, airVentilation, waterPh, waterEc,"
      + " lightGrow, lightSeed, lightWork, internalTime, globalTime)";

  private Connection getConnection() throws SQLException {
    return ConnectionBuilder.getConnection();
  }

  @Override
  public Long postEntity(Environment environment) throws DaoException {

    long id;

    String values = getStringBuilder(environment);

    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      id = (long) stmt.executeUpdate(INSERT_CONTAINER_DATA + COLUMNS + values, new String[]{"airTemp"});
      ResultSet gkRs = stmt.getGeneratedKeys();
      if (gkRs.next()) {
        id = gkRs.getLong(1);
      }

    } catch (SQLException | NullPointerException ex) {
      throw new DaoException(ex);
    }

    return id;
  }

  @Override
  public Long deleteAllRows() throws DaoException {

    long id;

    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      id = stmt.executeUpdate(DELETE_CONTAINER_DATA);

    } catch (SQLException | NullPointerException ex) {
      throw new DaoException(ex);
    }

    return id;
  }

  private String getStringBuilder(Environment environment) {

    Boolean t = true;
    t.booleanValue();
    StringBuilder values = new StringBuilder();
    values.append("VALUES (");
    values.append(environment.getContainerId());
    values.append(",");
    values.append(environment.getAirTemp());
    values.append(",");
    values.append(environment.getAirHumidity());
    values.append(",");
    values.append(environment.getAirCo2());
    values.append(",");
    values.append(booleanToBit(environment.isAirVentilation()));
    values.append(",");
    values.append(environment.getWaterPh());
    values.append(",");
    values.append(environment.getWaterEc());
    values.append(",");
    values.append(booleanToBit(environment.isLightGrow()));
    values.append(",");
    values.append(booleanToBit(environment.isLightSeed()));
    values.append(",");
    values.append(booleanToBit(environment.isLightWork()));
    values.append(",'");
//    values.append(getDbTimeStamp(environment.getInternalTime()));
//    values.append("','");
//    values.append(getDbTimeStamp(environment.getGlobalTime()));
//    values.append("')");
    values.append(environment.getInternalTime());
    values.append("','");
    values.append(environment.getGlobalTime());
    values.append("')");
    return values.toString();
  }

  private String getDbTimeStamp(LocalDateTime localDateTime) {
    if (Config.getProperty(Config.DB_URL).contains("sqlserver")) {
      return localDateTime.toString().substring(0, 23);
    }
    return localDateTime.toString();
  }

  private String selectTop(String query, int rows) {
    if (Config.getProperty(Config.DB_URL).contains("sqlserver")) {
      query = query.replace("SELECT *", "SELECT TOP %s *");
    } else {
      query = query + " LIMIT %s";
    }
    return String.format(query, rows);
  }

  @Override
  public List<Environment> getEntity(int topRows) throws DaoException {
    List<Environment> environments = new ArrayList<>();
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement()) {

      ResultSet tblContainers = stmt.executeQuery(selectTop(SELECT_CONTAINER_DATA, topRows));

      while (tblContainers.next()) {
        System.out.println();
        Environment environment = new Environment();
        for (int i = 0; i < tblContainers.getMetaData().getColumnCount(); i++) {
          System.out.print(tblContainers.getString(i + 1) + " : ");
        }
        environment.setContainerId(tblContainers.getInt("containerId"));
        environment.setAirTemp(tblContainers.getInt("airTemp"));
        environment.setAirHumidity(tblContainers.getInt("airHumidity"));
        environment.setAirCo2(tblContainers.getInt("airCo2"));
        environment.setAirVentilation(tblContainers.getBoolean("airVentilation"));
        environment.setWaterPh(tblContainers.getDouble("waterPh"));
        environment.setWaterEc(tblContainers.getInt("waterEc"));
        environment.setLightGrow(tblContainers.getBoolean("lightGrow"));
        environment.setLightSeed(tblContainers.getBoolean("lightSeed"));
        environment.setLightWork(tblContainers.getBoolean("lightWork"));
        environment.setInternalTime(tblContainers.getTimestamp("internalTime"));
        environment.setGlobalTime(tblContainers.getTimestamp("globalTime"));
        environments.add(environment);
      }
    } catch (SQLException ex) {
      throw new DaoException(ex);
    }
    return environments;
  }
}
