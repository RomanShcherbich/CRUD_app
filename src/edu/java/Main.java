package edu.java;

import config.Config;
import edu.java.dao.ContainerDao;
import edu.java.entity.Environment;
import edu.java.exception.DaoException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, DaoException {

        Class.forName(Config.getProperty(Config.DB_DRIVER));

        new ContainerDao().postEntity(new Environment(1,10,10));

        new ContainerDao().getEntity();
    }
}
