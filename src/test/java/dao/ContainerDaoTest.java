package dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class ContainerDaoTest {

  public static Constructor cst;
  public static Method post;
  public static Object dao;
  public static Method get;

  @BeforeClass
  public static void setUp() {
    try {
      Class ec = Class.forName("entity.Environment");
      cst = ec.getConstructor(int.class, int.class, int.class);

      Class daoClass = Class.forName("dao.ContainerDao");
      dao = daoClass.getConstructor().newInstance();

      post = daoClass.getMethod("postEntity", ec);
      get = daoClass.getMethod("getEntity", int.class);

    } catch (ClassNotFoundException | NoSuchMethodException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void insertEnvironmentTest() throws ClassNotFoundException {
    Object expectedEnv = instanceOfEnv(1, 23, 60);
    invokeMethod(post, expectedEnv);
    Object actualEnv = ((ArrayList)invokeMethod(get,1)).get(0);


//    LocalDateTime.now().toString() + " " + LocalDateTime.now().toString().length() + "\n"
//        + java.sql.Timestamp.valueOf(LocalDateTime.now()).toString() + " " + java.sql.Timestamp.valueOf(LocalDateTime.now()).toString().length();

    Assert.assertTrue("Expected environment: \n" + expectedEnv.toString()
            + "Actual environment: \n" + actualEnv.toString()
        , expectedEnv.equals(actualEnv));
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
}