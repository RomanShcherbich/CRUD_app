package edu.java;

import config.Config;
import edu.java.dao.ContainerDao;
import edu.java.entity.Environment;
import edu.java.exception.DaoException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, DaoException {

        Class.forName(Config.getProperty(Config.DB_DRIVER));

        System.out.println(new ContainerDao().postEntity(new Environment(1,12,10)));

        new ContainerDao().getEntity();
    }
}
