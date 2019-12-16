package domain;

import java.sql.Timestamp;

public class EnvironmentRequest {
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
}
