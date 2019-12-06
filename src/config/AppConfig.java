package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
  public static Properties appProps;

  public static void appPropsInit() throws IOException {
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "resources/application.properties";
    appProps = new Properties();
    appProps.load(new FileInputStream(appConfigPath));
  }
}
