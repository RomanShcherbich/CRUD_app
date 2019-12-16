package utils;

public class StringUtils {

  public static String formatRight(String s, int step) {
    return String.format("%"+ step + "s = ", s);
  }
}
