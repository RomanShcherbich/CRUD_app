package entity;

import exception.EntityException;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Environment implements Entity {
  private int containerId;
  private int temperature;
  private int humidity;
  private Timestamp globalTime;
  private Timestamp internalTime;

  public Environment(int containerId, int temperature, int humidity, Timestamp globalTime, Timestamp internalTime) {
    this.containerId = containerId;
    this.temperature = temperature;
    this.humidity = humidity;
    this.globalTime = globalTime;
    this.internalTime = internalTime;
  }

  public Environment(int containerId, int temperature, int humidity) {
    this.containerId = containerId;
    this.temperature = temperature;
    this.humidity = humidity;
    this.globalTime = Timestamp.valueOf(LocalDateTime.now());
    this.internalTime = Timestamp.valueOf(LocalDateTime.now());
  }

  public Environment() {
  }

  public int getContainerId() {
    return containerId;
  }

  public void setContainerId(int containerId) {
    this.containerId = containerId;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }

  public Timestamp getGlobalTime() {
    return globalTime;
  }

  public void setGlobalTime(Timestamp globalTime) {
    this.globalTime = globalTime;
  }

  public Timestamp getInternalTime() {
    return internalTime;
  }

  public void setInternalTime(Timestamp internalTime) {
    this.internalTime = internalTime;
  }

  @Override
  public String toString() {
    String log = new String();

    try {
      List<Method> methods = Arrays.stream(getClass().getMethods())
          .filter(m -> m.getName().contains("get") && !m.getName().contains("Class"))
          .collect(Collectors.toList());
      for (Method getValue :
          methods) {
        String name = getValue.getName();
        name = name.substring(3, 4).toLowerCase() + name.substring(4);
        String value = getValue.invoke(this).toString();
        log += (name + " = " + value + "\n");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return log;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Environment that = (Environment) o;

    List<String> log = new ArrayList<>();

    equalWithLog(containerId, that.containerId, "containerId", log);
    equalWithLog(temperature, that.temperature, "temperature", log);
    equalWithLog(humidity, that.humidity, "humidity", log);
    equalWithLog(globalTime, that.globalTime, "globalTime", log);
    equalWithLog(internalTime, that.internalTime, "internalTime", log);

    if (log.size() != 0) {
      throw new java.lang.AssertionError("Environment fields are different!\n"
          + log.stream().collect(Collectors.joining("\n")));
    }
    return log.size() == 0;
//        containerId == that.containerId &&
//        temperature == that.temperature &&
//        humidity == that.humidity &&
////        internalTime.toString().substring(0,23).equals(that.internalTime.toString().substring(0,23)) &&
////        globalTime.toString().substring(0,23).equals(that.globalTime.toString().substring(0,23));
//        globalTime.equals(that.globalTime) &&
//        internalTime.equals(that.internalTime);
//        globalTime.getDayOfYear() == that.globalTime.getDayOfYear() &&
//        globalTime.getHour() == that.globalTime.getHour() &&
//        globalTime.getMinute() == that.globalTime.getMinute() &&
//        globalTime.getSecond()== that.globalTime.getSecond() &&
//        internalTime.getDayOfYear() == that.internalTime.getDayOfYear() &&
//        internalTime.getHour() == that.internalTime.getHour() &&
//        internalTime.getMinute() == that.internalTime.getMinute() &&
//        internalTime.getSecond()== that.internalTime.getSecond()+1;
  }

  public boolean equalsReflection(Object o) {
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
      ex.printStackTrace();
    }

    if (log.size() != 0) {
      throw new java.lang.AssertionError("Environment fields are different!\n"
          + log.stream().collect(Collectors.joining("\n")));
    }
    return log.size() == 0;
  }

  private List<String> equalWithLog(Object expected, Object actual, String name, List<String> log) {
    if (!expected.equals(actual)) {
      log.add(formatRight("expected " + name) + expected);
      log.add(formatRight("actual " + name) + actual);
    }
    return log;
  }

  private String formatRight(String s) {
    return String.format("%25s = ", s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(containerId, temperature, humidity, globalTime, internalTime);
  }
}
