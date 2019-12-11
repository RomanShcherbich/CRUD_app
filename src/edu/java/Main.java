package edu.java;

import edu.java.dao.ContainerDao;
import edu.java.entity.Environment;
import edu.java.exception.DaoException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, DaoException {
        Class.forName("org.postgresql.Driver");

        Environment environment = new Environment();
        environment.setContainerId(1);
        environment.setTemperature(5);
        environment.setHumidity(5);
        environment.setInternalTime(LocalDateTime.now());
        environment.setGlobalTime(LocalDateTime.now());

        new ContainerDao().postEntity(environment);

        new ContainerDao().getEntity();
    }
}
