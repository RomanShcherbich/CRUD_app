package edu.java.entity;

import java.time.LocalDateTime;

public class Environment implements Entity {
  private int containerId;
  private int temperature;
  private int humidity;
  private LocalDateTime globalTime;
  private LocalDateTime internalTime;

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

  public LocalDateTime getGlobalTime() {
    return globalTime;
  }

  public void setGlobalTime(LocalDateTime globalTime) {
    this.globalTime = globalTime;
  }

  public LocalDateTime getInternalTime() {
    return internalTime;
  }

  public void setInternalTime(LocalDateTime internalTime) {
    this.internalTime = internalTime;
  }
}
