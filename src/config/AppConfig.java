package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.util.Properties;

public class AppConfig {

  public static Properties properties;

  public static final String DB_URL = "jdbc.url";
  public static final String DB_LOGIN = "jdbc.username";
  public static final String DB_PASSWORD = "jdbc.password";

  public static String getProperty(String name) throws IOException {
    if(properties == null) {
      appPropsInit();
    }
    return properties.getProperty(name);
  }

  public static void appPropsInit() throws IOException {
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "resources/application.properties";
    properties = new Properties();
    properties.load(new FileInputStream(appConfigPath));

    try (InputStream is =
             AppConfig.class.getClassLoader().getResourceAsStream("resources/application.properties")) {

    }

  }
}
