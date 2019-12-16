import config.Config;
import dao.ContainerDao;
import entity.Environment;
import exception.DaoException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, DaoException {

        Class.forName(Config.getProperty(Config.DB_DRIVER));
    }
}
