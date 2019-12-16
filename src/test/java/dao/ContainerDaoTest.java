package dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ContainerDaoTest {

  public static Constructor cst;
  public static Class daoClass;
  public static Object dao;
  public static Method get;
  public static Method post;

  private static final Logger logger = LoggerFactory.getLogger(ContainerDaoTest.class);

  @BeforeClass
  public static void setUp() {
    daoClass = getClass("dao.ContainerDao");
    try {
      Class environment = getClass("entity.Environment");
      cst = environment.getConstructor(int.class, int.class, int.class);

      dao = daoClass.getConstructor().newInstance();
      post = getMethod("postEntity", environment);
      get = getMethod("getEntity", int.class);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void insertEnvironmentTest() throws NoSuchMethodException {
    Object expectedEnv = instanceOfEnv(1, 27, 61);
    invokeMethod(post, expectedEnv);
    Object actualEnv = ((ArrayList) invokeMethod(get, 1)).get(0);

    actualEnv  = instanceOfEnv(1, 24, 60);
    logger.debug("Object expectedEnv \n{}", expectedEnv);
    List<String> expectedFields = Arrays.asList(expectedEnv.toString().split(";"));
    List<String> actualFields = Arrays.asList(actualEnv.toString().split(";"));
    expectedFields.replaceAll(e -> actualFields.contains(e));
    actualFields.removeAll(expectedFields);

    Assert.assertTrue("Environment fields are different!\n"
        + expectedEnv.toString() + "\n" + actualEnv.toString(), expectedEnv.equals(actualEnv));
  }

  @Test
  public void deleteTableTest() {
    Method deleteAll = getMethod("deleteAllRows");
    invokeMethod(deleteAll);
    List<Object> actualEnv = (ArrayList) invokeMethod(get, 1);

    Assert.assertTrue("the table contains rows!", actualEnv.isEmpty());
  }

  public Object instanceOfEnv(int containerId, int temperature, int humidity) {
    try {
      return cst.newInstance(containerId, temperature, humidity);
    } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Object invokeMethod(Method method) {
    Object object = null;
    try {
      object = method.invoke(dao);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return object;
  }

  public Object invokeMethod(Method method, Object args) {
    Object object = null;
    try {
      object = method.invoke(dao, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return object;
  }

  public Method getMethod(String methodName) {
    try {
      return daoClass.getMethod(methodName);
    } catch (NoSuchMethodException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static Method getMethod(String methodName, Class argClass) {
    try {
      return daoClass.getMethod(methodName, argClass);
    } catch (NoSuchMethodException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static Class getClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}