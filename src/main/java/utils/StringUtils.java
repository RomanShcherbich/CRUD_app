package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

  public static String formatLeftRight(String s, int step) {
    return String.format("%"+ step + "s = ", s);
  }

  public static String compareObjectsByString(Object expected, Object actual) {

    List<String> expectedFields = Arrays.asList(expected.toString().split(";"));
    List<String> actualFields = Arrays.asList(actual.toString().split(";"));

    return formatLeftRight("\nexpected",-10) + expectedFields.stream()
        .filter(str -> !actualFields.contains(str))
        .collect(Collectors.joining("; "))
        + formatLeftRight("\nactual",-10) + actualFields.stream()
        .filter(str -> !expectedFields.contains(str))
        .collect(Collectors.joining("; "));
  }
}
