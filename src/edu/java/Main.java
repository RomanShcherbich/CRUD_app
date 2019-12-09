package edu.java;

import edu.java.dao.ContainerDao;
import edu.java.exception.DaoException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, DaoException {
        Class.forName("org.postgresql.Driver");
        new ContainerDao().getEntity();
    }
}
