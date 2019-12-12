package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

  public static Properties properties;

  public static final String DB_URL = "jdbc.url";
  public static final String DB_LOGIN = "jdbc.username";
  public static final String DB_PASSWORD = "jdbc.password";
  public static final String DB_DRIVER = "jdbc.driverClassName";

  public static String getProperty(String name) {
    String propsSuiteName = "properties.suite";
    String path = "application.properties";

    if (properties == null) {
      properties = new Properties();
      loadProps(path);
    }
    String propsSuiteSystemValue = System.getProperty(propsSuiteName);

    if(propsSuiteSystemValue!=null)  {
      properties.setProperty(propsSuiteName,System.getProperty(propsSuiteName));
    }

    String propsSuite = properties.getProperty(propsSuiteName);
    path = path.replace("application","application-" + propsSuite);

    if (!properties.getProperty("properties.suite").equals("")) {
      properties.setProperty("properties.suite","");
      loadProps(path);
    }

    return properties.getProperty(name);
  }

  private static void loadProps(String path) {
    try (InputStream is = Config.class.getClassLoader()
        .getResourceAsStream(path)) {
      properties.load(is);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    }
  }
}

