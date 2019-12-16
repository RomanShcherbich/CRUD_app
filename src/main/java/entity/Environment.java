package entity;

import static utils.StringUtils.formatRight;

import exception.EntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Environment implements Entity {
  private int containerId;
  private int airTemp;
  private int airHumidity;
  private int airCo2;
  private boolean airVentilation;
  private double waterPh;
  private int waterEc;
  private boolean lightGrow;
  private boolean lightSeed;
  private boolean lightWork;
  private Timestamp internalTime;
  private Timestamp globalTime;
  private static final Logger logger = LoggerFactory.getLogger(Environment.class);

  public Environment(int containerId, int airTemp, int airHumidity, int airCo2, boolean airVentilation, int waterPh,
                     int waterEc, boolean lightGrow, boolean lightSeed, boolean lightWork, Timestamp internalTime,
                     Timestamp globalTime) {
    this.containerId = containerId;
    this.airTemp = airTemp;
    this.airHumidity = airHumidity;
    this.airCo2 = airCo2;
    this.airVentilation = airVentilation;
    this.waterPh = waterPh;
    this.waterEc = waterEc;
    this.lightGrow = lightGrow;
    this.lightSeed = lightSeed;
    this.lightWork = lightWork;
    this.internalTime = internalTime;
    this.globalTime = globalTime;
  }

  public Environment(int containerId, int temperature, int airHumidity, Timestamp globalTime, Timestamp internalTime) {
    this.containerId = containerId;
    this.airTemp = temperature;
    this.airHumidity = airHumidity;
    this.globalTime = globalTime;
    this.internalTime = internalTime;
  }

  public Environment(int containerId, int temperature, int airHumidity) {
    this.containerId = containerId;
    this.airTemp = temperature;
    this.airHumidity = airHumidity;
    this.airCo2 = 1200;
    this.airVentilation = true;
    this.waterPh = 6.2;
    this.waterEc = 900;
    this.lightGrow = true;
    this.lightSeed = false;
    this.lightWork = true;
    this.globalTime = new Timestamp(System.currentTimeMillis());
    this.internalTime = new Timestamp(System.currentTimeMillis());
  }

  public Environment() {
  }

  public int getContainerId() {
    return containerId;
  }

  public void setContainerId(int containerId) {
    this.containerId = containerId;
  }

  public int getAirTemp() {
    return airTemp;
  }

  public void setAirTemp(int airTemp) {
    this.airTemp = airTemp;
  }

  public int getAirHumidity() {
    return airHumidity;
  }

  public void setAirHumidity(int airHumidity) {
    this.airHumidity = airHumidity;
  }

  public int getAirCo2() {
    return airCo2;
  }

  public void setAirCo2(int airCo2) {
    this.airCo2 = airCo2;
  }

  public boolean isAirVentilation() {
    return airVentilation;
  }

  public void setAirVentilation(boolean airVentilation) {
    this.airVentilation = airVentilation;
  }

  public double getWaterPh() {
    return waterPh;
  }

  public void setWaterPh(double waterPh) {
    this.waterPh = waterPh;
  }

  public int getWaterEc() {
    return waterEc;
  }

  public void setWaterEc(int waterEc) {
    this.waterEc = waterEc;
  }

  public boolean isLightGrow() {
    return lightGrow;
  }

  public void setLightGrow(boolean lightGrow) {
    this.lightGrow = lightGrow;
  }

  public boolean isLightSeed() {
    return lightSeed;
  }

  public void setLightSeed(boolean lightSeed) {
    this.lightSeed = lightSeed;
  }

  public boolean isLightWork() {
    return lightWork;
  }

  public void setLightWork(boolean lightWork) {
    this.lightWork = lightWork;
  }

  public Timestamp getInternalTime() {
    return internalTime;
  }

  public void setInternalTime(Timestamp internalTime) {
    this.internalTime = internalTime;
  }

  public Timestamp getGlobalTime() {
    return globalTime;
  }

  public void setGlobalTime(Timestamp globalTime) {
    this.globalTime = globalTime;
  }

  @Override
  public String toString() {
    String string = new String();

    try {
      Class environment = getClass();
      List<Method> methods = Arrays.asList(environment.getMethods());
      for (Method m :
          methods) {
        if ((m.getName().contains("get") || m.getName().contains("is"))
            && !m.getName().contains("Class")) {
          String name = m.getName();
          name = name.replace("get", "");
          name = name.replace("is", "");
          name = name.substring(0, 1).toLowerCase() + name.substring(1);
          String value = m.invoke(this).toString();
          string += formatRight(name,4) + value + "; ";
        }
      }

    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      throw new EntityException("override method toString is a reason!", ex);
    }

    return string;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Environment that = (Environment) o;

    List<String> log = new ArrayList<>();

    try {
      List<Method> methods = Arrays.stream(getClass().getMethods())
          .filter(m -> m.getName().contains("get") && !m.getName().contains("Class"))
          .collect(Collectors.toList());
      for (Method getValue :
          methods) {
        String name = getValue.getName();
        name = name.substring(3, 4).toLowerCase() + name.substring(4);
        Object exValue = getValue.invoke(this);
        Object acValue = getValue.invoke(that);

        equalWithLog(exValue, acValue, name, log);

      }

    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      throw new EntityException("override method toString is a reason!", ex);
    }

    if (log.size() != 0) {
      logger.info("Environment fields are different!\n{}", log.stream().collect(Collectors.joining("\n")));
    }

    return log.size() == 0;
  }

  private List<String> equalWithLog(Object expected, Object actual, String name, List<String> log) {
    if (!expected.equals(actual)) {
      log.add(formatRight("expected " + name,25) + expected);
      log.add(formatRight("actual " + name,25) + actual);
    }
    return log;
  }

  @Override
  public int hashCode() {
    return Objects.hash(containerId, airTemp, airHumidity, globalTime, internalTime);
  }
}
