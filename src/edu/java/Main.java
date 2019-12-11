package edu.java;

import config.AppConfig;
import edu.java.dao.ContainerDao;
import edu.java.exception.DaoException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, DaoException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        AppConfig.appPropsInit();
        new ContainerDao().getEntity();
    }
}
