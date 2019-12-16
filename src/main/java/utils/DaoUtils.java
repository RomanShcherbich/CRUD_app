package utils;

import config.Config;

public class DaoUtils {
  public static String booleanToBit(boolean bool) {
    return bool ? "'1'": "'0'";
  }

  public static  String selectTop(String query, int rows) {
    if (Config.getProperty(Config.DB_URL).contains("sqlserver")) {
      query = query.replace("SELECT *", "SELECT TOP %s *");
    } else {
      query = query + " LIMIT %s";
    }
    return String.format(query, rows);
  }
}
