package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

  public static Properties properties;

  public static final String DB_URL = "jdbc.url";
  public static final String DB_LOGIN = "jdbc.username";
  public static final String DB_PASSWORD = "jdbc.password";

  public static String getProperty(String name) {
    if(properties == null) {
      try (InputStream is = Config.class.getClassLoader()
          .getResourceAsStream("resources/application.properties")) {
        properties = new Properties();
        properties.load(is);
      } catch (Exception ex) {
        ex.printStackTrace();
        throw  new RuntimeException(ex);
      }
    }
    return properties.getProperty(name);
  }
}
